package it.unical.uniexam.hibernate.mokTest;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for class DAO Course
 * 
 * @author luigi
 *
 */
public class DBTestCourseDAO {
	private static CourseDAO courseDAO=new CourseDAOImpl();
	private static ProfessorDAO professorDAO=new ProfessorDAOImp();
	static Long []ids=null;
	
	
	/**
	 * Testing of the main function
	 * Test superated
	 * @throws MalformedURLException
	 */
	@BeforeClass
	public static void prepareBD() throws MalformedURLException{

		ids=new Long[18];
		int count=0;
		ids[count++]=courseDAO.addCourse(new Course("INF", "AE", new URL("http:\\www.unical.it/AE"), 5, null, null, null));
		ids[count++]=courseDAO.addCourse(new Course("INF", "SI", new URL("http:\\www.unical.it/SI"), 5, null, null, null));
		/*2*/HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "ricca@gmail.com"));
		ids[count++]=professorDAO.addProfessor("Ciccio", "Ricca", new URL("http:\\www.ricca.com"), "pasticcio", new Address("Cs", "Italia", "87036", "Europa"),emails,new HashSet<PhoneNumber>(), null);
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "cali@gmail.com"));
		ids[count++]=professorDAO.addProfessor("Ciccio", "Calimeri", new URL("http:\\www.cali.com"),  
				"mero", new Address("Cs", "Italia", "87036", "Asia"),emails,new HashSet<PhoneNumber>(), null);
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "gibbi@gmail.com"));
		ids[count++]=professorDAO.addProfessor("Gibbi", "Ianni", new URL("http:\\www.ianni.com"),  
				"ibbig", new Address("Cs", "Italia", "87036", "USA"),emails,new HashSet<PhoneNumber>(), null);
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "super@gmail.com"));
		ids[count++]=professorDAO.addProfessor("Mario", "Alvian", new URL("http:\\www.superM.com"), 
				"Mario", new Address("Cs", "Italia", "87036", "Swizzera"),emails,new HashSet<PhoneNumber>(), null);
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "febbraro@gmail.com"));
		/*6*/ids[count++]=professorDAO.addProfessor("Onofr", "Febbr", new URL("http:\\www.febbre.com"),
				"marzo", new Address("Cs", "Italia", "87036", "roma"),emails,new HashSet<PhoneNumber>(), null);
		courseDAO.setHolderProfessor(ids[0], ids[2]);

		courseDAO.addProfessorAtCommission(ids[0], ids[2]);
		courseDAO.addProfessorAtCommission(ids[0], ids[3]);
		courseDAO.addProfessorAtCommission(ids[0], ids[4]);


		courseDAO.setHolderProfessor(ids[1], ids[3]);

		courseDAO.addProfessorAtCommission(ids[1], ids[4]);
		courseDAO.addProfessorAtCommission(ids[1], ids[5]);
		courseDAO.addProfessorAtCommission(ids[1], ids[6]);

		courseDAO.removeProfessorFromCommission(ids[0], ids[3]);// tolto ma non aggiunto
		courseDAO.addProfessorAtCommission(ids[0], ids[6]);

		/*count 7*/
		ids[count++]=courseDAO.addCourse(new Course("INF", "KM", new URL("http:\\www.unical.it/KM"), 10, null, null, null));
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "faber@gmail.com"));
		ids[count++]=professorDAO.addProfessor("Wolfgang", "Faber", new URL("http:\\www.faber.com"),
				"color", new Address("Wien", "Austrie", "87036", "europe"),emails,new HashSet<PhoneNumber>(), null);
		courseDAO.setHolderProfessor(ids[7], ids[8]);

		courseDAO.addProfessorAtCommission(ids[7], ids[3]);
		courseDAO.addProfessorAtCommission(ids[7], ids[5]);
		courseDAO.addProfessorAtCommission(ids[7], ids[6]);

		courseDAO.addRequestedCourse(ids[0], ids[1], RequestedCourse.POLICY_LIGHT);
		courseDAO.addRequestedCourse(ids[7], ids[1], RequestedCourse.POLICY_LIGHT);
		courseDAO.addRequestedCourse(ids[7], ids[0], RequestedCourse.POLICY_MEDIUM);
		
		courseDAO.removeRequestedCourse(ids[7], ids[1]);



		try{
			Thread.sleep(3000);
		}catch(Exception e){}
	}

	@Test
	public void checkCourseRequested(){
		assertTrue(courseDAO.getRequestedCourses(ids[0], RequestedCourse.POLICY_LIGHT).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[0], RequestedCourse.POLICY_MEDIUM)==null);
		assertTrue(courseDAO.getRequestedCourses(ids[7], RequestedCourse.POLICY_LIGHT)==null);
		assertTrue(courseDAO.getRequestedCourses(ids[7], RequestedCourse.POLICY_MEDIUM).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[7]).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[0]).iterator().next().getPolicyOfRequest()==RequestedCourse.POLICY_LIGHT);
		assertTrue(courseDAO.getRequestedCourses(ids[7]).iterator().next().getCourse().getName().equals("AE"));
	}

	@Test
	public void checkSetHolder(){
		assertTrue(courseDAO.getHolderProfessor(ids[1]).getId()==ids[3]);
		assertTrue(courseDAO.getHolderProfessor(ids[0]).getId()==ids[2]);
		courseDAO.setHolderProfessor(ids[1], ids[5]);
		assertTrue(courseDAO.getHolderProfessor(ids[1]).getId()==ids[5]);
		assertTrue(courseDAO.getHolderProfessor(ids[1]).getId()!=ids[3]);
	}

	@Test
	public void numberOfCourse(){
		assertTrue(courseDAO.getCourses().size()==3);
	}

	@AfterClass
	public static void afterClass(){

	}

	@Test
	public void numberOfCourseAsCommission(){
		Set<Course> setCourseAsCommission = professorDAO.getSetCourseAsCommission(ids[3]);
		for (Course course : setCourseAsCommission) {
			System.out.println("1 :"+course.getName());
		}
		assertTrue(setCourseAsCommission.size()==1);
		Set<Course> setCourseAsCommission2 = professorDAO.getSetCourseAsCommission(ids[6]);
		int size = setCourseAsCommission2.size();
		for (Course course : setCourseAsCommission2) {
			System.out.println("2 :"+course.getName());
		}
		assertTrue(size==3);
	}
}
