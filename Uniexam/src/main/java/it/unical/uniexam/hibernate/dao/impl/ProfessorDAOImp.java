package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sun.awt.image.PNGImageDecoder.PNGException;
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
			 * if(p.getDepartment_associated()!=null){do}
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
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();

			/**
			 * Aggiungere il dipartimento di appartenenza se non nullo
			 * if(professor.getDepartment_associated()!=null){do}
			 */
			id=(Long) session.save(professor);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Set<Professor> getProfessors() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Professor>res=null;
		try{
			Query q= session.createQuery("from Professor");
			@SuppressWarnings("unchecked")
			List<Professor> list = q.list();
			res=new HashSet<Professor>(list);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Professor> getProfessorsFromDepartment(Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Professor>res=null;
		try{
			Query q= session.createQuery("from Professor where department_associated=:par");
			q.setParameter("par", idDepartment);
			@SuppressWarnings("unchecked")
			List<Professor> list = q.list();
			res=new HashSet<Professor>(list);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Professor getProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Professor res=null;
		try{
			res=(Professor)session.get(Professor.class, idProfessor);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Professor removeProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Professor res=null;
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			res=(Professor)session.get(Professor.class, idProfessor);
			session.delete(res);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

//	@Override
//	public Set<Professor> getSetProfessors() {
//		Session session =HibernateUtil.getSessionFactory().openSession();
//		Set<Professor>res=null;
//		try{
//			Query q= session.createQuery("from Professor");
//			@SuppressWarnings("unchecked")
//			List<Professor> list = q.list();
//			res=new HashSet<Professor>(list);
//		}catch(Exception e){
//			e.printStackTrace();
//		}finally{
//			session.close();
//		}
//		return res;
//	}

//	@Override
//	public Set<Professor> getSetProfessorsFromDepartment(Long idDepartment) {
//		return null;
//	}

	@Override
	public Long addPhoneNumber(Long idProfessor, PhoneNumber number) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();
			
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			p.getPhoneNumbers().add(number);
			res=(Long)session.save(number);
			
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
	public PhoneNumber removePhoneNumber(Long idProfessor, Long idPhoneNumber) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		PhoneNumber res=null;
		try{
			transaction = session.beginTransaction();
			
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			PhoneNumber pn=(PhoneNumber)session.get(PhoneNumber.class, idPhoneNumber);
			p.getPhoneNumbers().remove(pn);
			session.delete(pn);
			res=pn;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

//	@Override
//	public void removePhoneNumber(Long idProfessor, PhoneNumber idPhoneNumber) {
//		// TODO Auto-generated method stub
//
//	}

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
