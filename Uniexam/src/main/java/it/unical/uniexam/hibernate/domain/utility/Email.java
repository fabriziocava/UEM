package it.unical.uniexam.hibernate.domain.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EMAIL")
public class Email {

	public static final String TYPE_UFFICIAL="uffical";
	public static final String TYPE_UNUFFICIAL="unuffical";
	public static final String TYPE_HOME="home";
	
	
	public Email(){}
	
	public Email(String type, String email) {
		super();
		this.type = type;
		this.email = email;
	}

	@Id
	@Column(name="EMAIL_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="EMAIL_TYPE")
	private String type;
	
	@Column(name="EMAIL")
	private String email;

	//implementation
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
