package it.unical.uniexam.mvc.service;

import org.springframework.web.servlet.ModelAndView;

import it.unical.uniexam.hibernate.domain.Session;

public interface UserService {
	
	public Session getSessionIfItIs(String isSession,Long idUser);
	public Boolean checkSession(Session session,Long idUser);
	
}
