package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.dao.impl.UserDAOImpl;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

/**
 * 
 * @author fabrizio
 *
 */

public class DBTestUserDAO {
	
	private static UserDAO userDAO = new UserDAOImpl();
	
	@BeforeClass
	public static void prepareDB() throws MalformedURLException {
		Address address = new Address("Cosenza", "Italy", "87100", "Piazza segreteria, 13");
		HashSet<Email> emails = new HashSet<Email>();
		emails.add(new Email(Email.TYPE_UFFICIAL, "segreteria@unical.it"));
		HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
		phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE_UFFICIAL, "0984123456"));
		Long idUser = userDAO.addUser("Marco", "Polo", "1234", address, emails, phoneNumbers);
	}
	
	@Test
	public void checkUser() {
		assertTrue(userDAO.getUser(Long.getLong("1"))!=null);
	}

}
