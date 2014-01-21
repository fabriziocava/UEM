package it.unical.uniexam.mvc.controll.manager;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("manager/ajax")
public class AjaxControllerExamsession {
	
	@Autowired
	ManagerService managerService;
	
	@RequestMapping("/dialog/view_examsession")
	public ModelAndView dialog_view_appeal(HttpServletRequest request, Model model){

		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idexamsession=request.getParameter("id");
		Long ides=Long.valueOf(idexamsession);
		ExamSession examsession=managerService.getExamsession(ides);
		model.addAttribute("examsession", examsession);
		
		return new ModelAndView("manager/dialog/view_examsession", "model", model);
	
		
	}

}
