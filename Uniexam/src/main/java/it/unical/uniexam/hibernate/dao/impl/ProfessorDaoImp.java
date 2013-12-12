package it.unical.uniexam.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.ProfessorDao;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ProfessorDaoImp implements ProfessorDao {

	@Override
	public Long addProfessor(String name, String surname) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			
			Professor p=new Professor();
			p.setName(name);
			p.setSurname(surname);
			
			id=(Long) session.save(p);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		
		return id;
	}

}
