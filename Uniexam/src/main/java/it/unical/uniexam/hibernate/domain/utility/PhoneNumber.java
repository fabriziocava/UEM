package it.unical.uniexam.hibernate.domain.utility;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author luigi
 *
 */
@Entity
@Table(name="PHONE_NUMBER")
public class PhoneNumber {

//	public static final String TYPE_UFFICIAL="uffical";
//	public static final String TYPE_UNUFFICIAL="unuffical";
//	public static final String TYPE_HOME="home";
	
	public enum TYPE{
		UFFICIAL,UNUFFICIAL,HOME
	}
	
	public PhoneNumber(){}
	
	public PhoneNumber(TYPE type, String number) {
		super();
		this.type = type;
		this.number = number;
	}

	@Id
	@Column(name="PHONE_ID")
	@GeneratedValue
	private Long id;
	
	@Column(name="PHONE_TYPE")
	private TYPE type;
	
	@Column(name="PHONE_NUMBER")
	private String number;

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}
