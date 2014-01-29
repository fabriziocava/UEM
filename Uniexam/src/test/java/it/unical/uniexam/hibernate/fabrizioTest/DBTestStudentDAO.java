package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber.TYPE;

/**
 * 
 * @author fabrizio
 *
 */

public class DBTestStudentDAO {
	
	private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
	private static CourseDAO courseDAO = new CourseDAOImpl();
	private static StudentDAO studentDAO = new StudentDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		Long idDepartment = departmentDAO.addDepartment(department);
		DegreeCourse degreeCourse = new DegreeCourse("INFORMATICA", department);
		Long idDegreeCourse = degreeCourseDAO.addDegreeCourse(degreeCourse);
			
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "studente@unical.it"));
		Address address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(TYPE.HOME, "0984404040"));
		Long idStudent = studentDAO.addStundent("Fabrizio", "Cava", "CVAFRZ88D14D086G", "1234", address, emails, phoneNumbers, degreeCourse, "158658");
	}
	
	@Test
	public void checkStudent() {
		assertTrue(studentDAO.getStudents().size()==1);
	}
	
}
