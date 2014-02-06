package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @category Actor 
 *
 * @author fabrizio
 *
 */

@Entity
@Table(name="SECRETARY")
@PrimaryKeyJoinColumn(name="ID")
public class Secretary extends User {
	public Secretary(TYPE type, String name, String surname, String fiscalCode, String password, Address address, Set<Email>emails, Set<PhoneNumber> phoneNumbers) {
		super(type, name, surname, null, password, address, emails, phoneNumbers);
	}
		
	public Secretary() {
	}
}
