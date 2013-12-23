package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.Address;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
 *
 */

@Entity
@Table(name="DEPARTMENT")
public class Department {

	@Id
	@Column(name="ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="NAME")
	private String name;
	
	@Embedded
	private Address address;
	
//	@OneToMany(mappedBy="department_assigned")
//	private Set<Professor> professors;

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
//	@Column(name="PRESIDENT")
//	@OneToOne(fetch=FetchType.LAZY)
//	private Professor president;
//	
	
	
	
}
