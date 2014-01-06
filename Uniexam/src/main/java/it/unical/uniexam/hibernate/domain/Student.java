package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
 * @author fabrizio
 *
 */

@Entity
@Table(name="STUDENT")
//@PrimaryKeyJoinColumn(name="STUDENT_ID")
public class Student extends User {
	
	/*
	 * CONSTURCTORS
	 */
	public Student(TYPE type, String name, String surname, String password, Address address, Set<Email>emails, Set<PhoneNumber> phoneNumbers, DegreeCourse degreeCourse_registered, Long serialNumber) {
		super(type, name, surname, null, password, address,emails,phoneNumbers);
		this.degreeCourse_registered = degreeCourse_registered;
		this.serialNumber = serialNumber;
	}
		
	public Student() {

	}
	/*
	 * END_CONSTRUCTORS
	 */
	

//	@Id
//	@Column(name="STUDENT_ID")
//	@GeneratedValue
//	private Long id;

	@Column(name="SERIAL_NUMBER", nullable=false, unique=true)
	private Long serialNumber; //MATRICOLA
	
	@Column(name="NAME", nullable=false)
	private String name;

	@Column(name="SURNAME", nullable=false)
	private String surname;
	
	@Column(name="FISCAL_CODE", length=16)
	private String fiscal_code;
	
	@ManyToOne
	DegreeCourse degreeCourse_registered;

	@OneToMany
	@JoinTable(name="STUDENT_APPEAL", 
				joinColumns={@JoinColumn(name="STUDENT_ID")},
				inverseJoinColumns={@JoinColumn(name="APPEAL_ID")}
			)
	Set<Appeal> appeals = new HashSet<Appeal>();
	
	@OneToMany
	@JoinTable(name="STUDENT_COURSE", //CARRIER
				joinColumns={@JoinColumn(name="STUDENT_ID")},
				inverseJoinColumns={@JoinColumn(name="COURSE_ID")}
			)
	Set<Course> carrier = new HashSet<Course>();
	
	@OneToMany
	@JoinTable(name="STUDENT_GROUP",
				joinColumns={@JoinColumn(name="STUDENT_ID")},
				inverseJoinColumns={@JoinColumn(name="GROUP_ID")}
			)
	Set<Group> groups = new HashSet<Group>();

	/*
	 * GETTER
	 */
	public Long getId() {
		return id;
	}

	public Long getSerialNumber() {
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

	public DegreeCourse getDegreeCourse_registered() {
		return degreeCourse_registered;
	}
	
	public Set<Appeal> getAppeals() {
		return appeals;
	}

	public Set<Course> getCarrier() {
		return carrier;
	}

	public Set<Group> getGroups() {
		return groups;
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

	public void setSerialNumber(Long serialNumber) {
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

	public void setDegreeCourse_registered(DegreeCourse degreeCourse_registered) {
		this.degreeCourse_registered = degreeCourse_registered;
	}

	public void setAppeals(Set<Appeal> appeals) {
		this.appeals = appeals;
	}

	public void setCarrier(Set<Course> carrier) {
		this.carrier = carrier;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}	
	/*
	 * END_SETTERS
	 */
}
