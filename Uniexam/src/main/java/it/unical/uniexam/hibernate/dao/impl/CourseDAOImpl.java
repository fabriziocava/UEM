package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class CourseDAOImpl implements CourseDAO{

	@Override
	public Long addCourse(Long idDegreeCourse, String codeCourse,
			String nameCourse, Integer creditCourse, Long idProfessorHolder,
			Set<RequestedCourse> requestedCourses) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			Course c=new Course();
			c.setCode(codeCourse);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public Set<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Course> getCoursesFromDegreeCourse(Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course getCourseFromId(Long idCourse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course removeCourse(Long idCourse) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRequestedCourse(Long idCourse, Long idCourseRequested) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRequestedCourse(Long idCourse, Long idCourseRequested) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHolderProfessor(Long idCourse, Long idProfessor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getHolderProfessor(Long idCourse) {
		// TODO Auto-generated method stub
		return null;
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