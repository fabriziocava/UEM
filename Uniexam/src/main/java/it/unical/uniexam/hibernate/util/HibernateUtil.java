package it.unical.uniexam.hibernate.util;


import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfMessage;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author luigi
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static{
		try{
			//ClassLoader.getSystemResource("resources/hibernate.cfg.xml")
			sessionFactory=new Configuration()
			.configure("resources/hibernate.cfg.xml")
			.addPackage("it.unical.uniexam.hibernate.domain")
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(Department.class)
			.addAnnotatedClass(RequestedCourse.class)
			.addAnnotatedClass(Course.class)
			.addAnnotatedClass(DegreeCourse.class)
			.addAnnotatedClass(Appeal.class)
			.addAnnotatedClass(Group.class)
			.addAnnotatedClass(Session.class)
			.addAnnotatedClass(PhoneNumber.class)
			.addAnnotatedClass(Email.class)
			.addAnnotatedClass(CommentOfMessage.class)
			.addAnnotatedClass(MessageOfGroup.class)
			.addAnnotatedClass(Professor.class)
			.addAnnotatedClass(Manager.class)
			.addAnnotatedClass(Student.class)
			.buildSessionFactory();
		}catch(Exception e){
			new MokException(e);
		}
	}
	//si devono aggiungere tutte le classi che vogliamo siano utilizzate nel db

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}
