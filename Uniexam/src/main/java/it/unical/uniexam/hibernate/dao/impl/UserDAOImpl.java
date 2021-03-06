package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Secretary;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;
import it.unical.uniexam.mvc.service.UtilsService;

/**
 * 
 * @author luigi
 * modified by fabrizio
 *
 */

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public User getUser(String email, String password,ArrayList<Object>result) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		User res=null;
		try{
			Query q=session.createQuery("from Email where email=:e");
			q.setParameter("e", email);
			Email e=(Email) q.uniqueResult();
			//			List<Email>u =q.list();
			//			Integer si=u.size();
			//			if(si>0){
			if(e!=null){
				//				Email e=u.get(0);
				if(e.getUser().getPassword().equals(password)){
					res=e.getUser();
				}else{
					result.add(2);//passwd
				}
			}else{
				result.add(1);// user
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean registerSession(String idSession, Long idUser) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction = session.beginTransaction();

			User u=(User)session.get(User.class, idUser);
			u.setSessionId(idSession);

			res=true;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public String getIdSession(Long idUser) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		String res=null;
		try{
			User u=(User)session.get(User.class, idUser);
			res=u.getSessionId();
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public User getUser(String idSession) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		User res=null;
		try{
			Query q=session.createQuery("from User where sessionId=:sID");
			q.setParameter("sID", idSession);
			res=(User)q.uniqueResult();
			//			List<User>li =q.list();
			//			if(li.size()==1){
			//				res=li.get(0);
			//			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean unRegisterSession(String idSession) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction = session.beginTransaction();

			Query q=session.createQuery("from User where sessionId=:sID");
			q.setParameter("sID", idSession);
			User user=(User)q.uniqueResult();
			if(user!=null){
				user.setSessionId(null);
				res=true;
			}
			//			List<User>li =q.list();
			//			if(li.size()==1){
			//				li.get(0).setSessionId(null);
			//				res=true;
			//			}

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public User getUser(Long idUser) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		User res=null;
		try{
			res=(User)session.get(User.class, idUser);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Map<String, String> getPersonalization(User user) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Map<String,String> res=new HashMap<String, String>();
		try{
			user=(User)session.get(User.class, user.getId());
			String perso=user.getPersonalizzation();
			res=UtilsService.getMapPersonalizzation(perso);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Map<String, String> getPersonalization(Long userId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Map<String,String> res=new HashMap<String, String>();
		try{
			User user=(User)session.get(User.class, userId);
			String perso=user.getPersonalizzation();
			res=UtilsService.getMapPersonalizzation(perso);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean updatePersonalizzation(Map<String, String> personalizzation,Long userId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction = session.beginTransaction();

			User user=(User)session.get(User.class, userId);
			String perso=user.getPersonalizzation();
			Map<String, String> mapPersonalizzation = UtilsService.getMapPersonalizzation(perso);
			mapPersonalizzation.putAll(personalizzation);
			user.setPersonalizzation(UtilsService.getStringPersonalizzation(mapPersonalizzation));

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean updatePersonalizzation(String personalizzation,Long userId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction = session.beginTransaction();
			//box-notify:left=10px%top=50px
			User user=(User)session.get(User.class, userId);
			String perso=user.getPersonalizzation();
			Map<String, String> mapPersonalizzation = UtilsService.getMapPersonalizzation(perso);
			Map<String, String> mapPersonalizzation2 = UtilsService.getMapPersonalizzation(personalizzation);
			mapPersonalizzation.putAll(mapPersonalizzation2);
			String queryPersonalizzation = UtilsService.getStringPersonalizzation(mapPersonalizzation);
			user.setPersonalizzation(queryPersonalizzation);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean resetPersonalizzation(Long userId) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction = session.beginTransaction();

			User user=(User)session.get(User.class, userId);
			user.setPersonalizzation(null);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
}
