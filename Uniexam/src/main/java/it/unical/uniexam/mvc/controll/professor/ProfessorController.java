package it.unical.uniexam.mvc.controll.professor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfMessage;
import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UserService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;

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
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Professor.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Professor p=(Professor)user;

		model.addAttribute("I",p);
		// aggiungere altre cose
		updateNotification(model, p);

		return ProfessorService.PROFESSOR_HOME;
	}

	/**
	 * in model add structNotification, a structure in which we have a Array of Array 
	 * like this : {{[MessageOfGroup],[CommentOfMessage],[CommentOfMessage]},{[MessageOfGroup],[CommentOfMessage]}}
	 * for each array in the first position we have a message of a group in which the comments belong
	 * @param model
	 * @param p
	 */
	private void updateNotification(Model model, Professor p) {
		try{
			if(p.getNoReadComments().size()>0){
				ArrayList<CommentOfMessage> comms=professorService.getNotificationFromComments(p.getNoReadComments());
				Collections.sort(comms, new Comparator<CommentOfMessage>(){
					@Override
					public int compare(CommentOfMessage o1, CommentOfMessage o2) {
						if(o1!=null && o2!=null)
							return (int) (o2.getDate_of_comment().getTime()-o1.getDate_of_comment().getTime());
						return 0;
					}
				});

				if(comms.size()>0){
					//creare struttura che aggrega, i commenti appartenenti ad un messaggio
					ArrayList<ArrayList<Object>>structure=new ArrayList<ArrayList<Object>>();
					int count=-1;
					MessageOfGroup mes=null;
					ArrayList<CommentOfMessage>removable=new ArrayList<CommentOfMessage>();
					while(comms.size()>0){
						for (CommentOfMessage commentOfMessage : comms) {
							if(commentOfMessage==null){
								removable.add(commentOfMessage);
								break;
							}
							if(mes==null){
								mes=commentOfMessage.getOfMessage();
								structure.add(new ArrayList<Object>());
								count++;
								structure.get(count).add(mes);
							}
							if(mes.getId()==commentOfMessage.getOfMessage().getId()){
								structure.get(count).add(commentOfMessage);
								removable.add(commentOfMessage);
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
