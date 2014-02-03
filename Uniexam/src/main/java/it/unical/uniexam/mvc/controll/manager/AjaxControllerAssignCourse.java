package it.unical.uniexam.mvc.controll.manager;

import javax.servlet.http.HttpServletRequest;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("user")
@RequestMapping("manager/ajax")
public class AjaxControllerAssignCourse {
	
	@Autowired
	ManagerService managerService;
	
	
	@RequestMapping("/assignCourse/course_details")
	public String course_details(HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return (UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
//		}
//		if(user.getClass()!=Manager.class){
//			return (UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return null;
		model.addAttribute("M",m);
		
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=managerService.getCourseDetails(idCourse);
		model.addAttribute("course", c);
		
		
		return "manager/assignCourse/course_details";
		
	}
	

}
