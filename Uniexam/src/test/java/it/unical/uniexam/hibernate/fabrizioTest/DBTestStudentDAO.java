package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

/**
 * 
 * @author fabrizio
 *
 */

public class DBTestStudentDAO {
	
	private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private static CourseDAO courseDAO = new CourseDAOImpl();
	private static StudentDAO studentDAO = new StudentDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		Long idDepartment = departmentDAO.addDepartment(department);
		
		
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "studente@unical.it"));
		Address address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber("HOME", "0984404040"));
		Long idStudent = studentDAO.addStundent("Fabrizio", "Cava", "CVAFRZ88D14D086G", "1234", address, emails, phoneNumbers, null, "158658");
	}
	
	@Test
	public void checkStudent() {
		assertTrue(studentDAO.getStudents().size()==1);
	}
	
}
