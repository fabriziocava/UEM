package it.unical.uniexam.mvc.controll.professor;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.Event;
import it.unical.uniexam.hibernate.domain.utility.EventsCalendar;
import it.unical.uniexam.mvc.service.ProfessorService;
import it.unical.uniexam.mvc.service.UtilsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
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
@RequestMapping("professor/ajax/calendar")
public class AjaxControllerCalendar {

	@Autowired
	ProfessorService professorService;

	@RequestMapping("/save")
	public String autocomplete_student(HttpServletRequest request, Model model,HttpServletResponse response){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return redirect;
		p=plist.get(0);

		
		String title=request.getParameter("title");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String allDay=request.getParameter("allDay");
		
		Event e=new Event(title, start, end, allDay);
		EventsCalendar events=null;
//				events=professorService.getEventsFromProfessor(p.getId());
		if(events==null)
			events=new EventsCalendar();
		events.getEvents().add(e);
		Boolean res=professorService.setEventsByProfessor(p.getId(),events);

		ServletOutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			if(res)
				outputStream.println("ok");
			else
				outputStream.println("no");
			outputStream.flush();
			outputStream.close();
		} catch (Exception ex) {
			new MokException(ex);
		}
		return null;
	}
	
	@RequestMapping("/remove")
	public ModelAndView declassify_students(HttpServletRequest request, Model model,HttpServletResponse response){
		Professor p=null;
		String redirect=null;
		ArrayList<Professor>plist=new ArrayList<Professor>();
		redirect=setProfessorOrRedirect(request,model,plist);
		if(redirect!=null)
			return new ModelAndView(redirect);
		p=plist.get(0);

		Boolean res=false;
		EventsCalendar events=professorService.getEventsFromProfessor(p.getId());
		if(events==null)
			res=false;
		else{
			String title=request.getParameter("title");
			String start=request.getParameter("start");
			String end=request.getParameter("end");
			
			Event remove=new Event(title,start,end,null);
			if(events.getEvents().contains(remove)){
				events.getEvents().remove(remove);
			}
			res=professorService.setEventsByProfessor(p.getId(), events);
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