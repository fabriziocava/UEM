package it.unical.uniexam.hibernate.dao;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;

public interface DegreeCourseDAO {
	
	public Long addDegreeCourse(String name, Department department_associated);
	public Set<DegreeCourse> getDegreeCourses(Long idDepartment);
	public Set<DegreeCourse> getDegreeCourses();
	
}
