package it.unical.uniexam.hibernate.dao;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.RequestedCourse;

public interface CourseDAO {
	/**
	 * Standard method
	 */
	
	/**
	 * Adding a Course and return the your id
	 */
	public Long addCourse(Long idDegreeCourse,String codeCourse,String nameCourse,Integer creditCourse,Long idProfessorHolder,Set<RequestedCourse>requestedCourses);
	public Set<Course> getCourses();
	public Set<Course> getCoursesFromDegreeCourse(Long idDepartment);
	public Course getCourseFromId(Long idCourse);
	public Course removeCourse(Long idCourse);

	/**
	 * Advanced method 
	 */
	
	/**
	 * Adding a requested course a one course
	 */
	public void addRequestedCourse(Long idCourse,Long idCourseRequested);
	public void removeRequestedCourse(Long idCourse,Long idCourseRequested);
//	public void modifyRequestedCourse(Long idCourse,Long idCourseRequested); // can be derived by a remove and a add
	
	public void setHolderProfessor(Long idCourse,Long idProfessor);
	public Long getHolderProfessor(Long idCourse);
}
