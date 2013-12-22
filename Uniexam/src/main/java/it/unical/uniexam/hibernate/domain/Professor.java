package it.unical.uniexam.hibernate.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @category Actor 
 * 
 * This class describe the actor Professor
 * each Professor extend User class, in addition have a set of a phone numbers
 * At each Professor 	is associated with a department
 * 						have many Exams (appelli)
 * 						have many Exams like commission (commissario) ?
 * 						have many Course as holder (titolare)	
 * 						have many Groups like a creator					
 * 
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="PROFESSOR")
public class Professor {

	
}
