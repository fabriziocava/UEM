package it.unical.uniexam.mvc.controll.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.StudentService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
@RequestMapping("student/ajax")
public class AjaxControllerStudentCourse {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/course/course_details")
	public String appeal_list(HttpServletRequest request, Model model) {
//		Student s = null;
//		String redirect = null;
//		ArrayList<Student> slist = new ArrayList<Student>();
//		redirect = setStudentOrRedirect(request, model, slist);
//		if(redirect!=null)
//			return redirect;
//		s = slist.get(0);
		
		Student s = (Student) request.getSession().getAttribute("user");
		if(s==null)
			return null;
		
		model.addAttribute("I",s);
		
		Long idCourse = Long.valueOf(request.getParameter("id"));
		Course c = studentService.getCourseDetails(idCourse);
		model.addAttribute("course", c);
		return "student/course/course_details";
	}
	
	String setStudentOrRedirect(HttpServletRequest request,Model model, ArrayList<Student> slist) {
		User user=studentService.getSession(request.getSession().getId());
		if(user==null){
			HttpSession session = request.getSession(false);
			if(session!=null){
				session.invalidate();
			}
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta Error code 1", "sessione", model);
		}
		if(user.getClass()!=Student.class){
			return UtilsService.redirectToErrorPageGeneral("Errore, Utente non riconosciuto", "Classe Utente", model);
		}
		slist.add((Student)user);
		return null;
	}
	
}
