package it.unical.uniexam.mvc.service;

import it.unical.uniexam.MokException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author luigi
 *
 */
public class UtilsService {
	public final static String LOGIN_SUCCESSFUL="";
	public final static String HOME="home";
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
//	public static String redirectToErrorPageGeneral(String error_message,String error_type,ModelAndView model) {
//		model.addObject("error_message", error_message);
//		model.addObject("error_type", error_type);
//		return UtilsService.GENERAL_ERROR;
//	}
	
}
