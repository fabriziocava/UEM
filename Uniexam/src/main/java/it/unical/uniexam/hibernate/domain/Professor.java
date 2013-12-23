package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @category Actor 
 * 
 * This class describe the actor Professor
 * each Professor extend User class, in addition have a set of a phone numbers
 * At each Professor 	is associated with a department
 * 						have many Exams (appelli)
 * 						have many Exams like commission (commissario) ?
 * 						have many Course as holder (titolare)	
 * 						have many Groups like a creator					
 * 
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="PROFESSOR")
public class Professor extends User{

	@Id
	@GeneratedValue
	@Column(name="PROFESSOR_ID")
	Long id;
	
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="PROFESSOR_PHONE",
	joinColumns={
			@JoinColumn(name="PROFESSOR_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="PHONE_ID")
			})
	private Set<PhoneNumber> phoneNumbers=new HashSet<PhoneNumber>();

	@ManyToOne
	Department department_associated;
	
	@OneToMany
	@JoinTable(name="PROFESSOR_EXAM",
	joinColumns={
			@JoinColumn(name="PROFESSOR_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="EXAM_ID")
			})
	Set<Exam>exams=new HashSet<Exam>();
	
	@OneToMany
	@JoinTable(name="PROFESSOR_COURSE_HOLDER",
	joinColumns={
			@JoinColumn(name="PROFESSOR_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="COURSE_ID")
			})
	Set<Course>holder=new HashSet<Course>();
	
	@OneToMany
	@JoinTable(name="PROFESSOR_GRUOP",
	joinColumns={
			@JoinColumn(name="PROFESSOR_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="GROUP_ID")
			})
	Set<Group>groups=new HashSet<Group>();
	
	
}
