package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.UserDAOImpl;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public class DBTest {

	private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
	private static CourseDAO courseDAO = new CourseDAOImpl();
	private static StudentDAO studentDAO = new StudentDAOImpl();
	private static ProfessorDAO professorDAO = new ProfessorDAOImp();
	private static UserDAO userDAO = new UserDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		Long idDepartment = departmentDAO.addDepartment(department);
		DegreeCourse degreeCourse = new DegreeCourse("INFORMATICA", department);
		Long idDegreeCourse = degreeCourseDAO.addDegreeCourse(degreeCourse);
		
		/*
		 * PROFESSOR
		 */
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "professore@unical.it"));
		Address address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber("OFFICE", "196123456"));
		Long idProfessor = professorDAO.addProfessor("Francesco", "Ricca", null, "1234", address, emails, phoneNumbers, department);
		
		/*
		 * STUDENT
		 */
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "studente@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber("HOME", "0984404040"));
		Long idStudent = studentDAO.addStundent("Fabrizio", "Cava", "CVAFRZ88D14D086G", "1234", address, emails, phoneNumbers, degreeCourse, "158658");
	
		/*
		 * SECRETARY
		 */
		address = new Address("Cosenza", "Italy", "87100", "Piazza segreteria, 13");
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "segreteria@unical.it"));
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE_UFFICIAL, "0984123456"));
		Long idUser = userDAO.addUser("Marco", "Polo", "1234", address, emails, phoneNumbers);
		
		/*
		 * COURSE		
		 */
		Course course = new Course("1", "Enterprise Application", 5, professorDAO.getProfessor(idProfessor), degreeCourse);
		Long idCourse = courseDAO.addCourse(course);
		course = new Course("2", "Agent Intelligence", 5, professorDAO.getProfessor(idProfessor), degreeCourse);
		idCourse = courseDAO.addCourse(course);
		course = new Course("3", "Database", 10, professorDAO.getProfessor(idProfessor), degreeCourse);
		idCourse = courseDAO.addCourse(course);
		course = new Course("4", "Knowledge Management", 10, professorDAO.getProfessor(idProfessor), degreeCourse);
		idCourse = courseDAO.addCourse(course);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void checkDepartment() {
		assertTrue(!departmentDAO.getDepartments().isEmpty());
	}
	
	@Test
	public void checkDegreeCourse() {
		assertTrue(!degreeCourseDAO.getDegreeCourses().isEmpty());
	}
	
	@Test
	public void checkStudent() {
		assertTrue(!studentDAO.getStudents().isEmpty());
	}
	
	@Test
	public void checkProfessor() {
		assertTrue(!professorDAO.getProfessors().isEmpty());
	}
	
	@Test
	public void checkCourse() {
		assertTrue(!courseDAO.getCourses().isEmpty());
	}
	
}
