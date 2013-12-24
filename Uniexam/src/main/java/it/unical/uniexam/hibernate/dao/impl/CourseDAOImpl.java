package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */
public class CourseDAOImpl implements CourseDAO{

	@Override
	public Long addCourse(Long idDegreeCourse, String codeCourse,
			String nameCourse, Integer creditCourse, Long idProfessorHolder,
			Set<RequestedCourse> requestedCourses,URL webSite) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			/**
			 * will add degreeCourse
			 */
			Course c=new Course();
			c.setCode(codeCourse);
			c.setCredits(creditCourse);
			c.setName(nameCourse);
			Professor p=(Professor) session.get(Professor.class, idProfessorHolder);
			c.setHolder(p);
			c.setRequestedCourses(requestedCourses);
			c.setUrlWebSite(webSite);

			p.getHolder().add(c);
			id=(Long) session.save(c);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Set<Course> getCourses() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Query q= session.createQuery("from Course");
			@SuppressWarnings("unchecked")
			List<Course> list = q.list();
			res=new HashSet<Course>(list);
		}catch(Exception e){
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getCoursesFromDegreeCourse(Long idDegreeCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Query q= session.createQuery("from Course where underDegreeCourse=:par");
			q.setParameter("par", idDegreeCourse);
			@SuppressWarnings("unchecked")
			List<Course> list = q.list();
			res=new HashSet<Course>(list);
		}catch(Exception e){
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Course getCourse(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Course res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			res=c1;
		}catch(Exception e){
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Course removeCourse(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Course res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			session.delete(c1);
			transaction.commit();
			res=c1;
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public boolean addRequestedCourse(Long idCourse, Long idCourseRequested,Integer degree) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			Course c2=(Course) session.get(Course.class, idCourseRequested);
			if(c1!=null && c2!=null){
				RequestedCourse paramE=new RequestedCourse();
				paramE.setCourse(c2);
				paramE.setDegreeOfRequest(degree);
				c1.getRequestedCourses().add(paramE);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}

	@Override
	public RequestedCourse removeRequestedCourse(Long idCourse, Long idCourseRequested) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		RequestedCourse res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			if(c1!=null){
				for(RequestedCourse r : c1.getRequestedCourses()){
					if(r.getCourse().getId()==idCourseRequested){
						res=r;
						break;
					}
				}
				c1.getRequestedCourses().remove(res);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public boolean setHolderProfessor(Long idCourse, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			Professor p=(Professor) session.get(Professor.class, idProfessor);
			if(c1!=null && p!=null){
				c1.setHolder(p);
				p.getHolder().add(c1);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}
	
	@Override
	public boolean setHolderProfessor(Long idCourse, Professor professor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			if(c1!=null){
				c1.setHolder(professor);
				professor.getHolder().add(c1);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}

	@Override
	public Professor getHolderProfessor(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Professor res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			res=c1.getHolder();
		}catch(Exception e){
		}finally{
			session.close();
		}
		return res;
	}

}

//Session session =HibernateUtil.getSessionFactory().openSession();
//Transaction transaction=null;
//try{
//	transaction=session.beginTransaction();
//	
//	
//	transaction.commit();
//}catch(Exception e){
//	transaction.rollback();
//}finally{
//	session.close();
//}
//return null;