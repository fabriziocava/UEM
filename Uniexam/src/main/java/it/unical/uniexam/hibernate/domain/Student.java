package it.unical.uniexam.hibernate.domain;

import it.unical.inf.november26.domain.Address;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Actor 
 * 
 * This class describe the actor Student
 * each Student extend User class, in addition have a set of a phone numbers
 * At each Student 		is associated with a Degree Course
 * 						have a carrier (Carrier is a list of a course with a vote)
 * 
 * 					something else?
 * 
 * 
 * @author fabrizio
 *
 */

@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue
	private Long id;

	@Column(name="SERIAL_NUMBER", nullable=false)
	private String serial_number; //MATRICOLA
	
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="SURNAME", nullable=false)
	private String surname;
	
	@Column(name="FISCAL_CODE", length=16)
	private String fiscal_code;
	
	@Embedded
	private Address address;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="PHONE",
	joinColumns={
			@JoinColumn(name="STUDENT_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="PHONE_ID")
			})
	private Set<PhoneNumber> phoneNumbers=new HashSet<PhoneNumber>();

	
	
	public Student() {
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getSerial_number() {
		return serial_number;
	}



	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getFiscal_code() {
		return fiscal_code;
	}



	public void setFiscal_code(String fiscal_code) {
		this.fiscal_code = fiscal_code;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}



	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	
	
	
}
