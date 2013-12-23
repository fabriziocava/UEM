package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name="Session")
public class Session {

	@Id
	@GeneratedValue
	@Column(name="SESSION_ID")
	Long id;
}
