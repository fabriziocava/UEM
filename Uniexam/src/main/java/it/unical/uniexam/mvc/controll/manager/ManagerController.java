package it.unical.uniexam.mvc.controll.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	
	@RequestMapping(value=ManagerService.MANAGER_ACCOUNT, method=RequestMethod.GET)
	public String account(HttpServletRequest request,HttpServletResponse response,Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;

		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		return ManagerService.MANAGER_ACCOUNT;
	}
	
	
	@RequestMapping(value=ManagerService.MANAGER_EXAM , method=RequestMethod.GET)
	public String course(HttpServletRequest request, Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;

		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		// aggiungere altre cose

		return ManagerService.MANAGER_EXAM;
	}
	
	@RequestMapping(value=ManagerService.MANAGER_UPLOAD, method=RequestMethod.POST)
	public String uploadImageProfile(MultipartHttpServletRequest requestM,HttpServletRequest request,Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;
		MultipartFile file=requestM.getFile("file");
		if (!file.isEmpty()) {
			try {
				managerService.putImage(m, file.getInputStream(),(int)file.getSize());
			} catch (Exception e) {
				new MokException(e);
			}
		} else {
			//            return "You failed to upload because the file was empty.";
		}
		return "redirect:"+ManagerService.MANAGER_ACCOUNT;
	}
	
	
	@RequestMapping(value=ManagerService.MANAGER_IMAGE, method=RequestMethod.GET)
	public String getImage(HttpServletRequest request,HttpServletResponse response,Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;

		//		String name=request.getParameter("idImage");
		try {
			//			response.setContentType("image/jpeg");
			if(managerService.streamImage(m,response.getOutputStream())){
				//do nothing
			}else{
				response.addHeader("image", "no");
				response.sendRedirect("/uniexam/res/img/no_image.jpg");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	private void updatePersonalizzation(Model model, Manager m) {
		Map<String, String> personalizzationMap=managerService.getPersonalizzationValues(m.getId());
		model.addAttribute("personalizzationMap", personalizzationMap);
	}
	
}
