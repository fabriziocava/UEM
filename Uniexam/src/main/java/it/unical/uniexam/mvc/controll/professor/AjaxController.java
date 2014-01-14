package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("professor/ajax")
public class AjaxController {

	@Autowired
	ProfessorService professorService;

	@RequestMapping("/course/course_details")
	public String course_details(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idCours=request.getParameter("idCourse");
		Long idCourse=Long.valueOf(idCours);
		Course c=professorService.getCourseDetails(p,idCourse);
		model.addAttribute("course", c);
		return "professor/ajax/course/course_details";
	}


	String setProfessorOrRedirect(HttpServletRequest request,Model model, ArrayList<Professor> plist) {
		User user=professorService.getSession(request.getSession().getId());
		if(user==null){
			HttpSession session = request.getSession(false);
			if(session!=null){
				session.invalidate();
			}
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta Error code 1", "sessione", model);
		}
		if(user.getClass()!=Professor.class){
			return UtilsService.redirectToErrorPageGeneral("Errore, Utente non riconosciuto", "Classe Utente", model);
		}
		plist.add((Professor)user);
		return null;
	}
}