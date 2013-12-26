package it.unical.uniexam.hibernate;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTestProfessorDAO {
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
		/*2*/ids[count++]=professorDAO.addProfessor("Ciccio", "Ricca", new URL("http:\\www.ricca.com"), "ricca@gmail.com", 
				"pasticcio", new Address("Cs", "Italia", "87036", "Europa"), null);
		ids[count++]=professorDAO.addProfessor("Ciccio", "Calimeri", new URL("http:\\www.cali.com"), "cali@gmail.com", 
				"mero", new Address("Cs", "Italia", "87036", "Asia"), null);
		ids[count++]=professorDAO.addProfessor("Gibbi", "Ianni", new URL("http:\\www.ianni.com"), "gibbi@gmail.com", 
				"ibbig", new Address("Cs", "Italia", "87036", "USA"), null);
		ids[count++]=professorDAO.addProfessor("Mario", "Alvian", new URL("http:\\www.superM.com"), "Mar@gmail.com", 
				"Mario", new Address("Cs", "Italia", "87036", "Swizzera"), null);
		/*6*/ids[count++]=professorDAO.addProfessor("Onofr", "Febbr", new URL("http:\\www.febbre.com"), "frev@gmail.com", 
				"marzo", new Address("Cs", "Italia", "87036", "roma"), null);

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
		ids[count++]=professorDAO.addProfessor("Wolfgang", "Faber", new URL("http:\\www.faber.com"), "faber@gmail.com", 
				"color", new Address("Wien", "Austrie", "87036", "europe"), null);

		courseDAO.setHolderProfessor(ids[7], ids[8]);

		courseDAO.addProfessorAtCommission(ids[7], ids[3]);
		courseDAO.addProfessorAtCommission(ids[7], ids[5]);
		courseDAO.addProfessorAtCommission(ids[7], ids[6]);

		courseDAO.addRequestedCourse(ids[0], ids[1], RequestedCourse.POLICY_1);
		courseDAO.addRequestedCourse(ids[7], ids[1], RequestedCourse.POLICY_1);
		courseDAO.addRequestedCourse(ids[7], ids[0], RequestedCourse.POLICY_2);
		
		courseDAO.removeRequestedCourse(ids[7], ids[1]);
/*9*/
		ids[count++]=professorDAO.addPhoneNumber(ids[2], new PhoneNumber("ufficio", "3891535998"));
		ids[count++]=professorDAO.addPhoneNumber(ids[2], new PhoneNumber("casa", "3891535999"));
		/*11*/ids[count++]=professorDAO.addPhoneNumber(ids[2], new PhoneNumber("room", "3891536000"));

		professorDAO.removePhoneNumber(ids[2], ids[10]);

		try{
			Thread.sleep(3000);
		}catch(Exception e){}
	}

	@Test
	public void checkPhoneNumber(){
		assertTrue(professorDAO.getPhoneNumbers(ids[2]).size()==2);
//		assertTrue(professorDAO.getPhoneNumbers(ids[2]).size()==2);
	}

	
	@Test
	public void checkCourseRequested(){
		assertTrue(courseDAO.getRequestedCourses(ids[0], RequestedCourse.POLICY_1).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[0], RequestedCourse.POLICY_2)==null);
		assertTrue(courseDAO.getRequestedCourses(ids[7], RequestedCourse.POLICY_1)==null);
		assertTrue(courseDAO.getRequestedCourses(ids[7], RequestedCourse.POLICY_2).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[7]).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[0]).iterator().next().getPolicyOfRequest()==RequestedCourse.POLICY_1);
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
