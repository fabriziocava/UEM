package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.Address;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * @category Structure 
 * 
 * This class describe a Department
 * each Department have a code, a name; 
 * At each Department 	have a professor like a president (maybe, we don't need it)
 * 						have a manager (!!!)
 * 						have many DC,
 * 			 			have many professors associated 
 * 						
 * 						something else?
 * 	
 * @author luigi
 * modified by fabrizio
 *
 */

@Entity
@Table(name="DEPARTMENT")
public class Department {
	
	public Department() {
	}

	public Department(String code, String name, Address address) {
		this.code = code;
		this.name = name;
		this.address = address;
	}

	@Id
	@Column(name="DEPARTMENT_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="CODE")
	private String code;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	Professor president;

	@Column(name="NAME")
	private String name;
	
	@Embedded
	private Address address;
	
	
//	@OneToMany(cascade=CascadeType.ALL ,mappedBy="department_associated")
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="DEPARTMENT_PROFESSOR",
	joinColumns={
			@JoinColumn(name="DEPARTMENT_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="ID")
			})
	private Set<Professor> professors=new HashSet<Professor>();

	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="DEPARTMENT_DEGREECOURSE",
	joinColumns={
			@JoinColumn(name="DEPARTMENT_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="DEGREE_COURSE_ID")
			})
	private Set<DegreeCourse> degreeCourses = new HashSet<DegreeCourse>();
	
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Professor getPresident() {
		return president;
	}

	public void setPresident(Professor president) {
		this.president = president;
	}

	public Set<Professor> getProfessors() {
		return professors;
	}

	public void setProfessors(Set<Professor> professors) {
		this.professors = professors;
	}

	public Set<DegreeCourse> getDegreeCourses() {
		return degreeCourses;
	}

	public void setDegreeCourses(Set<DegreeCourse> degreeCourses) {
		this.degreeCourses = degreeCourses;
	}
	
	
	
//	@Column(name="PRESIDENT")
//	@OneToOne(fetch=FetchType.LAZY)
//	private Professor president;
//	
	
	
	
}
