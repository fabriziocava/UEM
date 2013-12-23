package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @category Structure 
 * 
 * This class describe a Degree Course (CDL)
 * each DC have a code, a name; 
 * 		and belongs at an arrangement (ordinamento), and a department
 * At each DC we have a number of course and a number of student inscribed 
 * 
 * @author luigi
 *
 */
@Entity
@Table(name="DEGREE_COURSE")
public class DegreeCourse {
	
	@Id
	@GeneratedValue
	@Column(name="DEGREE_COURSE_ID")
	Long id;
	
	
	
}
