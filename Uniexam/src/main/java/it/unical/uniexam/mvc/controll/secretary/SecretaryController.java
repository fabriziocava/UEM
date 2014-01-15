package it.unical.uniexam.mvc.controll.secretary;

import java.util.HashMap;
import java.util.Map;

import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.mvc.service.SecretaryService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SecretaryController {
	
	//@Autowired
	//SecretaryService secretaryService;
	
	@RequestMapping(value=SecretaryService.SECRETARY_HOME, method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model){
		return SecretaryService.SECRETARY_HOME;
	}
	
	@RequestMapping(value=SecretaryService.SECRETARY_REGISTER_MANAGER, method=RequestMethod.GET)
	public ModelAndView getRegisterManagerForm(@ModelAttribute("registerManager") Manager manager, BindingResult result) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(SecretaryService.SECRETARY_REGISTER_MANAGER, "model", model);
	}
	
}
