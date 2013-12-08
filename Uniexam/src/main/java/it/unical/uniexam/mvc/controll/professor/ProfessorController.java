package it.unical.uniexam.mvc.controll.professor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller

public class ProfessorController {



	@RequestMapping(value="professor/home" , method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model){
		HttpSession session = request.getSession(false);
		if(request.isRequestedSessionIdValid()){
			model.addAttribute("user",session.getAttribute("user"));
			//		System.out.println(username);
			
			return "professor/home";
		}
		return "home"; //error session
	}
}
