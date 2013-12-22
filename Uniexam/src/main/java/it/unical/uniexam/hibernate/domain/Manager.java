package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Actor 
 * 
 * This class describe the actor Manager
 * each Manager extend User class, in addition have a set of a phone numbers
 * At each Manager 	is associated with a department
 * 					
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="MANAGER")
public class Manager extends User{
	
	@Id
	@Column(name="MANAGER_ID" , nullable=false)
	@GeneratedValue
	Long manager_id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="MANAGER_PHONE",
	joinColumns={
			@JoinColumn(name="MANAGER_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="PHONE_ID")
			})
	Set<PhoneNumber>phoneNumbers=new HashSet<PhoneNumber>();

	/**
	 * 
	 * Second part of implementation
	 * 
	 */
	
	
	
	
	public Long getManager_id() {
		return manager_id;
	}

	public Manager() {
	}

	public Manager(Long manager_id, Set<PhoneNumber> phoneNumbers) {
		this.manager_id = manager_id;
		this.phoneNumbers = phoneNumbers;
	}

	public void setManager_id(Long manager_id) {
		this.manager_id = manager_id;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	
}
