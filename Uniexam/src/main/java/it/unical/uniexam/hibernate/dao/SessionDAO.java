package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Session;

import java.util.HashMap;

public interface SessionDAO {

	public Long addSession(Long user,Long timeExpire,HashMap<String, Object>values);
	public Session getSession(Long idUser);
	public Session removeSession(Long idSession);
	
	
	public void invalidSession(Long idSession);
	public Object getAttribute(Long idSession,String name);
	public HashMap<String, Object> getAttributes(Long idSession);
	
	public Session setAttribute(String name,Object value);
	

}
