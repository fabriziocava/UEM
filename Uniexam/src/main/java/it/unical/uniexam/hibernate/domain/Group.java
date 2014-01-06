package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Event 
 * 
 * This class describe a Group
 * each Group have a code, ( map<Date,MessageOfGroup> messages OR List<MessageOfGroup>) ,a name,a description, a object
 * At each Exam 	have a professor like holder (titolare) same prof. that is holder in the course!,
 * 			 		have many professors like a commission
 * 					refer exact a one course
 * 					refer exact a one session of exam
 *					have zero or many students inscribed ? *** 
 * 					
 * 					something else?
 * 
 * @functions
 * 	when a message is put on group, the event send a mail a each member of a group, So each member can 
 * 	choose if decider receive a notification or not	
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="GROUP_TABLE")
public class Group {

	
	public Group(String name, String object, String description,
			Integer levelOfPolicy, Professor creator) {
		this.name = name;
		this.object = object;
		this.description = description;
		this.levelOfPolicy = levelOfPolicy;
		this.creator = creator;
	}

	/**
	 * only the user that have created it can publish
	 */
	public static final Integer POLICY_1=1;
	/**
	 * only the user can publish and the other user can comment
	 */
	public static final Integer POLICY_2=2;
	/**
	 * both creator's user and other can publish, And of course comments 
	 */
	public static final Integer POLICY_3=3;
//	public static final Integer POLITIC_4=4;
	
	@Id
	@GeneratedValue
	@Column(name="GROUP_ID")
	Long id;
	
	
	@Column(name="NAME")
	String name;
	
	@Column(name="OBJECT")
	String object;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@Column(name="POLICY")
	Integer levelOfPolicy;
	
	@ManyToOne
	@JoinColumn(name="USER_GRUOP")
	User creator;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="GROUP_USERS", 
	joinColumns={
			@JoinColumn(name="GROUP_ID")
	}, 
	inverseJoinColumns={
			@JoinColumn(name="ID")
	})
	Set<User>iscribed=new HashSet<User>();
	
	/**
	 * per inserire un nuovo messaggio devo utilizzare 
	 * messages.add(0,MessageOfGruop);
	 * in questo modo l'elemento viene aggiunto all'inizio della lista
	 * e quindi per prenderlo devo utilizzare 
	 * messages.get(0);
	 * 
	 */
	@OneToMany
	@JoinTable(name="GROUP_MESSAGE",
	joinColumns={
			@JoinColumn(name="GROUP_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="MESSAGE_OF_GROUP_ID")
			})
	List<MessageOfGroup>messages=new ArrayList<MessageOfGroup>();

	//IMPLEMENTATION	
	
	@Override
	public String toString() {
		return "Name: "+name+"; Creator: "+creator.name;
	}
	
	public Group() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLevelOfPolicy() {
		return levelOfPolicy;
	}

	public void setLevelOfPolicy(Integer levelOfPolicy) {
		this.levelOfPolicy = levelOfPolicy;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<MessageOfGroup> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageOfGroup> messages) {
		this.messages = messages;
	}

	public Set<User> getIscribed() {
		return iscribed;
	}

	public void setIscribed(Set<User> iscribed) {
		this.iscribed = iscribed;
	}

	
	
}
