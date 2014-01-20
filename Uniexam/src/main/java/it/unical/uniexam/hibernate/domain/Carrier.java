package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * 
 * @author fabrizio
 *
 */

@Entity
@Table(name="CARRIER")
@PrimaryKeyJoinColumn(name="CARRIER_ID")
public class Carrier {

	@Id
	@Column(name="CARRIER_ID", nullable=false)
	@GeneratedValue
	Long id;
	
	@ManyToOne
	Course course;
	
	@ManyToOne
	Student student;
	
	@Column(name="VOTE")
	int vote;
	
}
