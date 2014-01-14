package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.domain.Student;

public interface StudentService extends UserService {
	public final static String STUDENT_HOME="student/home";
	public final static String STUDENT_QUERY_ID="idStudent";
	public final static String STUDENT_OBJECT="studentObj";
	
	public Student getStudent(Long idUser);

}
