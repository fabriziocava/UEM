package it.unical.uniexam.mvc.controll.student;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.StudentService;
import it.unical.uniexam.mvc.service.UtilsService;

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
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		s = slist.get(0);
		
		model.addAttribute("I",s);
		
		Long idCourse = Long.valueOf(request.getParameter("id"));
		ArrayList<Appeal> appeal = studentService.getAppeal(idCourse);
		model.addAttribute("appeal", appeal);
		
		Long idStudent = s.getId();
		ArrayList<AppealStudent> appealStudent = studentService.getAppealStudent(idStudent);
		model.addAttribute("as", appealStudent);
		
		return new ModelAndView("student/dialog/view_appeal", "model", model);
	}
	
	@RequestMapping("/appeal/inscribeToAppeal")
	public String inscribeToAppeal(HttpServletRequest request, Model model) {
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
		
		model.addAttribute("I",s);
		
		return null;
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
