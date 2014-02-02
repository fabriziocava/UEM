package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.GroupDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ManagerDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDAOImp;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.UserDAOImpl;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Group;
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
	private static GroupDAO groupDAO = new GroupDAOImpl();
	private static ManagerDao managerDAO = new ManagerDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {
		
		ArrayList<Long> idProfessors = new ArrayList<Long>();
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Group> groups = new ArrayList<Group>();
		
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		Long idDepartment = departmentDAO.addDepartment(department);
		DegreeCourse degreeCourse = new DegreeCourse("INFORMATICA SPECIALISTICA", department);
		Long idDegreeCourse = degreeCourseDAO.addDegreeCourse(degreeCourse);
		
		/*
		 * PROFESSORS
		 */
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "ricca@unical.it"));
		Address address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "196123456"));
		idProfessors.add(professorDAO.addProfessor("Francesco", "Ricca", null, "1234", address, emails, phoneNumbers, department));

		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "calimeri@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "365823456"));
		idProfessors.add(professorDAO.addProfessor("Francesco", "Calimeri", null, "1234", address, emails, phoneNumbers, department));
		
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "alviano@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "998523456"));
		idProfessors.add(professorDAO.addProfessor("Mario", "Alviano", null, "1234", address, emails, phoneNumbers, department));

		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "rullo@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "255823456"));
		idProfessors.add(professorDAO.addProfessor("Don Pasquale", "Rullo", null, "1234", address, emails, phoneNumbers, department));

		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "terracina@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "255823456"));
		idProfessors.add(professorDAO.addProfessor("Giorgio", "Terracina", null, "1234", address, emails, phoneNumbers, department));
		
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "spataro@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "255823456"));
		idProfessors.add(professorDAO.addProfessor("William", "Spataro", null, "1234", address, emails, phoneNumbers, department));
		
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "ianni@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "255823456"));
		idProfessors.add(professorDAO.addProfessor("Giambattista", "Ianni", null, "1234", address, emails, phoneNumbers, department));
		
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "leone@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Via dei Ricchi, 58");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "255823456"));
		idProfessors.add(professorDAO.addProfessor("Nicola", "Leone", null, "1234", address, emails, phoneNumbers, department));
		
		/*
		 * MANAGER
		 */
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "manager@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Piazza Sdao, 30");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "12348888"));
		Long idManager = managerDAO.addManager("Paola", "Sdao", null, "1234", address, emails, phoneNumbers, department);
		
		/*
		 * STUDENT
		 */
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "cava@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.HOME, "0984404040"));
		Long idStudent = studentDAO.addStundent("Fabrizio", "Cava", "CVAFRZ88D14D086G", "1234", address, emails, phoneNumbers, degreeCourse, "158658");
		
		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "molinaro@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.HOME, "0984404040"));
		idStudent = studentDAO.addStundent("Luigi", "Molinaro", "XXXXXX88X14X888G", "1234", address, emails, phoneNumbers, degreeCourse, "160585");

		emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE.UFFICIAL, "lombardo@unical.it"));
		address = new Address("Cosenza", "Italy", "87100", "Viale degli studenti, 30");
		phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.HOME, "0984404040"));
		idStudent = studentDAO.addStundent("Giuseppe", "Lombardo", "XXXXXX88X14X888G", "1234", address, emails, phoneNumbers, degreeCourse, "165858");
		
		/*
		 * SECRETARY
		 */
//		address = new Address("Cosenza", "Italy", "87100", "Piazza segreteria, 13");
//		emails = new HashSet<Email>();
//		emails.add(new Email(Email.TYPE.UFFICIAL, "segreteria@unical.it"));
//		phoneNumbers = new HashSet<PhoneNumber>();
//		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "0984123456"));
//		Long idUser = userDAO.addUser("Marco", "Polo", "1234", address, emails, phoneNumbers);
		
		/*
		 * COURSE		
		 */
		courses.add(new Course("773", "Enterprise Application", 5, professorDAO.getProfessor(idProfessors.get(0)), degreeCourse));
		courses.add(new Course("774", "Agent Intelligence", 5, professorDAO.getProfessor(idProfessors.get(1)), degreeCourse));
		courses.add(new Course("775", "Knowledge Management", 10, professorDAO.getProfessor(idProfessors.get(2)), degreeCourse));
		courses.add(new Course("776", "Data Mining", 5, professorDAO.getProfessor(idProfessors.get(3)), degreeCourse));
		courses.add(new Course("777", "Data Warehouse", 5, professorDAO.getProfessor(idProfessors.get(4)), degreeCourse));
		courses.add(new Course("778", "Algoritmi paralleli e sistemi distribuiti", 5, professorDAO.getProfessor(idProfessors.get(5)), degreeCourse));
		courses.add(new Course("779", "Reti e sicurezza informatica", 10, professorDAO.getProfessor(idProfessors.get(6)), degreeCourse));
		courses.add(new Course("780", "Informatica teorica", 10, professorDAO.getProfessor(idProfessors.get(7)), degreeCourse));
		Long idCourse;
		for(Course c : courses) {
			idCourse = courseDAO.addCourse(c);
		}
		
		/*
		 * GROUP
		 */
		/*
		groups.add(new Group("EA_2013", "Gruppo per informazioni", "Iscriversi per essere sempre aggiornati", Group.POLICY_1, professorDAO.getProfessor(idProfessors.get(0)), courses.get(0)));
		groups.add(new Group("Artificial Intelligence_2013", "Gruppo per informazioni", "Iscriversi per essere sempre aggiornati", Group.POLICY_1, professorDAO.getProfessor(idProfessors.get(1)), courses.get(1)));
		groups.add(new Group("KM_2013", "Gruppo per informazioni", "Iscriversi per essere sempre aggiornati", Group.POLICY_1, professorDAO.getProfessor(idProfessors.get(2)), courses.get(2)));
		groups.add(new Group("DB", "Gruppo per informazioni", "Iscriversi per essere sempre aggiornati", Group.POLICY_1, professorDAO.getProfessor(idProfessors.get(3)), courses.get(3)));
		Long idGroup;
		for(Group g : groups) {
			idGroup = groupDAO.addGruop(g);
		}
		*/
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
	
	/*
	@Test
	public void checkGroup() {
		assertTrue(!groupDAO.getGroups().isEmpty());
	}
	*/
	
	@Test
	public void checkManager() {
		assertTrue(!managerDAO.getManagers().isEmpty());
	}
}
