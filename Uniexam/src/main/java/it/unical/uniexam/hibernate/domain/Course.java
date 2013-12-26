package it.unical.uniexam.hibernate.domain;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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



	public Course() {
	}

	
	
//	public Course(Course c) {
//		super();
//		this.id = c.id;
//		this.code = c.code;
//		this.name = c.name;
//		this.webSite = c.webSite;
//		this.credits = c.credits;
//		this.requestedCourses = new HashSet<RequestedCourse>(c.requestedCourses);
//		this.commissionProfessors = new HashSet<Professor>(c.commissionProfessors);
//		this.holder = new Professor(c.holder);
//	}



	public Course(String code, String name, URL webSite, Integer credits,
			Set<RequestedCourse> requestedCourses,
			Set<Professor> commissionProfessors, Professor holder) {
		super();
		this.code = code;
		this.name = name;
		this.webSite = webSite;
		this.credits = credits;
		this.requestedCourses = requestedCourses;
		this.commissionProfessors = commissionProfessors;
		this.holder = holder;
	}

	@Id
	@Column(name="COURSE_ID")
	@GeneratedValue
	Long id;

	//deve essere implemetnato il collegamento tra il cdl e il cdl all'ordinamento
	//	@ManyToOne
	//	DegreeCourse underDegreeCourse;

	@Column(name="CODE", nullable=false)
	String code;

	@Column(name="NAME", nullable=false)
	String name;

	@Column(name="URL_WEB_SITE", nullable=true)
	URL webSite;

	@Column(name="CREDITS", nullable=false)
	Integer credits;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="COURSE_REQUESTCOURSE",
	joinColumns={
			@JoinColumn(name="COURSE_ID")
	}, 
	inverseJoinColumns={
			@JoinColumn(name="REQUEST_COURSE_ID")
	})
	Set<RequestedCourse> requestedCourses=new HashSet<RequestedCourse>();

	@ManyToMany(mappedBy="asCommission")
	Set<Professor>commissionProfessors=new HashSet<Professor>();

	@ManyToOne
	Professor holder;

	/**
	 * implementetion function part
	 * 
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getWebSite() {
		return webSite;
	}

	public void setWebSite(URL webSite) {
//		if(webSite!=null)
			this.webSite = webSite;
	}

	public Integer getCredits() {
		return credits;
	}

	public void setCredits(Integer credits) {
		this.credits = credits;
	}

	public Set<RequestedCourse> getRequestedCourses() {
		return requestedCourses;
	}

	public void setRequestedCourses(Set<RequestedCourse> requestedCourses) {
		if(requestedCourses!=null)
			this.requestedCourses = requestedCourses;
	}

	public Set<Professor> getCommissionProfessors() {
		return commissionProfessors;
	}

	public void setCommissionProfessors(Set<Professor> commissionProfessors) {
		if(commissionProfessors!=null)
			this.commissionProfessors = commissionProfessors;
	}

	public Professor getHolder() {
		return holder;
	}

	public void setHolder(Professor holder) {
//		if(holder!=null)
			this.holder = holder;
	}

}