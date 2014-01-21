package it.unical.uniexam.hibernate.mokTest;

import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.dao.impl.AppealDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.GroupDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.dao.impl.UserDAOImpl;
import it.unical.uniexam.hibernate.domain.Course;
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
public class DBTestGroup2DAO {
	private static CourseDAO courseDAO=new CourseDAOImpl();
	private static ProfessorDAO professorDAO=new ProfessorDAOImp();
	private static GroupDAO groupDAO= new GroupDAOImpl();
	private static UserDAO userDAO=new UserDAOImpl();
	private static AppealDAO appealDAO=new AppealDAOImpl();
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
		ids[count++]=courseDAO.addCourse(new Course("INF", "AE", new URL("http:\\www.unical.it/AE"), 5, null, null, null,null));
		ids[count++]=courseDAO.addCourse(new Course("INF", "SI", new URL("http:\\www.unical.it/SI"), 5, null, null, null,null));
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "ricca@gmail.com"));
		/*2*/ids[count++]=professorDAO.addProfessor("Ciccio", "Ricca", new URL("http:\\www.ricca.com"), "pasticcio", new Address("Cs", "Italia", "87036", "Europa"),emails,new HashSet<PhoneNumber>(), null);
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "cali@gmail.com"));
		ids[count++]=professorDAO.addProfessor("Ciccio", "Calimeri", new URL("http:\\www.cali.com"),  
				"mero", new Address("Cs", "Italia", "87036", "Asia"),emails,new HashSet<PhoneNumber>(), null);
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "gibbi@gmail.com"));
		/*4*/ids[count++]=professorDAO.addProfessor("Gibbi", "Ianni", new URL("http:\\www.ianni.com"),  
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
		ids[count++]=courseDAO.addCourse(new Course("INF", "KM", new URL("http:\\www.unical.it/KM"), 10, null, null, null,null));
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
/*9*/
		ids[count++]=professorDAO.addPhoneNumber(ids[2], new PhoneNumber(PhoneNumber.TYPE_UFFICIAL, "3891535998"));
		ids[count++]=professorDAO.addPhoneNumber(ids[2], new PhoneNumber(PhoneNumber.TYPE_UNUFFICIAL, "3891535999"));
		/*11*/ids[count++]=professorDAO.addPhoneNumber(ids[2], new PhoneNumber(PhoneNumber.TYPE_HOME, "3891536000"));

		professorDAO.removePhoneNumber(ids[2], ids[10]);
		
		/*12*/
		ids[count++]=professorDAO.addEmail(ids[2], new Email(Email.TYPE_UFFICIAL, "ricca2@unical.it"));
		ids[count++]=professorDAO.addEmail(ids[2], new Email(Email.TYPE_UNUFFICIAL, "ricca@unUfficial.it"));
		ids[count++]=professorDAO.addEmail(ids[2], new Email(Email.TYPE_HOME, "ricca@home.it"));
		/*14*/

		professorDAO.removeEmail(ids[2], ids[13]);
		
		/*15*/ids[count++]=groupDAO.addGruop("Gruppo per iscrizione", "Iscrizione", "ti devi iscrivere a questo...bla..bla", ids[3], Group.POLICY_1,ids[1]);
		
		groupDAO.iscribeUserAtGroup(ids[2], ids[15]);
		groupDAO.iscribeUserAtGroup(ids[4], ids[15]);
		groupDAO.iscribeUserAtGroup(ids[5], ids[15]);
		groupDAO.iscribeUserAtGroup(ids[6], ids[15]);
		
		ids[count++]=groupDAO.addPostAtGroup(ids[15], new PostOfGroup(userDAO.getUser(ids[3]), "primo messaggio"));
		ids[count++]=groupDAO.addPostAtGroup(ids[15], new PostOfGroup(userDAO.getUser(ids[3]), "sexondo messaggio"));
		ids[count++]=groupDAO.addPostAtGroup(ids[15], new PostOfGroup(userDAO.getUser(ids[3]), "teerzo messaggio"));
		ids[count++]=groupDAO.addPostAtGroup(groupDAO.getGroup(ids[15]), new PostOfGroup(userDAO.getUser(ids[3]), "quarto messaggio")).getId();
		/*20*/ids[count++]=groupDAO.addPostAtGroup(ids[15], new PostOfGroup(userDAO.getUser(ids[3]), "quinto messaggio"));
		
		groupDAO.removePost(ids[15], ids[18]);
		
		/*21*/ids[count++]=groupDAO.addCommentAtPost(ids[19], new CommentOfPost(userDAO.getUser(ids[3]), "se se con il 4"));
		/*22*/ids[count++]=groupDAO.addCommentAtPost(ids[19], new CommentOfPost(userDAO.getUser(ids[3]), "se se con il 4.1"));
		try{
			Thread.sleep(300);
		}catch(Exception e){}
		/*23*/ids[count++]=groupDAO.addCommentAtPost(ids[19], new CommentOfPost(userDAO.getUser(ids[3]), "se se con il 4.2"));
		try{
			Thread.sleep(300);
		}catch(Exception e){}
		groupDAO.modifyCommentFromPost(ids[22], new CommentOfPost(userDAO.getUser(ids[3]),"no con 4.1.1"));
		
		groupDAO.removeCommentFromPost(ids[19], ids[21]);
		
		//add some comments
		
		/*24*/ids[count++]=groupDAO.addGruop("Gruppo2", "Ogetto2", "Descrizione 2", ids[4], Group.POLICY_1,ids[1]);
		groupDAO.iscribeUserAtGroup(ids[5], ids[24]);
		groupDAO.iscribeUserAtGroup(ids[7], ids[24]);
		try{
			Thread.sleep(300);
		}catch(Exception e){}
		/*25*/ids[count++]=groupDAO.addPostAtGroup(ids[24], new PostOfGroup(userDAO.getUser(ids[4]), "Io sono dovrei essere il 2"));
		groupDAO.addCommentAtPost(ids[25], new CommentOfPost(userDAO.getUser(ids[5]), "ok tu il 4 io il 5"));
		groupDAO.addCommentAtPost(ids[25], new CommentOfPost(userDAO.getUser(ids[5]), "sisi tu il 4 io il 5!!"));
		/*26*/ids[count++]=groupDAO.addPostAtGroup(ids[24], new PostOfGroup(userDAO.getUser(ids[5]), "Io sono dovrei essere il 3"));
		groupDAO.addCommentAtPost(ids[25], new CommentOfPost(userDAO.getUser(ids[4]), "si sei il 5 io il 4"));
		
		groupDAO.cancelUserFromGroup(ids[5], ids[15]);

//		groupDAO.removeGroup(ids[15]);
		// provare ad eliminare il corso! il gruppo deve restare
		
		/*27*/ids[count++]=courseDAO.addCourse(new Course("INF", "Fondamenti di Informatica", new URL("http:\\www.unical.it/Fondamenti"), 5, null, null, professorDAO.getProfessor(ids[3]),null));
		/*28*/ids[count++]=groupDAO.addGruop("Altro gruppo", "Iscrizione", "ti devi iscrivere a questo...bla..bla", ids[3], Group.POLICY_1,ids[1]);
		/*29*/ids[count++]=groupDAO.addGruop("gruppo per fondamenti", "Iscrizione", "ti devi iscrivere a questo...bla..bla", ids[3], Group.POLICY_1,ids[27]);
		
		/*30*/try {
			ids[count++]=appealDAO.addAppeal(ids[0], "Appello1", 13, 
					"MT8", "Appello per l'esame straordinario", 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("03-02-2014 09:00"), 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
					ids[2]);
			/*31*/ids[count++]=appealDAO.addAppeal(ids[0], "Appello2", 13, 
					"MT8", "Appello alternativo uguale data,ma questo sotto", 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"), 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
					ids[2]);
			/*32*/ids[count++]=appealDAO.addAppeal(null, "Appello3", 13, 
					"MT8", "Appello per provare il course null", 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("03-02-2014 09:00"), 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
					ids[2]);
			
//			ids[count++]=appealDAO.addAppeal(1L, "Appello1", 13, 
//					"MT8", "Appello per l'esame straordinario", 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("03-02-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
//					1L);
//			/*31*/ids[count++]=appealDAO.addAppeal(1l, "Appello2", 13, 
//					"MT8", "Appello alternativo uguale data,ma questo sotto", 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
//					1l);
//			/*32*/ids[count++]=appealDAO.addAppeal(null, "Appello3", 13, 
//					"MT8", "Appello per provare il course null", 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("03-02-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("31-01-2014 09:00"), 
//					new SimpleDateFormat("dd-MM-yyyy kk:mm").parse("02-02-2014 09:00"),  
//					1l);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		try{
			Thread.sleep(3000);
		}catch(Exception e){}
	}
	
	@Test
	public void checkQueryEmailPasswd(){
		User u=userDAO.getUser("cali@gmail.com", "mero",new ArrayList<Object>());
		assertTrue(u!=null);
	}
	
	@Test
	public void checkGetMethodOnGroup(){
		Set<Group> groups = groupDAO.getGroups();
		assertTrue(groups.size()==1);
//		for (Group group : groups) {
//			System.out.println("Name group: "+group.getName());
//		}
//		System.out.println(groupDAO.getGroup(ids[15]).toString());
		
		assertTrue(groupDAO.getPostsOfGroup(ids[15]).size()==0);
	}
	
	@Test
	public void checkDeleteModifyComment(){
		Set<CommentOfPost> commentsFromPost = groupDAO.getCommentsFromPost(ids[26]);
		assertTrue(commentsFromPost.size()==0);
	}
	
	@Test
	public void checkOnlyEmail(){
		Email emails = professorDAO.getEmail(ids[2],Email.TYPE_HOME);
		assertTrue(emails.getType().equals(Email.TYPE_HOME));
	}

	
	@Test
	public void checkEmail(){
		
		Set<Email> emails = professorDAO.getEmails(ids[2]);
		assertTrue(emails.size()==2);
	}

	@Test
	public void checkPhoneNumber(){
		Set<PhoneNumber> phoneNumbers = professorDAO.getPhoneNumbers(ids[2]);
		assertTrue(phoneNumbers.size()==2);
	}

	
	@Test
	public void checkCourseRequested(){
		assertTrue(courseDAO.getRequestedCourses(ids[0], RequestedCourse.POLICY_LIGHT).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[0], RequestedCourse.POLICY_MEDIUM)==null);
		assertTrue(courseDAO.getRequestedCourses(ids[7], RequestedCourse.POLICY_LIGHT)==null);
		assertTrue(courseDAO.getRequestedCourses(ids[7], RequestedCourse.POLICY_MEDIUM).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[7]).size()==1);
		assertTrue(courseDAO.getRequestedCourses(ids[0]).iterator().next().getPolicyOfRequested()==RequestedCourse.POLICY_LIGHT);
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
		assertTrue(setCourseAsCommission.size()==1);
		Set<Course> setCourseAsCommission2 = professorDAO.getSetCourseAsCommission(ids[6]);
		int size = setCourseAsCommission2.size();
		assertTrue(size==3);
	}
}
