package it.unical.uniexam.mvc.service.impl;

import org.springframework.web.servlet.ModelAndView;

import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.mvc.service.UserService;
import it.unical.uniexam.mvc.service.UtilsService;

public class UserServiceImpl implements UserService {

	@Override
	public Session getSessionIfItIs(String isSession, Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView redirectToErrorPageUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView redirectToErrorPagePasswd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectToErrorPageSession() {
		return UtilsService.SESSION_ERROR;
	}

	@Override
	public Boolean checkSession(Session session, Long idUser) {
		// TODO Auto-generated method stub
		return true;
	}

}
