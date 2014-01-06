package it.unical.uniexam.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.UserService;


/**
 * 
 * @author luigi
 *
 */
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public User getSession(String sessionId) {
		return userDAO.getUser(sessionId);
	}

	

}
