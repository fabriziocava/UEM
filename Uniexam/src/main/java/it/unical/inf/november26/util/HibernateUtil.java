package it.unical.inf.november26.util;

import it.unical.inf.november26.domain.MatrDetails;
import it.unical.inf.november26.domain.PhoneNumber;
import it.unical.inf.november26.domain.Student;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static{
		try{
			sessionFactory=new Configuration()
			.configure(ClassLoader.getSystemResource("resources/hibernate.cfg.xml"))
			.addPackage("it.unical.inf.november26.domain")
			.addAnnotatedClass(Student.class)
			.addAnnotatedClass(PhoneNumber.class)
			.addAnnotatedClass(MatrDetails.class)
			.buildSessionFactory();
		}catch(Exception e){

		}
	}
	//si devono aggiungere tutte le classi che vogliamo siano utilizzate nel db

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}
