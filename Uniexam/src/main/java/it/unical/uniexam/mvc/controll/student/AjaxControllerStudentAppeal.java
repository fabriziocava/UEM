package it.unical.uniexam.mvc.controll.student;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unical.uniexam.DateFormat;
import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.StudentService;
import it.unical.uniexam.mvc.service.UtilsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@RequestMapping("student/ajax")
public class AjaxControllerStudentAppeal {

	@Autowired
	StudentService studentService;
	
	@RequestMapping("/dialog/view_appeal")
	public ModelAndView dialogViewAppeal(HttpServletRequest request, Model model) {
//		Student s = null;
//		String redirect = null;
//		ArrayList<Student> slist = new ArrayList<Student>();
//		redirect = setStudentOrRedirect(request, model, slist);
//		if(redirect!=null)
//			return redirect;
//		s = slist.get(0);
		
		Student s = (Student) request.getSession().getAttribute("user");
		if(s==null)
			return null;
		
		model.addAttribute("I",s);
				
		Long idCourse = Long.valueOf(request.getParameter("id"));
		ArrayList<Appeal> appeal = studentService.getAppeals(idCourse);
		model.addAttribute("appeal", appeal);
		
		Long idStudent = s.getId();
		ArrayList<AppealStudent> appealStudent = studentService.getAppealStudentList(idStudent);
		model.addAttribute("as", appealStudent);
				
		/*
		 * Request Courses
		 */
		Set<RequestedCourse> requestedCourses = studentService.getRequestCourse(idCourse);
		if(requestedCourses!=null && !requestedCourses.isEmpty()) {
			ArrayList<Carrier> carrier = studentService.getCarrier(idStudent);
			for(RequestedCourse r : requestedCourses) {
				if(!r.getPolicyOfRequested().equals(RequestedCourse.POLICY_STRONG))
					requestedCourses.remove(r);
				else {
					for(Carrier c : carrier) {
						if(c.getCourse().getId()==r.getCourse().getId())
							requestedCourses.remove(r);
					}
				}
			}
		}
		model.addAttribute("rc", requestedCourses);
		
		return new ModelAndView("student/dialog/view_appeal", "model", model);
	}
	
	@RequestMapping("/appeal/inscriptionToAppeal")
	public String inscribeToAppeal(HttpServletRequest request, Model model, HttpServletResponse response) {
//		Student s = null;
//		String redirect = null;
//		ArrayList<Student> slist = new ArrayList<Student>();
//		redirect = setStudentOrRedirect(request, model, slist);
//		if(redirect!=null)
//			return redirect;
//		s = slist.get(0);
		
		Student s = (Student) request.getSession().getAttribute("user");
		if(s==null)
			return null;
		
		model.addAttribute("I",s);

		Long idAppeal = Long.valueOf(request.getParameter("id"));
		Long idStudent = s.getId();
		Appeal appeal = studentService.getAppeal(idAppeal);
		
		/*
		 * Request Courses
		 */
		Set<RequestedCourse> requestedCourses = studentService.getRequestCourse(appeal.getCourse().getId());
		if(requestedCourses!=null && !requestedCourses.isEmpty()) {
			ArrayList<Carrier> carrier = studentService.getCarrier(idStudent);
			for(RequestedCourse r : requestedCourses) {
				if(!r.getPolicyOfRequested().equals(RequestedCourse.POLICY_STRONG))
					requestedCourses.remove(r);
				else {
					for(Carrier c : carrier) {
						if(c.getCourse().getId()==r.getCourse().getId())
							requestedCourses.remove(r);
					}
				}
			}
		}
		
		Date dateNow = new Date();
		GregorianCalendar gcNow = new GregorianCalendar(DateFormat.getYear(dateNow),
				 										DateFormat.getMonth(dateNow),
														DateFormat.getDay(dateNow),
														DateFormat.getHour(dateNow),
														DateFormat.getMinute(dateNow));
		GregorianCalendar gcOpenDate = new GregorianCalendar(DateFormat.getYear(appeal.getOpenDate()),
															 DateFormat.getMonth(appeal.getOpenDate()),
															 DateFormat.getDay(appeal.getOpenDate()),
															 DateFormat.getHour(appeal.getOpenDate()),
															 DateFormat.getMinute(appeal.getOpenDate()));
		GregorianCalendar gcCloseDate = new GregorianCalendar(DateFormat.getYear(appeal.getCloseDate()),
															  DateFormat.getMonth(appeal.getCloseDate()),
															  DateFormat.getDay(appeal.getCloseDate()),
															  DateFormat.getHour(appeal.getCloseDate()),
															  DateFormat.getMinute(appeal.getCloseDate()));
		
		Boolean res = false;
		if(appeal.getCurrNumberOfInscribed()+1<=appeal.getMaxNumberOfInscribed()) {
			if(gcOpenDate.before(gcNow) && gcNow.before(gcCloseDate)) {
				if(requestedCourses==null || requestedCourses.isEmpty())
					res = studentService.inscriptionToAppeal(idAppeal, s.getId());
			}
		}
		
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
	
	@RequestMapping("/appeal/removeInscription")
	public String removeInscription(HttpServletRequest request, Model model, HttpServletResponse response) {
//		Student s = null;
//		String redirect = null;
//		ArrayList<Student> slist = new ArrayList<Student>();
//		redirect = setStudentOrRedirect(request, model, slist);
//		if(redirect!=null)
//			return redirect;
//		s = slist.get(0);
		
		Student s = (Student) request.getSession().getAttribute("user");
		if(s==null)
			return null;
		
		model.addAttribute("I",s);

		Long idAppealStudent = Long.valueOf(request.getParameter("id"));
		AppealStudent appealStudent = studentService.getAppealStudent(idAppealStudent);
		Boolean res = false;
		try {
			Date dateNow = new Date();
			GregorianCalendar gcNow = new GregorianCalendar(DateFormat.getYear(dateNow),
					 										DateFormat.getMonth(dateNow),
															DateFormat.getDay(dateNow),
															DateFormat.getHour(dateNow),
															DateFormat.getMinute(dateNow));
			GregorianCalendar gcCloseDate = new GregorianCalendar(DateFormat.getYear(appealStudent.getAppeal().getCloseDate()),
					  											  DateFormat.getMonth(appealStudent.getAppeal().getCloseDate()),
					  											  DateFormat.getDay(appealStudent.getAppeal().getCloseDate()),
					  											  DateFormat.getHour(appealStudent.getAppeal().getCloseDate()),
					  											  DateFormat.getMinute(appealStudent.getAppeal().getCloseDate()));
		
			if(gcNow.before(gcCloseDate))
				if(appealStudent.getTemporany_vote()==null)
					res = studentService.removeInscriptionToAppeal(idAppealStudent);
		} catch (Exception e) {
			new MokException(e);
		}
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
