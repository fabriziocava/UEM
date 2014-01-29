package it.unical.uniexam.mvc.controll.professor;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Map;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
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
public class ProfessorController {

	@Autowired
	ProfessorService professorService;
	
	@RequestMapping(value=ProfessorService.PROFESSOR_HOME , method=RequestMethod.GET)
	public String home(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);

		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		
		updateNews(model,p);
		
		// aggiungere altre cose
		/**
		 * qui all'ingresso io metterei un calendario con le lezioni che il professore ha
		 * con la possibilità di aggiungere note, o di progammare l'invio di email,
		 * o di progammare avvisi per lui su un certo evento! un calendario in pratica
		 * ******questo se rimane tempo è ne ho la possibilità
		 */

		return ProfessorService.PROFESSOR_HOME;
	}

	private void updateNews(Model model, Professor p) {
		//news sulle firme come commissione
		ArrayList<ArrayList<Object>> appealStudents=professorService.getAppealStudentsForSignAdCommission(p.getId());
		
		ArrayList<Object> reg = appealStudents.get(0);
		ArrayList<Object> noreg = appealStudents.get(1);
		Integer ntot=reg.size()+noreg.size();
		String message="Attenzione! Hai "+ntot+" esami che devi firmare come commissario"
				+ "di questi fanno parte "+reg.size()+" studenti regolari e "+noreg.size()+" studenti non regolari";
		String link="/uniexam/professor/sign/signAsCommissionar";
		
		// e qua posso inserire altre notizie
		
		
		News news=new News(message, link);
		
		
		
		
		ArrayList<News>newss=new ArrayList<News>();
		newss.add(news);
		model.addAttribute("newss", newss);
		
		
	}

	@RequestMapping(value=ProfessorService.PROFESSOR_COURSE)
	public String course(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);

		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		// aggiungere altre cose
		{
			//aggiungere informazione sui corsi 
			// far visualizzare per questa pagina
			// cosa può fare in questa sezione?
			/**
			 * qui deve visualizzare i corsi che gli sono stati associati, ovvero quei corsi in
			 * cui lui è il titolare
			 * Una lista....
			 * 
			 * anzi: con jQuery quando la lista supera il numero 3 fa apparire una barra di ricerca veloce
			 * sul nome del corso
			 * no
			 * 
			 * Con possibilità di scrivere qualche nota sotto il nome dell'esame 
			 */
			ArrayList<Course>courses=professorService.getAssociatedCourseWithGroups(p);
			model.addAttribute("courses", courses);

		}

		return ProfessorService.PROFESSOR_COURSE;
	}

	@RequestMapping(value=ProfessorService.PROFESSOR_ACCOUNT, method=RequestMethod.GET)
	public String account(HttpServletRequest request,HttpServletResponse response,Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);

		model.addAttribute("I",p);
		updateNotification(model, p);
		updatePersonalizzation(model, p);
		return ProfessorService.PROFESSOR_ACCOUNT;
	}

//	@RequestMapping(value="professor/addRequestedCourseAction", method=RequestMethod.POST)
//	public ModelAndView dialog_add_requested_course_action(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
//			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
//		Professor p=null;
//		String redirect=null;
//		ArrayList<Professor>plist=new ArrayList<Professor>();
//		redirect=setProfessorOrRedirect(request,model,plist);
//		if(redirect!=null)
//			return new ModelAndView(redirect);
//		p=plist.get(0);
//		
//		String idCours=request.getParameter("idCourse");
//		
//		Long idCourse=Long.valueOf(idCours);
//		
//		Boolean ris=professorService.addRequestedCourse(idCourse,requestedCourse);
//		
////		return new ModelAndView("redirect:/"+ProfessorService.PROFESSOR_COURSE, "model", model);
//		return new ModelAndView("redirect:/professor/ajax/dialog/requested_course?id="+idCourse+"&ris="+ris, "model", model);
//	}

	
	//Pagine Secondarie

	@RequestMapping(value=ProfessorService.PROFESSOR_PERSONALIZZATION , method=RequestMethod.POST)
	public String personalizzation(HttpServletRequest request,HttpServletResponse response,Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		////idTAG:name=cicio%surname=pasticcio$idTAG:id=125
		//box-notify[left:10px,top:50px
		String personalizzation=(String)request.getParameter("data");
		personalizzation = UtilsService.trasformPersonalizzationFromAJAXtoStringDB(personalizzation);
		//		Map<String, String> mapValues = UtilsService.getMapValues(request.getQueryString());

		professorService.updatePersonalizzationValues(personalizzation,p.getId());

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.println("ok");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}

	@RequestMapping(value=ProfessorService.PROFESSOR_CHANGE_NOTE , method=RequestMethod.POST)
	public String changeNote(HttpServletRequest request,HttpServletResponse response,Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		////idTAG:name=cicio%surname=pasticcio$idTAG:id=125
		//box-notify[left:10px,top:50px
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String param = (String) parameterNames.nextElement();
			if(param.startsWith("data")){
				String idCours=param.replace("data", "");
				Long idCourse=Long.valueOf(idCours);
				professorService.changeNote(idCourse,request.getParameter(param));
			}
		}

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			outputStream.println("ok");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}

	@RequestMapping(value=ProfessorService.PROFESSOR_IMAGE , method=RequestMethod.GET)
	public String getImage(HttpServletRequest request,HttpServletResponse response,Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		//		String name=request.getParameter("idImage");
		try {
			//			response.setContentType("image/jpeg");
			if(professorService.streamImage(p,response.getOutputStream())){
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

	@RequestMapping(value=ProfessorService.PROFESSOR_UPLOAD, method=RequestMethod.POST)
	public String uploadImageProfile(MultipartHttpServletRequest requestM,HttpServletRequest request,Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		MultipartFile file=requestM.getFile("file");
		if (!file.isEmpty()) {
			try {
				professorService.putImage(p, file.getInputStream(),(int)file.getSize());
			} catch (Exception e) {
				new MokException(e);
			}
		} else {
			//            return "You failed to upload because the file was empty.";
		}
		return "redirect:/"+ProfessorService.PROFESSOR_ACCOUNT;
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

	///per interfacciarsi con il db bisogna utilizzare le service
	// definire una service con una annotation @Autowired dentro il controller
	// non c'è bisogno di definirla... 
	//
	/**
	 * 
	 */
}
