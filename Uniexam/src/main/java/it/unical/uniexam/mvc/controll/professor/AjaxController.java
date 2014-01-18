package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
@RequestMapping("professor/ajax")
public class AjaxController {

	@Autowired
	ProfessorService professorService;

	@RequestMapping("/course/course_details")
	public String course_details(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idCours=request.getParameter("idCourse");
		Long idCourse=Long.valueOf(idCours);
		Course c=professorService.getCourseDetails(p,idCourse);
		model.addAttribute("course", c);
		return "professor/course/course_details";
	}
//	/ajax/course/course_details/dialog

	@RequestMapping("/dialog/add_appeal")
	public String dialog_add_appeal(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		
		return "professor/dialog/add_appeal";
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
		model.addAttribute("course", c);
		
		String ris=request.getParameter("ris");
		if(ris!=null){
			if(ris.equals("true"))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
		}
		return "professor/dialog/requested_course";
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
			model.addAttribute("courses", courses);
			
			model.addAttribute("idCourse", idCourse);
		}else{
			return new ModelAndView(UtilsService.GENERAL_ERROR);
//			response.sendRedirect("redirect:"+UtilsService.redirectToErrorPageGeneral("Errore", "Errore", model));
		}
//		Course c=professorService.getCourseDetails(p,idCourse);
//		model.addAttribute("course", c);
		return new ModelAndView("professor/dialog/addRequestedCourse", "model", model);
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
		return "professor/dialog/requested_course";
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