package it.unical.uniexam.mvc.controll.professor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.ComparatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author luigi
 *
 */
@Controller
@RequestMapping("professor/sign")
@SessionAttributes("user")
public class ProfessorControllerSign {

	@Autowired
	ProfessorService professorService;
	
	
	@RequestMapping("/signAsCommissionar")
	public ModelAndView signAsCommissionar(HttpServletRequest request, Model model){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return new ModelAndView(UtilsService.LOGIN);
		
		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		try{
			ArrayList<ArrayList<Object>> appealStudents=professorService.getAppealStudentsForSignAdCommission(p.getId());
			model.addAttribute("appealStudents", appealStudents);
		}catch(Exception e){new MokException(e);}
		
		return new ModelAndView("professor/sign/singlesignAsCommissionar","model",model);
	}
	
	@RequestMapping("/add_single_sign")
	public ModelAndView add_single_sign(@ModelAttribute("appealStudent")AppealStudent appealStudent, 
			HttpServletRequest request, Model model,BindingResult result){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);
		
		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		try{
			ArrayList<Course>courses=(ArrayList<Course>) professorService.getCourseAssociated(p.getId());
			model.addAttribute("courses", courses);
		}catch(Exception e){new MokException(e);}
		
		Boolean ris=false;
		try{
			AppealStudenValidator appealStudentValidator = new AppealStudenValidator();
	        appealStudentValidator.validate(appealStudent, result);
	        if(!result.hasErrors())
	        	ris=professorService.addPrepareSign(appealStudent,p.getId());
	        else{
	        	return new ModelAndView("professor/sign/prepare_sign","model",model);
	        }
		}catch(Exception e){new MokException(e);}
		model.addAttribute("ris", ris);
		return new ModelAndView("professor/sign/prepare_sign","model",model);
	}
	
	@RequestMapping("/singlesign")
	public ModelAndView singlesign(@ModelAttribute("appealStudent")AppealStudent appealStudent, 
			HttpServletRequest request, Model model,BindingResult result){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);
		
		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		try{
			ArrayList<ArrayList<Object>> appealStudents=professorService.getAppealStudentsForSign(p.getId());
			model.addAttribute("appealStudents", appealStudents);
		}catch(Exception e){new MokException(e);}
		
		return new ModelAndView("professor/sign/singlesign","model",model);
	}
	
	@RequestMapping("/singleprepare")
	public ModelAndView singleprepare(@ModelAttribute("appealStudent")AppealStudent appealStudent, 
			HttpServletRequest request, Model model){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return null;//new ModelAndView(ProfessorService.PROFESSOR_HOME);
		
		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		try{
			ArrayList<Course>courses=(ArrayList<Course>) professorService.getCourseAssociated(p.getId());
			model.addAttribute("courses", courses);
		}catch(Exception e){new MokException(e);}
		
		return new ModelAndView("professor/sign/prepare_sign","model",model);
	}
	
	@RequestMapping("/from_appeal_sign")
	public String from_appeal_sign(HttpServletRequest request, Model model){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return redirect;
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return UtilsService.LOGIN;
		
		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		try{
			String appealString=request.getParameter("id");
			Long idAppeal=Long.valueOf(appealString);
			if(appealString!=null && appealString.length()>0){
				ArrayList<ArrayList<Object>> appealStudents=professorService.getAppealStudentsForSignFromAppeal(p.getId(),idAppeal);
				model.addAttribute("appealStudents", appealStudents);
				Appeal appeal=professorService.getAppealGround(idAppeal);
				model.addAttribute("appeal", appeal);
			}
		}catch(Exception e){new MokException(e);}
		
		return "professor/sign/from_appeal_sign";
	}
	
	@RequestMapping("/prepare_from_appeal")
	public String prepare_from_appeal(HttpServletRequest request, Model model){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return redirect;
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return UtilsService.LOGIN;
		
		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		try{
			String appealString=request.getParameter("id");
			Long idAppeal=Long.valueOf(appealString);
			if(appealString!=null && appealString.length()>0){
				ArrayList<ArrayList<Object>> appealStudents=professorService.getAppealStudentsForPrepareSign(p.getId(),idAppeal);
				model.addAttribute("appealStudents", appealStudents);
				Appeal appeal=professorService.getAppealGround(idAppeal);
				model.addAttribute("appeal", appeal);
			}
		}catch(Exception e){new MokException(e);}
		
		return "professor/sign/prepare_from_appeal";
	}

	@RequestMapping(value="" , method=RequestMethod.GET)
	public String sign(HttpServletRequest request, Model model){
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return redirect;
//		p=plist.get(0);
		Professor p=(Professor)request.getSession().getAttribute("user");
		if(p==null) return UtilsService.LOGIN;

		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		// aggiungere altre cose
		/**
		 * qui all'ingresso io metterei un calendario con le lezioni che il professore ha
		 * con la possibilità di aggiungere note, o di progammare l'invio di email,
		 * o di progammare avvisi per lui su un certo evento! un calendario in pratica
		 * ******questo se rimane tempo è ne ho la possibilità
		 */

		return "professor/sign";
	}
	
	//OTHER METHOD

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

	/**
	 * Add to Model the "personalizzationMap" 
	 */
	private void updatePersonalizzation(Model model, Professor p) {
		Map<String, String> personalizzationMap=professorService.getPersonalizzationValues(p.getId());
		model.addAttribute("personalizzationMap", personalizzationMap);
	}

//	String setProfessorOrRedirect(HttpServletRequest request,Model model, ArrayList<Professor> plist) {
//		User user=professorService.getSession(request.getSession().getId());
//		if(user==null){
//			HttpSession session = request.getSession(false);
//			if(session!=null){
//				session.invalidate();
//			}
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta Error code 1", "sessione", model);
//		}
//		if(user.getClass()!=Professor.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore, Utente non riconosciuto", "Classe Utente", model);
//		}
//		plist.add((Professor)user);
//		return null;
//	}

	///per interfacciarsi con il db bisogna utilizzare le service
	// definire una service con una annotation @Autowired dentro il controller
	// non c'è bisogno di definirla... 
	//
	/**
	 * 
	 */
}
