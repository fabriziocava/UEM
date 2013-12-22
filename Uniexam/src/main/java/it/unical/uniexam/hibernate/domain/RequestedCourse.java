package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REQUESTED_COURSE")
public class RequestedCourse {

	@Id
	@GeneratedValue
	@Column(name="REQUESTED_COURSE_ID")
	Long id;
	
	@Column(name="COURSE")
	Course course;
	
//	@Column(name="REQUESTED_COURSE")
//	Course requested_course;
	
	@Column(name="DEGREE_OF_REQUEST")
	Integer degreeOfRequest;
	
}
