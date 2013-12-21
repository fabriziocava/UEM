package it.unical.inf.uem.hib.dao.imp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.inf.uem.hib.dao.CityDao;
import it.unical.inf.uem.hib.domain.City;
import it.unical.inf.uem.hib.util.HibernateUtil;

public class CityDaoImpl implements CityDao{

	@Override
	public void deleteCity(Long id) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();

		City city=(City)session.get(City.class, id);

		session.delete(city);

		session.flush();
		transaction.commit();
		session.close();
	}

	@Override
	public void updateCity(Long id, String name) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();

		City city=(City) session.get(City.class, id);
		city.setName(name);
		//		session.update(city);
		//è già legato alla sessione quindi non c'è bisogno di fare un update
		session.flush();
		transaction.commit();
		session.close();
	}

	@Override
	public List listCities() {
		Session session=HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction=session.beginTransaction();

//non ha la select poichè esso restituirà degli oggetti e non dei campi
		List <City>list = session.createQuery("from City").list();
		
		System.out.println("Lista delle citta°:");
		
		for (City city : list) {
			System.out.println(city.toString());
		}
//		transaction.rollback(); // ci elimina tutte le modifiche fatte a partire dall'ultima commit
		
//		transaction.commit();
		session.close();
		return list;
	}

	@Override
	public Long saveCity(String name) {
		Session session=HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=session.beginTransaction();

		City city=new City(name);
		Long id=(Long) session.save(city);

		session.flush();
		transaction.commit();
		session.close();

		return id;
	}

	@Override
	public City getCityWithId(Long id) {
		Session session=HibernateUtil.getSessionFactory().openSession();

		City c=(City) session.get(City.class, id);

		session.close();

		return c;
	}

}
