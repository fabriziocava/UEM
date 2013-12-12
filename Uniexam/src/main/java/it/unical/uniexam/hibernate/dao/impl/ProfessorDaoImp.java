package it.unical.uniexam.hibernate.dao.impl;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.ProfessorDao;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ProfessorDaoImp implements ProfessorDao {

	@Override
	public Long addProfessor(String name, String surname,Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			
			Professor p=new Professor();
			p.setName(name);
			p.setSurname(surname);
			
			Department d=(Department) session.get(Department.class, idDepartment);
			p.setDepartment(d);
			d.addProfessor(p);
			id=(Long) session.save(p);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		
		return id;
	}

	@Override
	public Set listProfessors() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		Set<Professor> p=null;
		try{
			transaction=session.beginTransaction();
			
			Query q=session.createQuery("from Professor");
			p=(Set<Professor>) q.list();
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return p;
	}

}
