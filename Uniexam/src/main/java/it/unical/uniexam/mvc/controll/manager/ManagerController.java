package it.unical.uniexam.mvc.controll.manager;

import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
//	@RequestMapping(value="/manager/home", method=RequestMethod.GET)
//	public String homeManager(HttpServletRequest request,Model model){
//			
//		
//		return "/manager/home";
//	}
	
	@RequestMapping(value=ManagerService.MANAGER_HOME, method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model){
		
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;

		model.addAttribute("M",m);
		
		return ManagerService.MANAGER_HOME;
	}
	
	
}
