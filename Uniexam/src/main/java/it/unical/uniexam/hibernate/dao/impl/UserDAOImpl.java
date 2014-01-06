package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
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
			List<Email>u =q.list();
			Integer si=u.size();
			if(si>0){
				Email e=u.get(0);
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
			List<User>li =q.list();
			if(li.size()==1){
				res=li.get(0);
			}
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
			List<User>li =q.list();
			if(li.size()==1){
				li.get(0).setSessionId(null);
				res=true;
			}

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

}
