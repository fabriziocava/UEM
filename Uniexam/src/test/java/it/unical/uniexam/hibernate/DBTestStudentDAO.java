package it.unical.uniexam.hibernate;

import java.net.MalformedURLException;

import org.junit.BeforeClass;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;

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
		
	}
	
}
