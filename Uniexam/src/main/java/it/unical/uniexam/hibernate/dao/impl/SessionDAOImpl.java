package it.unical.uniexam.hibernate.dao.impl;

import java.util.HashMap;

import javax.persistence.ManyToOne;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.SessionDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class SessionDAOImpl implements SessionDAO {

	@Override
	public Long addSession(Long user,Long timeExpire,HashMap<String, Object>values) {
		Session session =HibernateUtil.getSessionFactory().openSession();
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
					it.unical.uniexam.hibernate.domain.Session session2 = new it.unical.uniexam.hibernate.domain.Session();
					p.setSession(session2);
					session2.setOwner(p.getId());
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
