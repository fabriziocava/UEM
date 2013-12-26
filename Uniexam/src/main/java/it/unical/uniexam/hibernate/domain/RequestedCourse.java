package it.unical.uniexam.hibernate.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="REQUESTED_COURSE")
public class RequestedCourse {

	/**
	 * the course can be take from any student
	 */
	public static final Integer POLICY_1=1;
	/**
	 * the course can be take only if the student have already take the requested course
	 */
	public static final Integer POLICY_2=2;
	
//	public static final Integer POLICY_3=3;
	
	public RequestedCourse(Course course, Integer degreeOfRequest) {
		this.course = course;
		this.policyOfRequested = degreeOfRequest;
	}

	public RequestedCourse() {
	}

	@Id
	@GeneratedValue
	@Column(name="REQUESTED_COURSE_ID")
	Long id;
	
	@ManyToOne
	Course course;
	
	@Column(name="POLICY")
	Integer policyOfRequested;

	
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

	public Integer getPolicyOfRequest() {
		return policyOfRequested;
	}

	public void setPolicyOfRequest(Integer policyOfRequest) {
		this.policyOfRequested = policyOfRequest;
	}

	
	
}
