package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author fabrizio
 *
 */

@Entity
@Table(name="APPEAL_STUDENT")
@PrimaryKeyJoinColumn(name="AS_ID")
public class Appeal_Student {
	
	public enum STATE {
		NOT_SIGNED_BY_PROFESSOR, NOT_SIGNED_BY_COMMISSARY, AWAITING_ACKNOWLEDGMENT, LOADED_IN_SECRETERY;
	}
	
	/*
	 * CONSTRUCTORS
	 */
	public Appeal_Student() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * END_CONSTRUCTORS
	 */
	
	@Id
	@Column(name="AS_ID", nullable=false)
	@GeneratedValue
	Long id;

	@ManyToOne
	Appeal appeal;
	
	@ManyToOne
	Student student;
	
	@Column(name="STATE")
	STATE state;
	
	@Column(name="TEMPORANY_VOTE")
	int temporany_vote;

	/*
	 * GETTERS
	 */
	public Long getId() {
		return id;
	}

	public Appeal getAppeal() {
		return appeal;
	}

	public Student getStudent() {
		return student;
	}

	public STATE getState() {
		return state;
	}

	public int getTemporany_vote() {
		return temporany_vote;
	}
	/*
	 * END_GETTERS
	 */

	/*
	 * SETTERS
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public void setAppeal(Appeal appeal) {
		this.appeal = appeal;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public void setTemporany_vote(int temporany_vote) {
		this.temporany_vote = temporany_vote;
	}
	/*
	 * END_SETTERS
	 */
	
}
