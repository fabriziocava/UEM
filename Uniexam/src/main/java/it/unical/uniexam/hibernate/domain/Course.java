package it.unical.uniexam.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Structure 
 * 
 * This class describe a Course
 * each Course have a code, a name, a number of credits; 
 * 		and belongs at an DC;
 * At each Course 	have a professor like holder (titolare)(don't need exerciser),
 * 			 		have many other course as requested (propedeuticità)
 * 					possible have exam in pending (appelli)
 * 					possible appears in a carrier of students (carriera)
 * 					
 * 					something else?
 * 	
 * @author luigi
 *
 */

@Entity
@Table(name="COURSE")
public class Course {
	
	@Id
	@Column(name="COURSE_ID")
	@GeneratedValue
	Long id;
	
	@Column(name="CODE", nullable=false)
	String code;
	
	@Column(name="NAME", nullable=false)
	String name;
	
	@Column(name="CREDITS", nullable=false)
	Integer credits;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="COURSE_REQUESTCOURSE",
	joinColumns={
			@JoinColumn(name="COURSE")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="REQUEST_COURSE_ID")
			})
	Set<RequestedCourse> requestedCourses=new HashSet<RequestedCourse>();
	
	// scrivere la relazione Many to One
	@Column(name="HOLDER_PROFESSOR", nullable=false)
	Professor holder;
	
}