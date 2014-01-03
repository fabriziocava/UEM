package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.mvc.service.ProfessorService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author luigi
 *
 */
@Controller
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@RequestMapping(value="professor/home" , method=RequestMethod.GET)
	public String home(HttpServletRequest request,@ModelAttribute("model") ModelAndView mod, ModelAndView model){
//		HttpSession session = request.getSession(false);
//		if(request.isRequestedSessionIdValid()){
//			model.addAttribute("user",session.getAttribute("user"));
			//		System.out.println(username);
//			System.out.println(request.getRequestedSessionId());
//			return "professor/home";
//		}
		Map<String, Object> model2 = model.getModel();
		Session s=(Session) model2.get("session");
		if(!model2.containsKey("user")){
			Professor I=professorService.getProfessor(s.getOwner());
			model.addObject("user", I);
		}
		return "professor/home"; //error session
	}
	
	
	///per interfacciarsi con il db bisogna utilizzare le service
	// definire una service con una annotation @Autowired dentro il controller
	// non c'è bisogno di definirla... 
	//
	/**
	 * 
	 */
}
