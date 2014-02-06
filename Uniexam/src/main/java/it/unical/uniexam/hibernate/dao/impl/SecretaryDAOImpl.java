package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.SecretaryDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Secretary;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;
import it.unical.uniexam.mvc.service.UtilsService;

/**
 * 
 * @author luigi
 * modified by fabrizio
 *
 */

@Repository
public class SecretaryDAOImpl implements SecretaryDAO {

	@Override
	public Long addUserSecretary(String name, String surname,
			String password, Address address, Set<Email> emails,
			Set<PhoneNumber> phoneNumbers) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			User u = new Secretary(User.TYPE.SECRETARY, name, surname, null, password, address, emails, phoneNumbers);
			for(Email email : emails) {
				email.setUser(u);
			}
			id = (Long) session.save(u);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}

}
