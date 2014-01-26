package it.unical.uniexam.hibernate.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Event 
 * 
 * This class describe a Appeal
 * each Appeal have a code, a date, (many student inscribed ***); 
 * At each Appeal 	have a professor like holder (titolare) same prof. that is holder in the course!,
 * 			 		have many professors like a commission
 * 					refer exact a one course
 * 					refer exact a one session of exam
 *					have zero or many students inscribed ? *** 
 * 					
 * 					something else?
 * 
 * @functions
 * 	can inscribes at this appeal this student? this function have to use a Facade structure in which 
 * 		it ask at this class without know how this class works, and trust the result	
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="APPEAL")
public class Appeal {

	
	
	public Appeal() {
	}

	public Appeal(Course course, String name,
			Integer maxNumberOfInscribed, String location,String description, Date examDate,
			Date openDate, Date closeDate, Professor creatorProfessor) {
		this.course = course;
		this.name = name;
		this.currNumberOfInscribed = 0;
		this.maxNumberOfInscribed = maxNumberOfInscribed;
		this.location = location;
		this.description=description;
		this.examDate = examDate;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.creatorProfessor = creatorProfessor;
	}

	@Id
	@GeneratedValue
	@Column(name="APPEAL_ID")
	Long id;

	@ManyToOne(optional=true)
	Course course;
	
	@Column(name="CURR_INSCRIBED")
	Integer currNumberOfInscribed;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="MAX_INSCRIBED")
	Integer maxNumberOfInscribed;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@Column(name="LOCATION")
	String location;
	
	@Column(name="EXAM_DATE")
	Date examDate;
	
	@Column(name="OPEN_DATE")
	Date openDate;
	
	@Column(name="CLOSE_DATE")
	Date closeDate;
	

	@ManyToOne
	Professor creatorProfessor;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinTable(name="APPEAL_APPEALSTUDENT",
	joinColumns={
			@JoinColumn(name="APPEL_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="AS_ID")
			})
	Set<AppealStudent> appeal_student = new HashSet<AppealStudent>();
	
	
	/// Implementation
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaxNumberOfInscribed() {
		return maxNumberOfInscribed;
	}

	public void setMaxNumberOfInscribed(Integer maxNumberOfInscribed) {
		this.maxNumberOfInscribed = maxNumberOfInscribed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public Professor getCreatorProfessor() {
		return creatorProfessor;
	}

	public void setCreatorProfessor(Professor creatorProfessor) {
		this.creatorProfessor = creatorProfessor;
	}

	public Set<AppealStudent> getAppeal_student() {
		return appeal_student;
	}

	public void setAppeal_student(Set<AppealStudent> appeal_student) {
		this.appeal_student = appeal_student;
	}

	
	public Integer getCurrNumberOfInscribed() {
		return currNumberOfInscribed;
	}

	public void setCurrNumberOfInscribed(Integer currNumberOfInscribed) {
		this.currNumberOfInscribed = currNumberOfInscribed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
