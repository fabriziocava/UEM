package it.unical.uniexam.hibernate.pinoTest;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.util.HashSet;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.SecretaryDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.dao.impl.SecretaryDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.UserDAOImpl;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;


public class DBTestUserDAO {
        
        private static UserDAO userDAO = new UserDAOImpl();
        private static SecretaryDAO secretaryDAO = new SecretaryDAOImpl();
        
        @BeforeClass
        public static void prepareDB() throws MalformedURLException, InterruptedException {
                Address address = new Address("Cosenza", "Italy", "87100", "Piazza segreteria, 13");
                HashSet<Email> emails = new HashSet<Email>();
                emails.add(new Email(Email.TYPE.UFFICIAL, "segreteria@unical.it"));
                HashSet<PhoneNumber> phoneNumbers = new HashSet<PhoneNumber>();
                phoneNumbers.add(new PhoneNumber(PhoneNumber.TYPE.UFFICIAL, "0984123456"));
                Long idUser = secretaryDAO.addUserSecretary("Marco", "Polo", "1234", address, emails, phoneNumbers);
                Thread.sleep(3000);
        }
        
        @Test
        public void checkUser() {
                assertTrue(userDAO.getUser("1")!=null);
        }

}