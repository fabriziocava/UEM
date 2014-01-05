package it.unical.uniexam.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import it.unical.uniexam.hibernate.dao.SessionDAO;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.mvc.service.UserService;
import it.unical.uniexam.mvc.service.UtilsService;


/**
 * 
 * @author luigi
 *
 */
public class UserServiceImpl implements UserService {

	@Autowired
	SessionDAO sessionDAO;
	
	@Override
	public Session getSessionIfItIs(String isSession, Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean checkSession(Session session, Long idUser) {
		if(idUser!=null){
			Session ses2=sessionDAO.getSession(idUser);
			if(ses2.getId().equals(session.getId())){
				if(ses2.getValid())
					return true;
			}
		}
		return false;
	}

}
