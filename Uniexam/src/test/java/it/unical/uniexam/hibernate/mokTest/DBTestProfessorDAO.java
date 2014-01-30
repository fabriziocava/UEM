package it.unical.uniexam.hibernate.mokTest;

import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.dao.impl.AppealDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.AppealStudentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ExamSessionDAOimpl;
import it.unical.uniexam.hibernate.dao.impl.GroupDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.UserDAOImpl;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

import org.hibernate.Query;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for class DAO Group
 * 
 * @author luigi
 *
 */
public class DBTestProfessorDAO {
	private static CourseDAO courseDAO=new CourseDAOImpl();
	private static ProfessorDAO professorDAO=new ProfessorDAOImp();
	private static GroupDAO groupDAO= new GroupDAOImpl();
	private static UserDAO userDAO=new UserDAOImpl();
	private static AppealDAO appealDAO=new AppealDAOImpl();
	private static AppealStudentDAO appealStudentDAO=new AppealStudentDAOImpl();
	private static StudentDAO studentDAO=new StudentDAOImpl();
	private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
	private static ExamSessionDAO examSessionDAO=new ExamSessionDAOimpl();
	static Long []ids=null;
	
	
	/**
	 * Testing of the main function
	 * Test superated
	 * @throws MalformedURLException
	 */
	@BeforeClass
	public static void prepareBD() throws MalformedURLException{

		ids=new Long[48];
		int count=0;
//		/*30*/try {
//			ids[count++]=appealDAO.addAppeal(ids[0], "Appello1", 13, 
//					"MT8", "Appello per l'esame straordinario", 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("03-02-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
//					ids[2]);
//			/*31*/ids[count++]=appealDAO.addAppeal(ids[0], "Appello2", 13, 
//					"MT8", "Appello alternativo uguale data,ma questo sotto", 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
//					ids[2]);
//			/*32*/ids[count++]=appealDAO.addAppeal(null, "Appello3", 13, 
//					"MT8", "Appello per provare il course null", 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("03-02-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
//					ids[2]);
			
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
		
//		appealStudentDAO.addAppealStudent(null, 1l, 8l, "uno studente", 30.8);
//		appealStudentDAO.addAppealStudent(null, 2l, 8l, "sempre lo stesso studente", 30.8);
//		appealStudentDAO.addAppealStudent(null, 3l, 8l, "idem studente", 30.8);
//		
		HashSet< Email> emails=new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "mok.luigi@gmail.com"));
		HashSet<PhoneNumber>numbers=new HashSet<PhoneNumber>();
		numbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "+393891535996"));
		Long id=studentDAO.addStundent("Luigi", "Molinaro", "MLNLGU89P16B774U", "mok", 
				new Address("Acri", "Italy", "87041", "via Europa, 284")
		, emails, numbers, 1L, "158786");
		
		HashSet< Email> emails2=new HashSet<Email>();
		emails2.add(new Email(Email.TYPE.UFFICIAL, "muk.maria@gmail.com"));
		HashSet<PhoneNumber>numbers2=new HashSet<PhoneNumber>();
		numbers2.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "+393891535996"));
		Long id2=studentDAO.addStundent("Maria", "Gerace", "GRCMRAP16B774U", "muk", 
				new Address("Acri", "Italy", "87041", "via Rosati, 1")
		, emails2, numbers2, 1L, "158797");
		
		HashSet< Email> emails3=new HashSet<Email>();
		emails3.add(new Email(Email.TYPE.UFFICIAL, "mik.angelo@gmail.com"));
		HashSet<PhoneNumber>numbers3=new HashSet<PhoneNumber>();
		numbers3.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "+393891535996"));
		Long id3=studentDAO.addStundent("Angelo", "Molinaro", "MLNANG89P16B774U", "mik", 
				new Address("Acri", "Italy", "87041", "via Europa, 284")
		, emails3, numbers3, 1L, "158978");
		
		
		ExamSession ex=new ExamSession("Sessione di Febbraio", new GregorianCalendar(2014, 02-1, 06).getTime(), new GregorianCalendar(2014, 02-2, 27).getTime(), degreeCourseDAO.getDegreeCourse(1L));
		examSessionDAO.addExamSession(ex);
		
		
		try{
			Thread.sleep(3000);
		}catch(Exception e){}
	}
	
	@Test
	public void only(){
		
	}
	
}
