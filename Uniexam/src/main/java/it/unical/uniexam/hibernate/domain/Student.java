package it.unical.uniexam.hibernate.domain;

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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import it.unical.uniexam.hibernate.domain.utility.Address;

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
 * @author luigi
 *
 */

@Entity
@Table(name="STUDENT")
public class Student {

	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue
	private Long id;



	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="STUDENT_PHONE",
	joinColumns={
			@JoinColumn(name="STUDENT_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="PHONE_ID")
			})
	private Set<PhoneNumber> phoneNumbers=new HashSet<PhoneNumber>();


	public Student() {}
}
