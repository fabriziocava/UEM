package it.unical.uniexam.hibernate.domain;

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
import javax.persistence.Table;

@Entity
@Table(name="REQUESTED_COURSE")
public class RequestedCourse {

	@Id
	@GeneratedValue
	@Column(name="REQUESTED_COURSE_ID")
	Long id;
	
	@ManyToOne
	Course course;
	
//	@OneToMany
//	@JoinTable(name="STUDENT_PHONE",
//	joinColumns={
//			@JoinColumn(name="STUDENT_ID")
//			}, 
//	inverseJoinColumns={
//			@JoinColumn(name="PHONE_ID")
//			})
//	Set<Course>requested_course=new HashSet<Course>();
	
//	@Column(name="REQUESTED_COURSE")
//	Course requested_course;
	
	@Column(name="DEGREE_OF_REQUEST")
	Integer degreeOfRequest;
	
}
