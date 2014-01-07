package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */
@Repository
public class AppealDAOImpl implements AppealDAO {

	@Deprecated
	@Override
	public Long addAppeal(Appeal appeal) {
		return null;
	}

	@Deprecated
	@Override
	public Long addAppeal(Course course, String name,
			Integer maxNumberOfInscribed, String location, Date examDate,
			Date openDate, Date closeDate, Professor creatorProfessor) {
		return null;
	}

	@Override
	public Long addAppeal(Long idCourse, String name,
			Integer maxNumberOfInscribed, String location, Date examDate,
			Date openDate, Date closeDate, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();

			Course course=(Course)session.get(Course.class, idCourse);
			Professor creatorProfessor=(Professor)session.get(Professor.class, idProfessor);
			Appeal a=new Appeal(course, name, maxNumberOfInscribed, location, examDate, openDate, closeDate, creatorProfessor);
			creatorProfessor.getAppeals().add(a);
			id=(Long)session.save(a);

			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Appeal removeAppeal(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();
			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			Professor p=a.getCreatorProfessor();
			p.getAppeals().remove(a);
			session.delete(a);
			res=a;

			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Deprecated
	@Override
	public Appeal removeAppeal(Appeal appeal) {
		return null;
	}

	@Override
	public Set<Appeal> removeAllAppealsFromProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Set<Appeal> res=null;
		try{
			transaction=session.beginTransaction();
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			if(p.getAppeals()!=null && p.getAppeals().size()>0){
				res=new HashSet<Appeal>(p.getAppeals());
				for (Appeal appeal : res) {
					session.delete(appeal);
				}
			}
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Appeal modifyAppeal(Long idAppeal, Appeal appealNew) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();
			Appeal old=(Appeal)session.get(Appeal.class, idAppeal);
			if(appealNew.getCloseDate()!=null && appealNew.getCloseDate().compareTo(old.getCloseDate())!=0)
				old.setCloseDate(appealNew.getCloseDate());
			if(appealNew.getCourse()!=null && !appealNew.getCourse().equals(old.getCourse()))
				old.setCourse(appealNew.getCourse());
			if(appealNew.getExamDate()!=null && appealNew.getExamDate().compareTo(old.getExamDate())!=0)
				old.setExamDate(appealNew.getExamDate());
			if(appealNew.getLocation()!=null && !appealNew.getLocation().equals(old.getLocation()))
				old.setLocation(appealNew.getLocation());
			if(appealNew.getMaxNumberOfInscribed()!=null && appealNew.getMaxNumberOfInscribed()!=old.getMaxNumberOfInscribed())
				old.setMaxNumberOfInscribed(appealNew.getMaxNumberOfInscribed());
			if(appealNew.getName()!=null && !appealNew.getName().equals(old.getName()))
				old.setName(appealNew.getName());
			if(appealNew.getOpenDate()!=null && appealNew.getOpenDate().compareTo(old.getOpenDate())!=0)
				old.setOpenDate(appealNew.getOpenDate());
			if(appealNew.getStudentsInscribed()!=null && appealNew.getStudentsInscribed().size()>0)
				old.setStudentsInscribed(appealNew.getStudentsInscribed());
			res=old;
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Appeal> getAppealsFromProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Appeal> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Appeal>(p.getAppeals());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Student> getStudetsIscribed(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Student> res=null;
		try{
			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			res=new HashSet<Student>(a.getStudentsInscribed());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Deprecated
	@Override
	public Appeal setProfessor(Long idAppeal,Professor professor) {
		return null;
	}

	@Override
	public Appeal setProfessor(Long idAppeal,Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();

			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			Professor creatorProfessor =(Professor)session.get(Professor.class, idProfessor);
			a.setCreatorProfessor(creatorProfessor);
			res=a;
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Appeal addStudentAtAppeal(Long idAppeal, Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();

			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			Student s=(Student)session.get(Student.class, idStudent);
			a.getStudentsInscribed().add(s);
			res=a;
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Deprecated
	@Override
	public Appeal addStudentAtAppeal(Long idAppeal, Student student) {
		return null;
	}

	@Override
	public boolean removeStudentAtAppeal(Long idAppeal, Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction=session.beginTransaction();
			/**
			 * TODO
			 * vedere cosa fare su studente
			 */
			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			Student s=(Student)session.get(Student.class, idStudent);
			if(a.getStudentsInscribed().contains(s)){
				a.getStudentsInscribed().remove(s);
			}
			transaction.commit();
			ok=true;
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		if(ok)return true;
		else return false;
	}

	@Deprecated
	@Override
	public boolean removeStudentAtAppeal(Long idAppeal, Student student) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Appeal> getAppeals() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Appeal> res=null;
		try{
			Query q= session.createQuery("from Appeal");
			res=new HashSet<Appeal>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

}
