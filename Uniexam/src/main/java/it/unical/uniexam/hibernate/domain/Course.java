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

	@ManyToOne
	Professor holder;

	/**
	 * implementetion function part
	 * 
	 */
	
	/**
	 * 
	 */
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
		this.requestedCourses = requestedCourses;
	}

	public Professor getHolder() {
		return holder;
	}

	public void setHolder(Professor holder) {
		this.holder = holder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public URL getUrlWebSite() {
		return webSite;
	}

	public void setUrlWebSite(URL url_web_site) {
		this.webSite = url_web_site;
	}
	
	
	
	
}