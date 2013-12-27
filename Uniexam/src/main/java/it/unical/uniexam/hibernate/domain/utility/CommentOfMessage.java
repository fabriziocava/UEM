package it.unical.uniexam.hibernate.domain.utility;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @category Event 
 * 
 * This class describe a Message of a group
 * each Message of a Group have a code, a date, a id of group that it belong; 
 * At each Message 		have a principal content
 * 						have a possible comments  	 
 * 					
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="COMMENT_OF_MESSAGE")
public class CommentOfMessage {

	@Id
	@Column(name="MESSAGE_OF_GROUP_ID")
	@GeneratedValue
	Long id;
	
	@Column(name="USER_ID", nullable=false)
	Long id_user;
	
	@Column(name="MESSAGE", nullable=false)
	String message;
	
	@Column(name="DATE_OF_MESSAGE",nullable=false)
	Date date_of_message;

	public CommentOfMessage(Long id_user, String message) {
		super();
		this.id_user = id_user;
		this.message = message;
	}

	public CommentOfMessage() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId_user() {
		return id_user;
	}

	public void setId_user(Long id_user) {
		this.id_user = id_user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate_of_message() {
		return date_of_message;
	}

	public void setDate_of_message(Date date_of_message) {
		this.date_of_message = date_of_message;
	}
}
