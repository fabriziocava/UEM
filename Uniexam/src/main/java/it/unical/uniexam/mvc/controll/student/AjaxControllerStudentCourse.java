package it.unical.uniexam.mvc.controll.student;

import javax.servlet.http.HttpServletRequest;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.mvc.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/ajax")
public class AjaxControllerStudentCourse {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/course/course_details")
	public String appeal_list(HttpServletRequest request, Model model) {
		Long idCourse = Long.valueOf(request.getParameter("id"));
		Course c = studentService.getCourseDetails(idCourse);
		model.addAttribute("course", c);
		return "student/course/course_details";
	}
	
}
