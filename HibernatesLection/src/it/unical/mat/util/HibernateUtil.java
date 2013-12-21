package it.unical.mat.util;

import it.unical.mat.domain.City;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static{
		try{
			sessionFactory=new Configuration()
			.configure(ClassLoader.getSystemResource("resources/hibernate.cfg.xml"))
			.addPackage("it.unical.mat.domain")
			.addAnnotatedClass(City.class)
			.buildSessionFactory();
		}catch(Exception e){

		}
	}
	//si devono aggiungere tutte le classi che vogliamo siano utilizzate nel db

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}


}
