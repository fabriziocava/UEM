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
 * This class describe a Post of a group
 * each Post of a Group have a code, a date, a id of group that it belong; 
 * At each Post 		have a principal content
 * 						have a possible comments  	 
 * 					
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="POST_OF_GROUP")
public class PostOfGroup {

	@Id
	@Column(name="POST_OF_GROUP_ID")
	@GeneratedValue
	Long id;
	
	@ManyToOne
	User user;
	
	@Column(name="POST", nullable=false)
	String post;
	
	@Column(name="DATE_OF_POST",nullable=false)
	Date date_of_post;

	public PostOfGroup(User id_user, String post) {
		super();
		this.user = id_user;
		this.post = post;
	}

	@ManyToOne(fetch=FetchType.EAGER, optional=false)
	Group group;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="POST_COMMENT",
	joinColumns={
			@JoinColumn(name="POST_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="COMMENT_ID")
			})
	private Set<CommentOfPost> comments=new HashSet<CommentOfPost>();
	
// IMPLEMENTATION
	
	public PostOfGroup() {
	}

	
	
	public Set<CommentOfPost> getComments() {
		return comments;
	}



	public void setComments(Set<CommentOfPost> comments) {
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

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public Date getDate_of_post() {
		return date_of_post;
	}

	public void setDate_of_post(Date date_of_post) {
		this.date_of_post = date_of_post;
	}



	public Group getGroup() {
		return group;
	}



	public void setGroup(Group group) {
		this.group = group;
	}
}
