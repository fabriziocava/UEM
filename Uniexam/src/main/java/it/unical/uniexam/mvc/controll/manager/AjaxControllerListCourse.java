package it.unical.uniexam.mvc.controll.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("manager/ajax")

public class AjaxControllerListCourse {
	
	
	@Autowired
	ManagerService managerService;
	
	@RequestMapping("/course/course_details")
	public String course_details(HttpServletRequest request, Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return (UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
		}
		if(user.getClass()!=Manager.class){
			return (UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=managerService.getCourseDetails(idCourse);
		model.addAttribute("course", c);
		
		
		return "manager/course/course_details";
		
	}
	
	
	@RequestMapping("/dialog/requested_course")
	public String dialog_requested_course(HttpServletRequest request, Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return (UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
		}
		if(user.getClass()!=Manager.class){
			return (UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=managerService.getCourseDetails(idCourse);
		model.addAttribute("course", c);
		
		String ris=request.getParameter("ris");
		if(ris!=null){
			if(ris.equals("true"))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
		}
		return "manager/dialog/requested_course";
	}
	
	@RequestMapping("/dialog/AssegnaCorso")
	public String dialog_assegnaCorso(HttpServletRequest request, Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return (UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
		}
		if(user.getClass()!=Manager.class){
			return (UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idCours=request.getParameter("id");
		Long idCourse=Long.valueOf(idCours);
		Course c=managerService.getCourseDetails(idCourse);
		model.addAttribute("course", c);
		
		String ris=request.getParameter("ris");
		if(ris!=null){
			if(ris.equals("true"))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
		}
		return "manager/dialog/AssegnaCorso";
	}
	
	
	@RequestMapping("/dialog/AssegnaCorso_Professore")
	public ModelAndView dialog_AssegnaCorso(@ModelAttribute("assegnaCorso") Course course,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
		}
		if(user.getClass()!=Manager.class){
			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idcourse=request.getParameter("id");
		Long idc=Long.valueOf(idcourse);
		model.addAttribute("idcourse",idc);
		
		Set<Professor> p=managerService.getProfessorfromDepartment(m.getDepartmentAssociated());
		model.addAttribute("professors", p);
		
		String ris=request.getParameter("ris");
		if(ris!=null){
			if(ris.equals("true"))
				model.addAttribute("result","success");
			else
				model.addAttribute("result","error");
		}
		return new ModelAndView("manager/dialog/AssegnaCorso_Professore","model",model);
	}
	
	
	
	@RequestMapping("/dialog/addRequestedCourse")
	public ModelAndView dialog_add_requested_course(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
		}
		if(user.getClass()!=Manager.class){
			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		String idCours=request.getParameter("id");
		if(idCours!=null){
			Long idCoursereq=Long.valueOf(idCours);
			ArrayList<String> degree = new ArrayList<String>();
			degree.add(RequestedCourse.POLICY_STRONG);
			model.addAttribute("degree", degree);
			
			ArrayList<Course> courses=managerService.getCourses();
			DegreeCourse dg=null;
			for(Course c: courses){
				if(c.getId()==idCoursereq){
					
					dg=c.getDegreeCourse();
				}
			}
			ArrayList<Course> courses2=managerService.getCoursesFromDegreeCourse(dg.getId());
			model.addAttribute("courses", courses2);
			
			model.addAttribute("idCoursereq", idCoursereq);
		}else{
			return new ModelAndView(UtilsService.GENERAL_ERROR);
		}
		return new ModelAndView("manager/dialog/addRequestedCourse", "model", model);
	}

	
	@RequestMapping("/course/remove_course")
	public String remove_course(HttpServletRequest request, Model model,HttpServletResponse response){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idcourse=request.getParameter("id");
		Long idc=Long.valueOf(idcourse);

		String idcourserequested=request.getParameter("id2");
		Long idcr=Long.valueOf(idcourserequested);
		Boolean res=managerService.removeRequestedCourse(idc, idcr);
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/course/remove_courseALL")
	public String remove_courseALL(HttpServletRequest request, Model model,HttpServletResponse response){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idcourse=request.getParameter("id");
		Long idc=Long.valueOf(idcourse);

		String idcourserequested=request.getParameter("id2");
		Long idcr=Long.valueOf(idcourserequested);
		
		Boolean res=managerService.removeCourse(idc,idcr);
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/course/remove_courseAsHolder")
	public String remove_courseasHolder(HttpServletRequest request, Model model,HttpServletResponse response){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
		}
		if(user.getClass()!=Manager.class){
			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		String idcourse=request.getParameter("id");
		Long idc=Long.valueOf(idcourse);

		String idcourserequested=request.getParameter("id2");
		Long idcr=Long.valueOf(idcourserequested);
		Boolean res=managerService.removeHolderProfessor(idc, idcr);
		
		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			new MokException(e);
		}
		return null;
	}
	
	@RequestMapping("/dialog/addCourse")
	public ModelAndView dialog_add_session(@ModelAttribute("addcourse") Course course,HttpServletRequest request, Model model){
		User user=managerService.getSession(request.getSession().getId());
		if(user==null){
			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
		}
		if(user.getClass()!=Manager.class){
			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
		}
		Manager m=(Manager)user;
		model.addAttribute("M",m);
		
		Set<DegreeCourse> degreecourses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("degreecourses", degreecourses);
		
//		Set<Professor> professors=managerService.getProfessors();
//		model.addAttribute("professors", professors);
		
		ArrayList<Course> courses=managerService.getCourses();
		
//		Course c=new Course(null, null, null, null, null, null, null, null);
//		courses.add(c);
		
		model.addAttribute("courses", courses);
		
		
		return new ModelAndView("manager/dialog/addCourse", "model", model);
		
	}
	
	
	
}
