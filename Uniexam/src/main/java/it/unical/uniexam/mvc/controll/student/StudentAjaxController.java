package it.unical.uniexam.mvc.controll.student;

import javax.servlet.http.HttpServletRequest;

import it.unical.uniexam.mvc.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/ajax")
public class StudentAjaxController {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/appeal/appeal_list")
	public String appeal_list(HttpServletRequest request, Model model) {
		
		return "student/appeal/appeal_list";
	}
	
}
