package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("professor/ajax/sign")
public class AjaxControllerSign {

	@Autowired
	ProfessorService professorService;

	@RequestMapping("/prepare_from_appeal")
	public String prepare_from_appeal(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		
		try{
			String appealString=request.getParameter("id");
			Long idAppeal=Long.valueOf(appealString);
			if(appealString!=null && appealString.length()>0){
				ArrayList<AppealStudent> appealStudents=professorService.getAppealStudentsForPrepareSign(idAppeal);
				model.addAttribute("appealStudents", appealStudents);
				Appeal appeal=professorService.getAppealGround(idAppeal);
				model.addAttribute("appeal", appeal);
			}
		}catch(Exception e){new MokException(e);}
		
		return "professor/sign/prepare_from_appeal";
	}
	
	@RequestMapping("/list_appeals")
	public String list_appeals(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String appealString=request.getParameter("id");
		if(appealString!=null && appealString.length()>0){
			ArrayList<Appeal>appeanls=professorService.getAppealsMatch(appealString);
			model.addAttribute("listAppeals", appeanls);
		}
		
		return "professor/sign/list_autocomplete";
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