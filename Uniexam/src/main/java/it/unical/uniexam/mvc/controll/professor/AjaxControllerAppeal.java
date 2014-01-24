package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
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
public class AjaxControllerAppeal {

	@Autowired
	ProfessorService professorService;

	@RequestMapping("/appeal/modify_appeal_student")
	public String modify_appeal_student(HttpServletRequest request, Model model,HttpServletResponse response){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		
		String idAppea=request.getParameter("id");
		Long idAppeal=Long.valueOf(idAppea);
		String variable=request.getParameter("variable");
		String value=request.getParameter("value");
//		String clazz=request.getParameter("clazz");
//		clazz=clazz+".class";
		
		Boolean res=professorService.modifyAppealStudent(idAppeal,variable,value);
		
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
	
	
	@RequestMapping("/appeal/remove_appeal")
	public String remove_appeal(HttpServletRequest request, Model model,HttpServletResponse response){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idAppea=request.getParameter("id");
		Long idAppeal=Long.valueOf(idAppea);

		Boolean res=professorService.removeAppeal(idAppeal);
		
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
	
	@RequestMapping("/appeal/appeal_details")
	public String appeal_details(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);
		String idAppea=request.getParameter("id");
		Long idAppeal=Long.valueOf(idAppea);
		Appeal appeal=professorService.getAppealDetails(idAppeal);
		model.addAttribute("appeal", appeal);
		return "professor/appeal/appeal_details";
	}
	
	@RequestMapping("/appeal/modify_appeal")
	public String modify_appeal(HttpServletRequest request, Model model,HttpServletResponse response){
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
		
		String idAppea=request.getParameter("id");
		Long idAppeal=Long.valueOf(idAppea);
		String variable=request.getParameter("variable");
		String value=request.getParameter("value");
		String clazz=request.getParameter("clazz");
//		clazz=clazz+".class";
		
		Boolean res=professorService.changeAppealAttribute(idAppeal,variable,value,clazz);
		
		
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
	
//	/ajax/course/course_details/dialog

	@RequestMapping("/dialog/add_appeal")
	public ModelAndView dialog_add_appeal(@ModelAttribute("appeal") Appeal appeal,HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		p=plist.get(0);
		
		List<Course> courses=professorService.getCourseAssociated(p.getId());
		Course course = new Course(null, "NO", null, null, null, null, null,null);
		course.setId(-1L);
		courses.add(0, course);
		model.addAttribute("courses", courses);
		
		return new ModelAndView("professor/dialog/add_appeal", "model", model);
	}
	
	@RequestMapping("/dialog/view_appeal")
	public ModelAndView dialog_view_appeal(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		p=plist.get(0);

		String idApp=request.getParameter("id");
		Long idAppeal=Long.valueOf(idApp);
		Appeal appeal=professorService.getAppealDetails(idAppeal);
		model.addAttribute("appeal", appeal);
		
		return new ModelAndView("professor/dialog/view_appeal", "model", model);
	}
	
	@RequestMapping("/dialog/list_student")
	public ModelAndView dialog_list_student(HttpServletRequest request, Model model){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		p=plist.get(0);

		String idAppea=(String)request.getParameter("id");
		if(idAppea!=null){
			Long idAppeal=Long.valueOf(idAppea);
			ArrayList<ArrayList<Object>> students=professorService.getListStudentFromAppealRegularAndNot(idAppeal);
//			ArrayList<ArrayList<RequestedCourse>>requestedCourses=professorService.getListOfRequestedCourseFromListStudentAndAppeal(idAppeal,students.get(1));
			Appeal appeal=professorService.getAppealGround(idAppeal);
			model.addAttribute("appeal", appeal);
			model.addAttribute("appealstudentsRegAndNot", students);
//			model.addAttribute("requestedCourses", requestedCourses);
		}
		//devono viaggiare insieme
//		requestedCourses  			  ArrayList<ArrayList<RequestedCourse>>requestedCourses
//		appealstudentsNoRegular		  ArrayList<AppealStudent> appealStudentsNoRegular
		return new ModelAndView("professor/dialog/list_student", "model", model);
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