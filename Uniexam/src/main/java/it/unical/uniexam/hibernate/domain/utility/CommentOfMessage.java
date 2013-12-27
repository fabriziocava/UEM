package it.unical.uniexam.hibernate.domain.utility;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.ws.soap.MTOM;

/**
 * @category Event 
 * 
 * This class describe a Comment of a Message of a group
 * each Comment of a Message have a code, a date, a id of Message that it belong; 
 * At each Comment 		have a principal content
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
	@Column(name="COMMENT_ID")
	@GeneratedValue
	Long id;
	
	@Column(name="USER_ID", nullable=false)
	Long id_user;
	
	@Column(name="COMMENT", nullable=false)
	String comment;
	
	@Column(name="DATE_OF_COMMENT",nullable=false)
	Date date_of_comment;

	public CommentOfMessage(Long id_user, String comment) {
		super();
		this.id_user = id_user;
		this.comment = comment;
		this.date_of_comment=new Date();
	}

//	@ManyToOne
//	MessageOfGroup ofMessage;
	
	
	//IMPLEMENTATION
	
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate_of_comment() {
		return date_of_comment;
	}

	public void setDate_of_comment(Date date_of_comment) {
		this.date_of_comment = date_of_comment;
	}

}
