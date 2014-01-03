package it.unical.uniexam.mvc.service;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author luigi
 *
 */
public class UtilsService {
	public final static String LOGIN_SUCCESSFUL="";
	public final static String LOGIN_ERROR="";

	public static ModelAndView getView(String path,String modelName,Model model){
		if(model!=null)
			return new ModelAndView(path,modelName,model);
		return new ModelAndView(path);
	}
}
