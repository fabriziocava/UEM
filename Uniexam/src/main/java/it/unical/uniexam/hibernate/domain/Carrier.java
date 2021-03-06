package it.unical.uniexam.hibernate.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author fabrizio
 *
 */

@Entity
@Table(name="CARRIER")
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
        Integer vote;
        
        @Column(name="DATE")
        Date date;
        
        public Carrier() {

        }
        
        public Carrier(Course course, Student student, Integer vote, Date date) {
        	this.course = course;
        	this.student = student;
        	this.vote = vote;
        	this.date = date;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Course getCourse() {
                return course;
        }

        public void setCourse(Course course) {
                this.course = course;
        }

        public Student getStudent() {
                return student;
        }

        public void setStudent(Student student) {
                this.student = student;
        }

        public Integer getVote() {
                return vote;
        }

        public void setVote(Integer vote) {
                this.vote = vote;
        }

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}
                
}
