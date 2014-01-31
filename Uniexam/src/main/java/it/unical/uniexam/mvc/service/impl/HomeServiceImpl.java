package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.mvc.service.HomeService;

/**
 * 
 * @author luigi
 *
 */
@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	UserDAO userDAO;
	
	
	@Override
	public ArrayList<Object> loginUser(String email, String password) {
		ArrayList<Object>result=new ArrayList<Object>();
		if(email==null || password==null || email.length()<6 || password.length()<1){
			result.add(ERROR_USER); // non dovrebbe accadere poichè abbiamo dei controlli anche sul client
			return result;
		}
		User u=userDAO.getUser(email, password,result);
		if(u!=null){
			result.add(NO_ERROR);
//			result.add(u.getType());
			result.add(u);
		}
		return result;
	}


	@Override
	public void registerSession(String idSession, Long idUser) {
		userDAO.registerSession(idSession, idUser);
	}


	@Override
	public Boolean unRegisterSession(String idSession) {
		return userDAO.unRegisterSession(idSession);
	}

//	@Autowired
//	SessionDAO sessionDAO=new SessionDAOImpl();
//
//	@Override
//	public ArrayList<Object> loginUser(String email, String password) {
//		//		return sessionDAO.getSessionFrom(email, password);
//		Long idUser=sessionDAO.isUser(email);
//		ArrayList<Object>result=new ArrayList<Object>();
//		if(idUser!=null){
//			Session s=sessionDAO.getSessionLogin(idUser, password);
//			if(s!=null){
//				result.add(NO_ERROR);
//				result.add(s);
//				return result;
//			}
//			result.add(ERROR_PASSWD);
//			return result;
//		}else{
//			result.add(ERROR_USER);
//			return result;
//		}
//	}

}
