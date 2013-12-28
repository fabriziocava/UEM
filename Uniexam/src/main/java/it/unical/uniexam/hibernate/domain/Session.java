package it.unical.uniexam.hibernate.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @category Event 
 * 
 * This class describe the Session 
 * each Session have a id, a start date, a end date
 * At each Session 		is associated with a DC (Degree Course)
 * 
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="SESSION")
public class Session {

	public static final Long timeExpire=86400000L; //one day
	
	@Id
	@GeneratedValue
	@Column(name="SESSION_ID")
	Long id;
	
	@Column(name="OWNER")
	Long owner;

	@Column(name="TYPE")
	User.TYPE type;
	
	@Column(name="EXPIRE")
	Date expire;
	
	@Column(name="ATTRIBUTES")
	String attributes;
	
	@Column(name="VALID")
	Boolean valid;
	
	@Column(name="CREATED")
	Date created;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwner() {
		return owner;
	}

	public void setOwner(Long owner) {
		this.owner = owner;
	}

	public User.TYPE getType() {
		return type;
	}

	public void setType(User.TYPE type) {
		this.type = type;
	}

	public Date getExpire() {
		return expire;
	}

	public void setExpire(Date expire) {
		this.expire = expire;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
}
