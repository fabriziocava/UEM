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
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ProfessorDAOImp implements ProfessorDAO {

	@Override
	public Long addProfessor(String name,String surname,
			URL webSite,Set<Email> emails,String password,
			Address address,Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();

			Professor p=new Professor(name, surname, webSite, password, address);
			/**
			 * Le email se ci sono
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
			PhoneNumber removable=null;
			for (PhoneNumber e : p.getPhoneNumbers()) {
				if(e.getType().equals(number.getType())){
					removable=e;
				}
			}
			if(removable!=null){
				p.getPhoneNumbers().remove(removable);
				session.delete(removable);
			}
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
	//
	//	}

	@Override
	public Set<PhoneNumber> getPhoneNumbers(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<PhoneNumber> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<PhoneNumber>(p.getPhoneNumbers());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public PhoneNumber getPhoneNumber(Long idProfessor, String type) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		PhoneNumber res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (PhoneNumber pn : p.getPhoneNumbers()) {
				if(pn.getType().equals(type)){
					res=pn;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
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
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Course> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=p.getSetHoldersCourse();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getCourseCommission(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Course> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=p.getSetAsCommission();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
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

	@Override
	public Long addEmail(Long idProfessor, Email email) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Email removable=null;
			for (Email e : p.getEmails()) {
				if(e.getType().equals(email.getType())){
					removable=e;
				}
			}
			if(removable!=null){
				p.getEmails().remove(removable);
				session.delete(removable);
			}
			p.getEmails().add(email);
			res=(Long)session.save(email);

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
	public Email removeEmail(Long idProfessor, Long idEmail) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Email res=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Email email=(Email)session.get(Email.class, idEmail);
			p.getEmails().remove(email);
			session.delete(email);
			res=email;
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
	public Set<Email> getEmails(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Email> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Email>(p.getEmails());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Email getEmail(Long idProfessor, String type) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Email res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (Email email : p.getEmails()) {
				if(email.getType().equals(type)){
					res=email;
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

}
