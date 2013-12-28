package it.unical.uniexam.hibernate.domain;


import java.net.URL;

import it.unical.uniexam.hibernate.domain.utility.Address;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class User {

	public enum TYPE{
		PROFESSOR,STUDENT;
	}
	
	
public User() {
	}

public User(String name, String surname, URL webSite,
			String password, Address address) {
		super();
		this.name = name;
		this.surname = surname;
		this.webSite = webSite;
		this.password = password;
		this.address = address;
	}

//	@Id
//	@Column(name="USER_ID", nullable=false)
//	@GeneratedValue
//	String user_id;
	
	
	
	@Column(name="NAME", nullable=false)
	String name;
	
	@Column(name="SURNAME", nullable=false)
	String surname;
	
	@Column(name="WEB_SITE",nullable=true)
	URL webSite;
	
	@Column(name="PASSWORD", nullable=false)
	String password;
	
	@Embedded
	Address address;
	
//	@OneToMany(cascade=CascadeType.ALL)
//	@JoinTable(name="USER_PHONE",
//	joinColumns={
//			@JoinColumn(name="USER_ID")
//			}, 
//	inverseJoinColumns={
//			@JoinColumn(name="PHONE_ID")
//			})
//	Set<PhoneNumber>phoneNumbers=new HashSet<PhoneNumber>();
	
	
	/**
	 * Second part of function
	 */



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public URL getWebSite() {
		return webSite;
	}

	public void setWebSite(URL webSite) {
		this.webSite = webSite;
	}

}
