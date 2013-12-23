package it.unical.uniexam.hibernate.domain;


import it.unical.uniexam.hibernate.domain.utility.Address;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class User {

//	@Id
//	@Column(name="USER_ID", nullable=false)
//	@GeneratedValue
//	String user_id;
	
	@Column(name="USERNAME", nullable=false)
	String username;
	
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



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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

}
