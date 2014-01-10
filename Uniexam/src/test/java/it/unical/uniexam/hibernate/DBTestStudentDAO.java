package it.unical.uniexam.hibernate;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

/**
 * 
 * @author fabrizio
 *
 */

public class DBTestStudentDAO {
	
	private static CourseDAO courseDAO = new CourseDAOImpl();
	private static StudentDAO studentDAO = new StudentDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "studente@unical.it"));
		Address address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber("HOME", "0984404040"));
		Long id = studentDAO.addStundent("Fabrizio", "Cava", "1234", address, emails, phoneNumbers, null, 1);
	}
	
	@Test
	public void checkStudent() {
		assertTrue(studentDAO.getStudents().size()==1);
	}
	
}
