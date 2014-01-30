package it.unical.uniexam.mvc.controll.professor;

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
@RequestMapping("professor/ajax/course")
public class AjaxControllerCourse {

	@Autowired
	ProfessorService professorService;

	ArrayList<Course>courseAlreadyRequested;
//	add_commissionary mi deve dare il redirect to list commissionary
	
	@RequestMapping("/dialog/add_commissionary")
	public String add_commissionary(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String id=request.getParameter("idProf");
		String idCourse=request.getParameter("idCourse");
		Boolean ris=false;
		//se non c'è già lo inserisce...e se non sono più di 5!
		return "redirect:/professor/ajax/course/dialog/list_commissionary?id="+idCourse+"&ris="+ris;
	}
	
	
	@RequestMapping("/list_professor")
	public String list_professor(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String id=request.getParameter("id");
		
		if(id!=null && !id.equals("")){
			ArrayList<Professor>match=professorService.getProfessorsMatch(id);
			model.addAttribute("list", match);
//			System.out.println(studentsMatch.size());
			return "professor/autocomplete/professors";
		}
		return null;
	}
	
	@RequestMapping("/dialog/list_commissionary")
	public String list_commissionary(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		
		ArrayList<Professor>commissionary=professorService.getListCommissionary(idCourse);
		model.addAttribute("commissionary", commissionary);
		model.addAttribute("idCourse", idCourse);
		return "professor/course/list_commissionary";
	}
	
	@RequestMapping("/course_details")
	public String course_details(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=professorService.getCourseDetails(p,idCourse);
		model.addAttribute("course", c);
		return "professor/course/course_details";
	}

	
	@RequestMapping("/dialog/requested_course")
	public String dialog_requested_course(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=professorService.getCourseDetails(p,idCourse);
		
		ArrayList<Course>requested=new ArrayList<Course>();
		for(RequestedCourse r:c.getRequestedCourses()){
			requested.add(r.getCourse());
		}
		requested.add(c);
		courseAlreadyRequested=requested;
		
		model.addAttribute("course", c);
		
		String ris=request.getParameter("ris");
		if(ris!=null){
			if(ris.equals("true"))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
		}
		return "professor/course/dialog/requested_course";
	}
	
	@RequestMapping("/dialog/addRequestedCourse")
	public ModelAndView dialog_add_requested_course(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		p=plist.get(0);
		//riempire una mappa da dove posso sceliere i corsi che possono essere richiesti 
		String idCours=request.getParameter("id");
		if(idCours!=null){
			Long idCourse=Long.valueOf(idCours);
			ArrayList<String> degree = new ArrayList<String>();
			degree.add(RequestedCourse.POLICY_LIGHT);
			degree.add(RequestedCourse.POLICY_MEDIUM);
			degree.add(RequestedCourse.POLICY_STRONG);
			model.addAttribute("degree", degree);
			Set<Course> courses=professorService.getCoursesForRequestedCourseFromDepartment(idCourse);
			if(courseAlreadyRequested!=null && courseAlreadyRequested.size()>0)
				courses.removeAll(courseAlreadyRequested);
			model.addAttribute("courses", courses);
			
			model.addAttribute("idCourse", idCourse);
		}else{
			return new ModelAndView(UtilsService.GENERAL_ERROR);
//			response.sendRedirect("redirect:"+UtilsService.redirectToErrorPageGeneral("Errore", "Errore", model));
		}
//		Course c=professorService.getCourseDetails(p,idCourse);
//		model.addAttribute("course", c);
		return new ModelAndView("professor/course/dialog/addRequestedCourse", "model", model);
	}
	
	@RequestMapping(value="/addRequestedCourseAction", method=RequestMethod.POST)
	public ModelAndView dialog_add_requested_course_action(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		p=plist.get(0);
		Boolean ris=false;
		String idCours=request.getParameter("idCourse");
		if(idCours!= null && !idCours.equals("")){
			Long idCourse=Long.valueOf(idCours);
			ris=professorService.addRequestedCourse(idCourse,requestedCourse);
		}
//		return new ModelAndView("redirect:/"+ProfessorService.PROFESSOR_COURSE, "model", model);
		return new ModelAndView("redirect:/professor/ajax/course/dialog/requested_course?id="+idCours+"&ris="+ris, "model", model);
	}
	
	@RequestMapping(value="/dialog/requested_course/command", method=RequestMethod.POST)
	public String dialog_requested_course_command(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
//		Enumeration parameterNames = request.getParameterNames();
//		while (parameterNames.hasMoreElements()) {
//			String object = (String) parameterNames.nextElement();
//			System.out.println(object);
//		}
		String commands=request.getParameter("data");
//		sendRequestedCourse
		if(commands!=null && commands.startsWith("sendRequestedCourse")){
			commands=commands.replace("sendRequestedCourse", "");
			String []items=commands.split(":");
			String idCours=items[0];
//			System.out.println(commands);
			Long idCourse=Long.valueOf(idCours);
			if(professorService.applyCommandForRequestedCourse(idCourse,commands))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
			Course c=professorService.getCourseDetails(p,idCourse);
			model.addAttribute("course", c);
		}
//		System.out.println(commands);
		return "professor/course/dialog/requested_course";
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