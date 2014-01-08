package it.unical.uniexam.mvc.controll.professor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UserService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.ComparatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@RequestMapping(value=ProfessorService.PROFESSOR_HOME , method=RequestMethod.GET)
	public String home(HttpServletRequest request, Model model){
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
		Professor p=(Professor)user;

		model.addAttribute("I",p);
		updateNotification(model, p);
		// aggiungere altre cose
		/**
		 * qui all'ingresso io metterei un calendario con le lezioni che il professore ha
		 * con la possibilità di aggiungere note, o di progammare l'invio di email,
		 * o di progammare avvisi per lui su un certo evento! un calendario in pratica
		 * ******questo se rimane tempo è ne ho la possibilità
		 */

		return ProfessorService.PROFESSOR_HOME;
	}

	@RequestMapping(value=ProfessorService.PROFESSOR_COURSE , method=RequestMethod.GET)
	public String course(HttpServletRequest request, Model model){
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
		Professor p=(Professor)user;

		model.addAttribute("I",p);
		updateNotification(model, p);
		// aggiungere altre cose
		{
			//aggiungere informazione sui corsi e su tutto il resto che intendi 
			// far visualizzare per questa pagina
			// cosa può fare in questa sezione?
			/**
			 * allora vediamo
			 * qui deve visualizzare i corsi che gli sono stati associati, ovvero quei corsi in
			 * cui lui è il titolare
			 * Una lista....
			 * In ordine in base a quelli più cliccati! ovvero nel quale effettua più accessi
			 * anzi: con jQuery quando la lista supera il numero 3 fa apparire una barra di ricerca veloce
			 * sul nome del corso
			 * 
			 * per ogni corso in basso a destra un elenco con il link ai vari gruppi aperti
			 * ed in fondo alla lista i gruppi chiusi
			 * 
			 * Con possibilità di scrivere qualche nota sotto il nome dell'esame 
			 */
			ArrayList<Course>courses=professorService.getAssociatedCourseWithGroups(user);
			model.addAttribute("courses", courses);
			
		}
		
		return ProfessorService.PROFESSOR_COURSE;
	}
	
	
	/**
	 * in model add structNotification, a structure in which we have a Array of Array 
	 * like this : {{[PostOfGroup],[CommentOfPost],[CommentOfPost]},{[PostOfGroup],[CommentOfPost]}}
	 * for each array in the first position we have a message of a group in which the comments belong
	 * @param model
	 * @param p
	 */
	private void updateNotification(Model model, Professor p) {
		try{
			if(p.getNoReadComments().size()>0){
				ArrayList<CommentOfPost> comms=professorService.getNotificationFromComments(p.getNoReadComments());
				Collections.sort(comms, new Comparator<CommentOfPost>(){
					@Override
					public int compare(CommentOfPost o1, CommentOfPost o2) {
						if(o1!=null && o2!=null)
							return (int) (o2.getDate_of_comment().getTime()-o1.getDate_of_comment().getTime());
						return 0;
					}
				});

				if(comms.size()>0){
					int generalCount=0;
					//creare struttura che aggrega, i commenti appartenenti ad un messaggio
					ArrayList<ArrayList<Object>>structure=new ArrayList<ArrayList<Object>>();
					int count=-1;
					PostOfGroup mes=null;
					ArrayList<CommentOfPost>removable=new ArrayList<CommentOfPost>();
					while(comms.size()>0){
						for (CommentOfPost commentOfPost : comms) {
							if(commentOfPost==null){
								removable.add(commentOfPost);
								break;
							}
							if(mes==null){
								mes=commentOfPost.getOfPost();
								structure.add(new ArrayList<Object>());
								count++;
								structure.get(count).add(mes);
							}
							if(mes.getId()==commentOfPost.getOfPost().getId()){
								structure.get(count).add(commentOfPost);
								removable.add(commentOfPost);
								generalCount++;
							}
						}
						mes=null;
						comms.removeAll(removable);
						removable.clear();
					}
					model.addAttribute("structNotification", structure);
				}
			}
		}catch(Exception e){new MokException(e);}
	}

	///per interfacciarsi con il db bisogna utilizzare le service
	// definire una service con una annotation @Autowired dentro il controller
	// non c'è bisogno di definirla... 
	//
	/**
	 * 
	 */
}
