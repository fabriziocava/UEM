package it.unical.uniexam.mvc.service;

import it.unical.uniexam.MokException;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author luigi
 *
 */
public class UtilsService {
	public static final String SEPARATOR_ID_PAIRS = ":";
	public static final String SEPARATOR_ITEM_PERSONALIZZATION$ = "$";
	
	public final static String LOGIN_SUCCESSFUL="";
	public final static String LOGIN="login";
	
	public static final String QUERY_SESSION = "idSession";
	public static final String SESSION_ERROR = "error_page/session_error";
	public static final String LOGIN_PASSWD_ERROR = "error_page/passwd_error";
	public static final String LOGIN_USERNAME_ERROR = "error_page/username_error";
	/**
	 * ?error_message=this&error_type=these
	 */
	public static final String GENERAL_ERROR = "error_page/general_error";

	public static ModelAndView getView(String path,String modelName,Model model){
		if(model!=null)
			return new ModelAndView(path,modelName,model);
		return new ModelAndView(path);
	}

	public static Map<String,String> getMapValues(String queryString){
		Map<String, String>mapValues=new HashMap<String,String>();
		if(queryString==null)
			return mapValues;
		try{
		String []values=queryString.split("&");
		for (String value: values) {
				String[] split = value.split("=");
				String name=split[0];
				String val=split[1];
				mapValues.put(name, val);
		}
		}catch(Exception e){new MokException(e);}
		return mapValues;
	}
	
	public static String redirectToErrorPageGeneral(String error_message,String error_type,Model model) {
		model.addAttribute("error_message", error_message);
		model.addAttribute("error_type", error_type);
		return UtilsService.GENERAL_ERROR;
	}

	public static String getStringPersonalizzation(Map<String, String> mapValues2) {
		StringBuilder sb=new StringBuilder();
		Set<Entry<String, String>> entrySet = mapValues2.entrySet();
		boolean p=true;
		for (Entry<String, String> entry : entrySet) {
			if(p){
				p=false;
			}else{
				sb.append(SEPARATOR_ITEM_PERSONALIZZATION$);
			}
			sb.append(entry.getKey());
			sb.append(SEPARATOR_ID_PAIRS);
			sb.append(entry.getValue());
		}
		sb.append(SEPARATOR_ITEM_PERSONALIZZATION$);
		return sb.toString();
	}

	//idTAG:name=cicio%surname=pasticcio$idTAG:id=125
	//idTAG=[name=cicio%surname=pasticcio]&idTAG=[id=125]
	//box-notify[left:10px,top:50px
	//box-notify:left=10px%top=50px$
	public static Map<String, String> getMapPersonalizzation(String personalizzation) {
		Map<String,String>res=new HashMap<String, String>();
		if(personalizzation==null || personalizzation.length()==0)
			return res;
		String []items=personalizzation.split("\\"+SEPARATOR_ITEM_PERSONALIZZATION$);
		for (String item: items) {
			String[] element = item.split(SEPARATOR_ID_PAIRS);
			res.put(element[0], element[1]);
		}
		return res;
	}
	
	public static String trasformPersonalizzationFromAJAXtoStringDB(String personalizzation) {
		personalizzation=personalizzation.replaceAll(",", "%");
		personalizzation=personalizzation.replaceAll(":", "=");
		personalizzation=personalizzation.replaceAll("\\[", UtilsService.SEPARATOR_ID_PAIRS);
		personalizzation=personalizzation+UtilsService.SEPARATOR_ITEM_PERSONALIZZATION$;
		return personalizzation;
	}
}
