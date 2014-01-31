package it.unical.uniexam.hibernate.domain.utility;

import it.unical.uniexam.hibernate.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author luigi
 *
 */
@Entity
@Table(name="EMAIL")
public class Email {

//	public static final String TYPE_UFFICIAL="uffical";
//	public static final String TYPE_UNUFFICIAL="unuffical";
//	public static final String TYPE_HOME="home";
	
	public enum TYPE{
		UFFICIAL,UNUFFICIAL,HOME
	}
	
	public Email(){}
	
	public Email(TYPE type, String email) {
		super();
		this.type = type;
		this.email = email;
	}

	@Id
	@Column(name="EMAIL_ID")
	@GeneratedValue
	private Long id;
	
	
	
	@ManyToOne
	User user;
	
	@Column(name="EMAIL_TYPE")
	private TYPE  type;
	
	@Column(name="EMAIL" , unique=true)
	private String email;

	//implementation
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TYPE getType() {
		return type;
	}

	public void setType(TYPE type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
