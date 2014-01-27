package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public class DBTestCourseDAO {
	
	private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
	private static ProfessorDAO professorDAO = new ProfessorDAOImp();
	private static CourseDAO courseDAO = new CourseDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {	
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		departmentDAO.addDepartment(department);
		DegreeCourse degreeCourse = new DegreeCourse("INFORMATICA", department);
		degreeCourseDAO.addDegreeCourse(degreeCourse);
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "ricca@unical.it"));
		Address address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber("OFFICE", "196123456"));
		Long idProfessor = professorDAO.addProfessor("FRANCESCO", "RICCA", null, "1234", address, emails, phoneNumbers, department);
		Course course = new Course("1", "Enterprise Application", 5, professorDAO.getProfessor(idProfessor), degreeCourse);
		Long idCourse = courseDAO.addCourse(course);
		course = new Course("2", "Agent Intelligence", 5, professorDAO.getProfessor(idProfessor), degreeCourse);
		idCourse = courseDAO.addCourse(course);
		course = new Course("3", "Database", 10, professorDAO.getProfessor(idProfessor), degreeCourse);
		idCourse = courseDAO.addCourse(course);
		course = new Course("4", "Knowledge Management", 10, professorDAO.getProfessor(idProfessor), degreeCourse);
		idCourse = courseDAO.addCourse(course);
	}
	
	@Test
	public void checkCourse() {
		assertTrue(!courseDAO.getCourses().isEmpty());
	}
	
}
