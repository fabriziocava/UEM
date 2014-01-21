package it.unical.uniexam.hibernate.pinoTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.impl.CourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.ExamSessionDAOimpl;
import it.unical.uniexam.hibernate.dao.impl.ManagerDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.StudentDAOImpl;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public class DBTestManagerDAO {

	 private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
     private static CourseDAO courseDAO = new CourseDAOImpl();
     private static ManagerDao managerDAO = new ManagerDAOImpl();
     private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
     private static ExamSessionDAO examsessionDAO=new ExamSessionDAOimpl();
     
     @BeforeClass
     public static void prepareDB() throws MalformedURLException, InterruptedException {
             Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
             Long idDepartment = departmentDAO.addDepartment(department);
             DegreeCourse dg=new DegreeCourse("INFORMATICA", department);
             Long idDegreeCourse = degreeCourseDAO.addDegreeCourse(dg);
             ExamSession examsession=new ExamSession("Sessione febbraio", new Date(),new Date() , dg);
             Long idexamsession=examsessionDAO.addExamSession(examsession);
             
             HashSet<Email> emails = new HashSet<Email>();
             emails.add(new Email(Email.TYPE_UFFICIAL, "manager@unical.it"));
             Address address = new Address("Cosenza", "Italy", "87100", "Via Univ, 10");
             HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
             phoneNumbers.add(new PhoneNumber("HOME", "00000000"));
             Long idManager = managerDAO.addManager("Pino", "Lombardo",  new URL("http:\\www.pinolombardo.com"), "0000", address, emails, phoneNumbers, department);
             
             Thread.sleep(3000);
     }
     
     @Test
     public void checkStudent() {
             assertTrue(managerDAO.getManagers().size()==1);
     }
	
     @Test
     public void checkDegreeCourseOfAllDepartmets() {
             assertTrue(degreeCourseDAO.getDegreeCourses().size()==1);
     }
     
}
