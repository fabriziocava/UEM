package it.unical.uniexam.mvc.controll.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerController {

	@RequestMapping(value="manager/home", method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model){
		
		return "manager/home";
	}
}
