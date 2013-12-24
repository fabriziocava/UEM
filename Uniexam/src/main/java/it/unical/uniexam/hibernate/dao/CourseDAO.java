package it.unical.uniexam.hibernate.dao;

import java.net.URL;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;

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
}
