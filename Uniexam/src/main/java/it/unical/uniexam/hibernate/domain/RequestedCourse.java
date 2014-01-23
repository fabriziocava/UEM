package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author luigi
 *
 */
@Entity
@Table(name="REQUESTED_COURSE")
public class RequestedCourse {

	/**
	 * the course can be take from any student
	 */
	public static final String POLICY_LIGHT="light";
	/**
	 * the course can be take only if the student have already take the requested course
	 */
	public static final String POLICY_MEDIUM="medium";
	
	public static final String POLICY_STRONG="strong";
	
	public RequestedCourse(Course course, String degreeOfRequest) {
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
	String policyOfRequested;

	
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

	public String getPolicyOfRequested() {
		return policyOfRequested;
	}

	public void setPolicyOfRequested(String policyOfRequested) {
		this.policyOfRequested = policyOfRequested;
	}

}
