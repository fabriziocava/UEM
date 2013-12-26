package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ProfessorDAOImp implements ProfessorDAO {

	@Override
	public Long addProfessor(String name, String surname, URL webSite,
			String email, String password, Address address, Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			
			Professor p=new Professor(name, surname, webSite, email, password, address);
			/**
			 * Aggiungere il dipartimento di appartenenza se non nullo
			 */
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
	public Long addProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Professor> getProfessors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Professor> getProfessorsFromDepartment(Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor getProfessor(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor removeProfessor(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Professor> getSetProfessors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Professor> getSetProfessorsFromDepartment(Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addPhoneNumber(Long idProfessor, PhoneNumber number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePhoneNumber(Long idProfessor, Long idPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePhoneNumber(Long idProfessor, PhoneNumber idPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<PhoneNumber> getPhoneNumbers(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long setDepartmentAssociated(Long idProfessor, Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setDepartmentAssociated(Long idProfessor,
			Department department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Course> getCourseHolder(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Course> getCourseCommission(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Course> getSetCourseAsHolder(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Course>(p.getSetHoldersCourse());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getSetCourseAsCommission(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Course>(p.getSetAsCommission());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}


}
