package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.uniexam.hibernate.dao.SessionDAO;
import it.unical.uniexam.hibernate.dao.impl.SessionDAOImpl;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.mvc.service.HomeService;

/**
 * 
 * @author luigi
 *
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	SessionDAO sessionDAO=new SessionDAOImpl();

	@Override
	public ArrayList<Object> loginUser(String email, String password) {
		//		return sessionDAO.getSessionFrom(email, password);
		Long idUser=sessionDAO.isUser(email);
		ArrayList<Object>result=new ArrayList<Object>();
		if(idUser!=null){
			Session s=sessionDAO.getSessionLogin(idUser, password);
			if(s!=null){
				result.add(NO_ERROR);
				result.add(s);
				return result;
			}
			result.add(ERROR_PASSWD);
			return result;
		}else{
			result.add(ERROR_USER);
			return result;
		}
	}

}
