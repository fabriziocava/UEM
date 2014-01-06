package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.domain.User;

public interface UserService {
	
//	public Session getSessionIfItIs(String isSession,Long idUser);
	public User getSession(String sessionId);
	
}
