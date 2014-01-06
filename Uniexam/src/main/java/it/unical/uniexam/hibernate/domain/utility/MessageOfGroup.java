package it.unical.uniexam.hibernate.domain.utility;

import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name="MESSAGE_OF_GROUP")
public class MessageOfGroup {

	@Id
	@Column(name="MESSAGE_OF_GROUP_ID")
	@GeneratedValue
	Long id;
	
	@ManyToOne
	User user;
	
	@Column(name="MESSAGE", nullable=false)
	String message;
	
	@Column(name="DATE_OF_MESSAGE",nullable=false)
	Date date_of_message;

	public MessageOfGroup(User id_user, String message) {
		super();
		this.user = id_user;
		this.message = message;
	}

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	Group group;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="MESSAGE_COMMENT",
	joinColumns={
			@JoinColumn(name="MESSAGE_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="COMMENT_ID")
			})
	private Set<CommentOfMessage> comments=new HashSet<CommentOfMessage>();
	
// IMPLEMENTATION
	
	public MessageOfGroup() {
	}

	
	
	public Set<CommentOfMessage> getComments() {
		return comments;
	}



	public void setComments(Set<CommentOfMessage> comments) {
		this.comments = comments;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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



	public Group getGroup() {
		return group;
	}



	public void setGroup(Group group) {
		this.group = group;
	}
}
