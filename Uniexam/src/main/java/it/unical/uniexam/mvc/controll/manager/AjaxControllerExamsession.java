package it.unical.uniexam.mvc.controll.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("user")
@RequestMapping("manager/ajax")
public class AjaxControllerExamsession {
	
	@Autowired
	ManagerService managerService;
	
	@RequestMapping("/dialog/view_examsession")
	public ModelAndView dialog_view_appeal(HttpServletRequest request, Model model){

//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
//		}
//		if(user.getClass()!=Manager.class){
//			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return null;
		model.addAttribute("M",m);
		
		String idexamsession=request.getParameter("id");
		Long ides=Long.valueOf(idexamsession);
		ExamSession examsession=managerService.getExamsession(ides);
		model.addAttribute("examsession", examsession);
		
		return new ModelAndView("manager/dialog/view_examsession", "model", model);
	}
	
	
	@RequestMapping("/dialog/addSession")
	public ModelAndView dialog_add_session(@ModelAttribute("addsession") ExamSession examsession,HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
//		}
//		if(user.getClass()!=Manager.class){
//			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return null;
		model.addAttribute("M",m);
		
		Set<DegreeCourse> degreecourses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("degreecourses", degreecourses);
		
		Set<ExamSession> es=managerService.getExamSession();
		
		
		ExamSession session=new ExamSession(null, null, null, null);
		es.add(session);
		model.addAttribute("examsession", es);
		
		return new ModelAndView("manager/dialog/addSession", "model", model);
		
	}
	
	

	@RequestMapping("exam_session/sessionDetails")
	public String sessionDetails(HttpServletRequest request, Model model){
		
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return null;
		model.addAttribute("M",m);
		
		String idexamsession=request.getParameter("id");
		Long ides=Long.valueOf(idexamsession);
		ExamSession examsession=managerService.getExamsession(ides);
		model.addAttribute("examsession", examsession);
		
		return "manager/exam_session/sessionDetails";
	}
	
	@RequestMapping("/exam_session/remove_session")
	public String remove_session(HttpServletRequest request, Model model,HttpServletResponse response){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return null;
		model.addAttribute("M",m);
		
		String idsession=request.getParameter("id");
		Long idexamsession=Long.valueOf(idsession);

		Boolean res=managerService.removeExamsession(idexamsession);
		
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
	
	
	@RequestMapping("/exam_session/modify_session")
	public String modify_session(HttpServletRequest request, Model model,HttpServletResponse response){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return null;
		model.addAttribute("M",m);
		
		String idsession=request.getParameter("id");
		Long idexamsession=Long.valueOf(idsession);
		String variable=request.getParameter("variable");
		String value=request.getParameter("value");
		String clazz=request.getParameter("clazz");

		Boolean res=managerService.changeExamSessionField(idexamsession, variable, value, clazz);
		
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
	
	

}
