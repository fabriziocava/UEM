package it.unical.uniexam.mvc.controll.professor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfessorController {

	@RequestMapping(value="professor/home" , method=RequestMethod.GET)
	public String home(@RequestParam("user") String username,Model model){
		model.addAttribute("user",username);
//		System.out.println(username);
		return "professor/home";
	}
}
