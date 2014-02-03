package it.unical.uniexam.mvc.controll.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.ManagerService;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UserService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
public class ManagerController {

	@Autowired
	ManagerService managerService;
	
	
	@RequestMapping(value=ManagerService.MANAGER_HOME, method=RequestMethod.GET)
	public String home(HttpServletRequest request,Model model){
		
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
		
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		model.addAttribute("M",m);
		
		return ManagerService.MANAGER_HOME;
	}
	
	
	@RequestMapping(value=ManagerService.MANAGER_ACCOUNT, method=RequestMethod.GET)
	public String account(HttpServletRequest request,HttpServletResponse response,Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;

		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		return ManagerService.MANAGER_ACCOUNT;
	}
	
	
	@RequestMapping(value=ManagerService.MANAGER_EXAM , method=RequestMethod.GET)
	public String exam(HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;

		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		// aggiungere altre cose
		
		Set<DegreeCourse> courses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("courses", courses);
		
		Set<ExamSession> es=managerService.getExamSession();
		model.addAttribute("examsession", es);

		
		return ManagerService.MANAGER_EXAM;
	}
	
	@RequestMapping(value=ManagerService.MANAGER_ORDINAMENTO , method=RequestMethod.GET)
	public String ordinamento(HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;

		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		// aggiungere altre cose
		
		Set<DegreeCourse> courses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("courses", courses);

		ArrayList<Course> c=managerService.getCourses();
		model.addAttribute("c", c);
		
		

		
		return ManagerService.MANAGER_ORDINAMENTO;
	}
	
	
	@RequestMapping(value=ManagerService.MANAGER_COURSE , method=RequestMethod.GET)
	public String course(HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;

		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		// aggiungere altre cose
	//	Set<DegreeCourse> courses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
	//	model.addAttribute("courses", courses);

		return ManagerService.MANAGER_COURSE;
	}
	
	@RequestMapping(value=ManagerService.MANAGER_COURSELIST , method=RequestMethod.GET)
	public String courselist(HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;

		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		// aggiungere altre cose
		Set<DegreeCourse> courses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("courses", courses);

		ArrayList<Course> c=managerService.getCourses();
		model.addAttribute("c", c);
		
		return ManagerService.MANAGER_COURSELIST;
	}
	
	@RequestMapping(value=ManagerService.MANAGER_ASSIGNCOURSE , method=RequestMethod.GET)
	public String assigncourse(HttpServletRequest request, Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;

		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		// aggiungere altre cose
		Set<DegreeCourse> courses=managerService.getAssociatedCourseWithDepartment(m.getDepartmentAssociated());
		model.addAttribute("courses", courses);

		ArrayList<Course> c=managerService.getCourses();
		model.addAttribute("c", c);
		
		return ManagerService.MANAGER_ASSIGNCOURSE;
	}
	
	@RequestMapping(value="manager/addRequestedCourseAction", method=RequestMethod.POST)
	public ModelAndView dialog_add_requested_course_action(@ModelAttribute("requestedCourse") RequestedCourse requestedCourse,
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
//		}
//		if(user.getClass()!=Manager.class){
//			return new ModelAndView(UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return new ModelAndView(UtilsService.LOGIN);
		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		
		String idCours=request.getParameter("idCourse");
		
		Long idCourse=Long.valueOf(idCours);
		
		Boolean ris=managerService.addRequestedCourse(idCourse, requestedCourse);
		
		return new ModelAndView("redirect:/manager/ajax/dialog/requested_course?id="+idCourse+"&ris="+ris, "model", model);
	}
	
	
	@RequestMapping(value="manager/assegnaCorsoAction", method=RequestMethod.POST)
	public String assegnaCorsoaction(
			HttpServletRequest request, Model model,HttpServletResponse response) throws IOException{
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return (UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model));
//		}
//		if(user.getClass()!=Manager.class){
//			return (UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model));
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		model.addAttribute("M",m);
		updatePersonalizzation(model, m);
		
		String idCours=request.getParameter("idCourse");
		
		Long idCourse=Long.valueOf(idCours);

		String idprofessor=request.getParameter("idprofessor");
		Long idprof=Long.valueOf(idprofessor);
		
		Boolean ris=managerService.setHolderProfessor(idCourse, idprof);
		
		return managerService.MANAGER_COURSE;
	}
	
	
	
	@RequestMapping(value=ManagerService.MANAGER_UPLOAD, method=RequestMethod.POST)
	public String uploadImageProfile(MultipartHttpServletRequest requestM,HttpServletRequest request,Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;
		MultipartFile file=requestM.getFile("file");
		if (!file.isEmpty()) {
			try {
				managerService.putImage(m, file.getInputStream(),(int)file.getSize());
			} catch (Exception e) {
				new MokException(e);
			}
		} else {
			//            return "You failed to upload because the file was empty.";
		}
		return ManagerService.MANAGER_ACCOUNT;
	}
	
	
	@RequestMapping(value=ManagerService.MANAGER_IMAGE, method=RequestMethod.GET)
	public String getImage(HttpServletRequest request,HttpServletResponse response,Model model){
//		User user=managerService.getSession(request.getSession().getId());
//		if(user==null){
//			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta", "sessione", model);
//		}
//		if(user.getClass()!=Manager.class){
//			return UtilsService.redirectToErrorPageGeneral("Errore Utente non riconosciuto", "Classe Utente", model);
//		}
//		Manager m=(Manager)user;
		Manager m=(Manager)request.getSession().getAttribute("user");
		if(m==null) return UtilsService.LOGIN;

		//		String name=request.getParameter("idImage");
		try {
			//			response.setContentType("image/jpeg");
			if(managerService.streamImage(m,response.getOutputStream())){
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

	
	private void updatePersonalizzation(Model model, Manager m) {
		Map<String, String> personalizzationMap=managerService.getPersonalizzationValues(m.getId());
		model.addAttribute("personalizzationMap", personalizzationMap);
	}
	
}
