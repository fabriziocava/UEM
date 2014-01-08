package it.unical.uniexam.hibernate.dao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;

public interface CourseDAO {
	/**
	 * Standard method
	 */
	
	/**
	 * Adding a Course and return the your id
	 */
	public Long addCourse(Long idDegreeCourse,String codeCourse,String nameCourse,
			Integer creditCourse,Long idProfessorHolder,
			Set<RequestedCourse>requestedCourses,URL webSite);
	
	public Long addCourse(Course course);
	
	public Set<Course> getCourses();
	public Set<Course> getCoursesFromDegreeCourse(Long idDegreeCourse);
	public Course getCourse(Long idCourse);
	public Course removeCourse(Long idCourse);

	/**
	 * Advanced method 
	 */
	
	/**
	 * Adding a requested course a one course
	 */
	public boolean addRequestedCourse(Long idCourse,Long idCourseRequested,Integer degree);
	public RequestedCourse removeRequestedCourse(Long idCourse,Long idCourseRequested);
//	public void modifyRequestedCourse(Long idCourse,Long idCourseRequested); // can be derived by a remove and a add
	
	public boolean setHolderProfessor(Long idCourse,Long idProfessor);
	public boolean setHolderProfessor(Long idCourse, Professor professor);
	public Professor getHolderProfessor(Long idCourse);
	
	//commission
	public Long addProfessorAtCommission(Long idCourse,Long idProfessor);
	public Long addProfessorAtCommission(Long idCourse,Professor professor);
	public boolean setCommission(Long idCourse,Set<Professor>commission);
	public Professor removeProfessorFromCommission(Long idCourse,Long idProfessor);
	public Set<Professor> removeCommission(Long idCourse);
	
	public Set<RequestedCourse> getRequestedCourses(Long idCourse);
	public Set<RequestedCourse> getRequestedCourses(Long idCourse,Integer degreeOdPolicy);

	public ArrayList<Course> getAssociatedCourseWithGroups(User user);
	
}
