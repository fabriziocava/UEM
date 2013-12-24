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
@Table(name="MESSAGE_OF_GROUP")
public class MessageOfGroup {

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
	
}
