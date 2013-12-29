package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.HashMap;

import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.SessionDAO;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class SessionDAOImpl implements SessionDAO {

	@Override
	public Long addSession(Long user,Long timeExpire,HashMap<String, Object>attributes) {
		org.hibernate.Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			try{
				Manager m=(Manager)session.get(Manager.class, user);
				m.getName();
				
			}catch(Exception e){
				try{
					Professor p=(Professor)session.get(Professor.class, user);
					p.getName();
					Session session2 = new Session();
					p.setSession(session2);
					session2.setOwner(p.getId());
					session2.setType(User.TYPE.PROFESSOR);
					Date now = new Date();
					session2.setCreated(now);
					session2.setValid(true);
					session2.setExpire(new Date(now.getTime()+((timeExpire!=null)?timeExpire:Session.timeExpire)));
					/**
					 * creare funzione che serializzi gli attributi nel DB
					 */
				}catch(Exception e2){

				}
			}

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public it.unical.uniexam.hibernate.domain.Session getSession(Long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public it.unical.uniexam.hibernate.domain.Session removeSession(
			Long idSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidSession(Long idSession) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getAttribute(Long idSession, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Object> getAttributes(Long idSession) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public it.unical.uniexam.hibernate.domain.Session setAttribute(String name,
			Object value) {
		// TODO Auto-generated method stub
		return null;
	}


}
