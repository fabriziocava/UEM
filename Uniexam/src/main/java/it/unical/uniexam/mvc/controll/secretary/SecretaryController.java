package it.unical.uniexam.mvc.controll.secretary;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class SecretaryController {
	
	@RequestMapping(value="/secretary/home", method=RequestMethod.GET)
	public String homeStudent(HttpServletRequest request,Model model){
			
		return "/secretary/home";
	}
	
}
