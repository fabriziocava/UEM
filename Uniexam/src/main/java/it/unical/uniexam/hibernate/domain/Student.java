package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@PrimaryKeyJoinColumn(name="STUDENT_ID")
public class Student extends User {
	
	/*
	 * CONSTURCTORS
	 */
	public Student(TYPE type, String name, String surname, String fiscalCode, String password, Address address, Set<Email>emails, Set<PhoneNumber> phoneNumbers, DegreeCourse degreeCourse_registered, String serialNumber) {
		super(type, name, surname, null, password, address, emails, phoneNumbers);
		this.fiscalCode = fiscalCode;
		this.degreeCourse_registered = degreeCourse_registered;
		this.serialNumber = serialNumber;
	}
		
	public Student() {
	}
	/*
	 * END_CONSTRUCTORS
	 */
	
	@Column(name="SERIAL_NUMBER", nullable=false, unique=true)
	private String serialNumber; //MATRICOLA
	
	@Column(name="FISCAL_CODE", length=16, nullable=false)
	private String fiscalCode;
	
	@ManyToOne
	DegreeCourse degreeCourse_registered;

//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinTable(name="STUDENT_APPEAL",
//				joinColumns={@JoinColumn(name="ID")},
//				inverseJoinColumns={@JoinColumn(name="APPEAL_ID")}
//			)
//	Set<Appeal> appeals = new HashSet<Appeal>();
	
//	@OneToMany(fetch=FetchType.LAZY)
//	@JoinTable(name="STUDENT_COURSE", //CARRIER
//				joinColumns={@JoinColumn(name="ID")},
//				inverseJoinColumns={@JoinColumn(name="COURSE_ID")}
//			)
//	Set<Course> carrier = new HashSet<Course>();
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="STUDENT_APPEALSTUDENT",
				joinColumns={@JoinColumn(name="ID")},
				inverseJoinColumns={@JoinColumn(name="AS_ID")}
			)
	Set<Appeal_Student> appeal_student = new HashSet<Appeal_Student>();

	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="STUDENT_CARRIER",
				joinColumns={@JoinColumn(name="ID")},
				inverseJoinColumns={@JoinColumn(name="CARRIER_ID")}
			)
	Set<Carrier> carrier = new HashSet<Carrier>();
	
	/*
	 * GETTERS
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public DegreeCourse getDegreeCourse_registered() {
		return degreeCourse_registered;
	}
	
//	public Set<Appeal> getAppeals() {
//		return appeals;
//	}

//	public Set<Course> getCarrier() {
//		return carrier;
//	}

	public Set<Group> getGroups() {
		return groups;
	}	
	
	public Set<Appeal_Student> getAppeal_student() {
		return appeal_student;
	}
	
	public Set<Carrier> getCarrier() {
		return carrier;
	}
	/*
	 * END_GETTERS
	 */

	/*
	 * SETTERS
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public void setDegreeCourse_registered(DegreeCourse degreeCourse_registered) {
		this.degreeCourse_registered = degreeCourse_registered;
	}

//	public void setAppeals(Set<Appeal> appeals) {
//		this.appeals = appeals;
//	}

//	public void setCarrier(Set<Course> carrier) {
//		this.carrier = carrier;
//	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public void setAppeal_student(Set<Appeal_Student> appeal_student) {
		this.appeal_student = appeal_student;
	}
	
	public void setCarrier(Set<Carrier> carrier) {
		this.carrier = carrier;
	}
	/*
	 * END_SETTERS
	 */
	
}
