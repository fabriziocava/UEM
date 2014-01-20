package it.unical.uniexam.mvc.service;

import java.util.ArrayList;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Student;

public interface StudentService extends UserService {
	public final static String STUDENT_HOME="student/home";
	public final static String STUDENT_QUERY_ID="idStudent";
	public final static String STUDENT_OBJECT="studentObj";
	public static final String STUDENT_COURSE = "student/course";
	
	public Student getStudent(Long serialNumber);
	public ArrayList<Course> getCourses();

}
