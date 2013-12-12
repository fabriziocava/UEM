package it.unical.uniexam.hibernate.domain;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="PROFESSOR")
public class Professor {

	@Id
	@Column(name="PROFESSOR_ID")
	@GeneratedValue
	private Long id;
	
//	@JoinColumn(name="PROFESSOR_DEPARTMENT")
	@ManyToOne
	@ForeignKey(name="FK_PROFESSOR_DEPARTMENT")
	private Department department_assigned;

	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="EMAIL")
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="PROFESSOR_PHONE",
	joinColumns={
			@JoinColumn(name="PROFESSOR_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="PHONE_ID")
			})
	private Set<PhoneNumber> phoneNumbers=new HashSet<PhoneNumber>();
	
	@Override
	public String toString() {
		return name+ " "+ surname+" "+ department_assigned.getCode()+" \n";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Professor() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department_assigned;
	}
	public void setDepartment(Department department){
		department_assigned=department;
	}
}
