package it.unical.uniexam.hibernate.dao;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public interface SecretaryDAO {
	public Long addUserSecretary(String name, String surname, String password, Address address, Set<Email> emails, Set<PhoneNumber> phoneNumbers);
}
