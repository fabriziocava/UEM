package it.unical.uniexam.mvc.controll.student;

import javax.servlet.http.HttpServletRequest;

import it.unical.uniexam.mvc.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("student/ajax")
public class AjaxControllerStudentAppeal {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/dialog/view_appeal")
	public ModelAndView dialogViewAppeal(HttpServletRequest request, Model model) {
		
		Long idCourse = Long.valueOf(request.getParameter("id"));
		
		return new ModelAndView("student/dialog/view_appeal", "model", model);
	}
	
}
