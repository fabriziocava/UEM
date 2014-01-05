package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.URL;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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
@PrimaryKeyJoinColumn(name="STUDENT_ID")
public class Student extends User {
	
	/*
	 * CONSTURCTORS
	 */
	public Student(TYPE type, String name, String surname, String password, Address address, Email email, Set<PhoneNumber> phoneNumbers, DegreeCourse degreeCourse_registered, Long serialNumber) {
		super(type, name, surname, null, password, address);
		this.email=email;
		this.phoneNumbers = phoneNumbers;
		this.degreeCourse_registered = degreeCourse_registered;
		this.serialNumber = serialNumber;
	}
		
	public Student() {

	}
	/*
	 * END_CONSTRUCTORS
	 */
	

	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue
	private Long id;

	@Column(name="SERIAL_NUMBER", nullable=false)
	private Long serialNumber; //MATRICOLA
	
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
	
	@Column(name="EMAIL")
	private Email email;
	
	@ManyToOne
	DegreeCourse degreeCourse_registered;


	/*
	 * GETTER
	 */
	public Long getId() {
		return id;
	}

	public Long getSerial_number() {
		return serialNumber;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getFiscal_code() {
		return fiscal_code;
	}

	public Address getAddress() {
		return address;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public Email getEmail() {
		return email;
	}

	public DegreeCourse getDegreeCourse_registered() {
		return degreeCourse_registered;
	}	
	/*
	 * END_GETTER
	 */
	
	/*
	 * SETTER
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public void setSerial_number(Long serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setFiscal_code(String fiscal_code) {
		this.fiscal_code = fiscal_code;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setDegreeCourse_registered(DegreeCourse degreeCourse_registered) {
		this.degreeCourse_registered = degreeCourse_registered;
	}
	/*
	 * END_SETTERS
	 */
}
