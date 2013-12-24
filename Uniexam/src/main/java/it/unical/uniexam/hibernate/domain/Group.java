package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

	
	@Id
	@GeneratedValue
	@Column(name="GROUP_ID")
	Long id;
	
	@ManyToOne
	@JoinColumn(name="PROFESSOR_GRUOP")
	Professor creator;
	
	@Column(name="NAME")
	String name;
	
	@Column(name="OBJECT")
	String object;
	
	@Column(name="DESCRIPTION")
	String description;
	
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

	public Group() {
	}

	
}
