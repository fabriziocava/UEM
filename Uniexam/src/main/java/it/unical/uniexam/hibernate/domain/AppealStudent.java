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
public class AppealStudent {
	
	public enum STATE {
		NOT_SIGNED_BY_PROFESSOR, 
		NOT_SIGNED_BY_COMMISSARY, 
		AWAITING_ACKNOWLEDGMENT, 
		LOADED_IN_SECRETERY;
	}
	
	/*
	 * CONSTRUCTORS
	 */
	public AppealStudent() {
	}
	
	public AppealStudent(Appeal appeal, Student student, STATE state,
			Double temporany_vote, String note) {
		super();
		this.appeal = appeal;
		this.student = student;
		this.state = state;
		this.temporany_vote = temporany_vote;
		this.note = note;
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
	Double temporany_vote;
	
	@Column(name="NOTE")
	String note;

	
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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

	public Double getTemporany_vote() {
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

	public void setTemporany_vote(Double temporany_vote) {
		this.temporany_vote = temporany_vote;
	}
	/*
	 * END_SETTERS
	 */
	
}
