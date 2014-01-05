package it.unical.uniexam.mvc.controll.professor;

import java.util.Map;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author luigi
 *
 */
@Controller
@SessionAttributes({UtilsService.QUERY_SESSION})
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@RequestMapping(value=ProfessorService.PROFESSOR_HOME , method=RequestMethod.GET)
	public String home(HttpServletRequest request, ModelAndView model,
			@ModelAttribute(UtilsService.QUERY_SESSION)Session session
			){
		boolean ok=false;
		if(session==null || (session!=null && !professorService.checkSession(session, null))){
			// return pagina di errore
		}
		request.getSession().setAttribute(UtilsService.QUERY_SESSION, session);

		if(ok) return ProfessorService.PROFESSOR_HOME; 
		return ProfessorService.PROFESSOR_HOME;
	}


	///per interfacciarsi con il db bisogna utilizzare le service
	// definire una service con una annotation @Autowired dentro il controller
	// non c'è bisogno di definirla... 
	//
	/**
	 * 
	 */
}
