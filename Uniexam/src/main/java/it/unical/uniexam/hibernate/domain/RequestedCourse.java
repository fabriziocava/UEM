package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
	
	@Column(name="DEGREE_OF_REQUEST")
	Integer degreeOfRequest;

	
	/**
	 * Implementation
	 */
	
	
	/**
	 * 
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getDegreeOfRequest() {
		return degreeOfRequest;
	}

	public void setDegreeOfRequest(Integer degreeOfRequest) {
		this.degreeOfRequest = degreeOfRequest;
	}

	
	
}
