package it.unical.uniexam.hibernate.util;


import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DC;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Exam;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static{
		try{
			//ClassLoader.getSystemResource("resources/hibernate.cfg.xml")
			sessionFactory=new Configuration()
			.configure("resources/hibernate.cfg.xml")
			.addPackage("it.unical.uniexam.hibernate.domain")
			.addAnnotatedClass(Department.class)
			.addAnnotatedClass(Professor.class)
			.addAnnotatedClass(Course.class)
			.addAnnotatedClass(DC.class)
			.addAnnotatedClass(Exam.class)
			.addAnnotatedClass(Group.class)
			.addAnnotatedClass(Manager.class)
			.addAnnotatedClass(RequestedCourse.class)
			.addAnnotatedClass(Session.class)
			.addAnnotatedClass(Student.class)
			.addAnnotatedClass(User.class)
			.buildSessionFactory();
		}catch(Exception e){

		}
	}
	//si devono aggiungere tutte le classi che vogliamo siano utilizzate nel db

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}
