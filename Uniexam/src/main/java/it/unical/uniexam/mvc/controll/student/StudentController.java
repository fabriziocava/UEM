package it.unical.uniexam.mvc.controll.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.mvc.service.StudentService;
import it.unical.uniexam.mvc.service.UtilsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class StudentController {
	
	@Autowired StudentService studentService;
	
	@RequestMapping(value=StudentService.STUDENT_HOME, method=RequestMethod.GET)
	public String homeStudent(HttpServletRequest request,Model model){			
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
		model.addAttribute("I",s);
		
		Long idStudent = s.getId();
		Set<Group> groups = studentService.getGroups();
		ArrayList<AppealStudent> appealStudent = studentService.getAppealStudentList(idStudent);
		ArrayList<AppealStudent> appealStudentForVerbalToBeSigned = studentService.getAppealStudentForVerbalToBeSigned(idStudent);
		
		ArrayList<String> news = new ArrayList<String>();
		List<PostOfGroup> postOfGroup = null;
		try {
			for(Group g : groups) {
				postOfGroup = g.getPosts();
				if(postOfGroup!=null && !postOfGroup.isEmpty())
					news.add("Group "+g.getName()+": "+postOfGroup.get(0).getDate_of_post()+" "+postOfGroup.get(0).getPost());
			}
		} catch (Exception e) {
			
		}
		try {
			for(AppealStudent as : appealStudent) {
				if(as.getTemporany_vote()!=null)
					news.add(as.getAppeal().getCourse().getName().toUpperCase()+" - Appello del "+as.getAppeal().getExamDate()+": e' presente un voto provvisorio.");
			}
		} catch (Exception e) {
			
		}
		try {
			for(AppealStudent asFVTB : appealStudentForVerbalToBeSigned) {
				news.add(asFVTB.getAppeal().getCourse().getName().toUpperCase()+": � in attesa di essere firmato dallo studente.");
			}
			model.addAttribute("news", news);
		} catch (Exception e) {
			
		}
		return StudentService.STUDENT_HOME;
	}
	
	@RequestMapping(value=StudentService.STUDENT_COURSE, method=RequestMethod.GET)
	public String course(HttpServletRequest request, Model model) {
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
		model.addAttribute("I",s);
		
		Long idStudent = s.getId();
		ArrayList<Course> courses = studentService.getCourses(idStudent);
		model.addAttribute("courses", courses);
		
		return StudentService.STUDENT_COURSE;
	}
	
	@RequestMapping(value=StudentService.STUDENT_GROUP, method=RequestMethod.GET)
	public String group(HttpServletRequest request, Model model) {
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
		model.addAttribute("I",s);
		
		Set<Group> groups = studentService.getGroups();
		model.addAttribute("groups", groups);
		
		return StudentService.STUDENT_GROUP;
	}

	
	@RequestMapping(value=StudentService.STUDENT_CARRIER, method=RequestMethod.GET)
	public String carrier(HttpServletRequest request, Model model) {
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
		model.addAttribute("I",s);
		
		Long idStudent = s.getId();
		ArrayList<AppealStudent> appealStudent = studentService.getAppealStudentForCarrier(idStudent);
		model.addAttribute("as", appealStudent);
		ArrayList<Carrier> carrier = studentService.getCarrier(idStudent);
		model.addAttribute("carrier", carrier);
		
		return StudentService.STUDENT_CARRIER;
	}

	@RequestMapping(value=StudentService.STUDENT_VERBALTOBESIGNED, method=RequestMethod.GET)
	public String verbalToBeSigned(HttpServletRequest request, Model model) {
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
		
		model.addAttribute("I",s);
		
		Long idStudent = s.getId();
		ArrayList<AppealStudent> appealStudent = studentService.getAppealStudentForVerbalToBeSigned(idStudent);
		model.addAttribute("as", appealStudent);
		
		return StudentService.STUDENT_VERBALTOBESIGNED;
	}
	
	@RequestMapping(value=StudentService.STUDENT_VERBALTOBESIGNED+"/sign", method=RequestMethod.POST)
	String signVerbal(HttpServletRequest request, Model model) {
		Student s = null;
		String redirect = null;
		ArrayList<Student> slist = new ArrayList<Student>();
		redirect = setStudentOrRedirect(request, model, slist);
		if(redirect!=null)
			return redirect;
		s = slist.get(0);
	
		model.addAttribute("I",s);
		
		String password = request.getParameter("password");
		if(s.getPassword().equals(password)) {
			Long idStudent = s.getId();
			ArrayList<AppealStudent> appealStudent = studentService.getAppealStudentForVerbalToBeSigned(idStudent);
			Long idAppealStudent = null;
			ArrayList<Long> idAppealStudentList = new ArrayList<Long>();
			for(AppealStudent as : appealStudent) {
				try {
					idAppealStudent = Long.valueOf(request.getParameter("appealStudent"+as.getId()));
					if(idAppealStudent==as.getId()) {
						idAppealStudentList.add(as.getId());
					}
				} catch (Exception e) {
					
				}
			}
			studentService.setStateAppealStudent(idAppealStudentList);
			return carrier(request, model);
		}
		else {
			Boolean error = true;
			model.addAttribute("error", error);
			return verbalToBeSigned(request, model);
		}
	}
	
	
	String setStudentOrRedirect(HttpServletRequest request,Model model, ArrayList<Student> slist) {
		User user=studentService.getSession(request.getSession().getId());
		if(user==null){
			HttpSession session = request.getSession(false);
			if(session!=null){
				session.invalidate();
			}
			return UtilsService.redirectToErrorPageGeneral("Sessione scaduta Error code 1", "sessione", model);
		}
		if(user.getClass()!=Student.class){
			return UtilsService.redirectToErrorPageGeneral("Errore, Utente non riconosciuto", "Classe Utente", model);
		}
		slist.add((Student)user);
		return null;
	}
	
}
