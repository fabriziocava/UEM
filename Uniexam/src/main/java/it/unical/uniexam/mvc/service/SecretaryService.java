package it.unical.uniexam.mvc.service;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;

public interface SecretaryService extends UserService {
	
	public final static String SECRETARY_HOME="secretary/home";
	public final static String SECRETARY_DEPARTMENT="secretary/department";
	public final static String SECRETARY_REGISTER_DEPARTMENT="secretary/registerDepartment";
	public final static String SECRETARY_REGISTER_DEGREECOURSE="secretary/registerDegreeCourse";
	public final static String SECRETARY_REGISTER_MANAGER="secretary/registerManager";
	public final static String SECRETARY_QUERY_ID="idSecretary";
	public final static String SECRETARY_OBJECT="secretaryObj";

	public Long addDepartment(Department department);
	public Long addManager(Manager manager);
	public Long addProfessor(Professor professor);
	public Long addStudent(Student student);
	
	public Set<Department> getDepartment();
	
}
