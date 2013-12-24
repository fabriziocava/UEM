package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @category Event 
 * 
 * This class describe a Group
 * each Group have a code, ( map<Date,MessageOfGroup> messages OR List<MessageOfGroup>) 
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
	
//	@ManyToOne
//	Professor creator;
	
}
