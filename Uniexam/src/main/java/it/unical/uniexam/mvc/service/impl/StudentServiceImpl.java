package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.Set;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.mvc.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author fabrizio
 *
 */

@Service
public class StudentServiceImpl extends UserServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	@Autowired
	CourseDAO courseDAO;
	
	
	@Override
	public Student getStudent(Long serialNumber) {
		return studentDAO.getStudent(serialNumber);
	}
	
	@Override
	public ArrayList<Course> getCourses() {
		return courseDAO.getCourses();
	}
	
	@Override
	public Course getCourseDetails(Long idCourse) {	
		return courseDAO.getCourse(idCourse);
	}
	
}
