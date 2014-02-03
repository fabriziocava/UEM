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
 * improved by mok
 */

@Entity
@Table(name="APPEAL_STUDENT")
public class AppealStudent {
        
        public enum STATE {
                NO_STATE,
                NOT_SIGNED_BY_PROFESSOR, 
                NOT_SIGNED_BY_COMMISSARY, 
                AWAITING_ACKNOWLEDGMENT, 
                LOADED_IN_SECRETERY;
        }
        
        /*
         * CONSTRUCTORS
         */
        
        
        public AppealStudent() {
        }
        /*
         * END_CONSTRUCTORS
         */
        
        public AppealStudent(Appeal appeal, Student student, STATE state,
                        Double temporany_vote, String note) {
                super();
                this.appeal = appeal;
                this.student = student;
                this.state = state;
                this.temporany_vote = temporany_vote;
                this.note = note;
        }

        @Id
        @Column(name="AS_ID", nullable=false)
        @GeneratedValue
        Long id;

        @ManyToOne
        Appeal appeal;

        @Column(name="DATE_PRENOTATION")
        Date datePrenotation;
        
        @Column(name="DATE_EXAM")
        Date date;
        
        @ManyToOne
        Student student;
        
        @Column(name="STATE")
        STATE state;
        
        @Column(name="TEMPORANY_VOTE")
        Double temporany_vote;
        
        @Column(name="NOTE")
        String note;

        ////NEED FOR THE SINGLESIGN
        @ManyToOne
        Course course;
        
        @ManyToOne
        Professor professor;
        ////NEED FOR THE SINGLESIGN
        
        public String getNote() {
                return note;
        }

        public void setNote(String note) {
                this.note = note;
        }

        /*
         * GETTERS
         */
        public Long getId() {
                return id;
        }

        public Appeal getAppeal() {
                return appeal;
        }

        public Student getStudent() {
                return student;
        }

        public STATE getState() {
                return state;
        }

        public Double getTemporany_vote() {
                return temporany_vote;
        }
        /*
         * END_GETTERS
         */

        /*
         * SETTERS
         */
        public void setId(Long id) {
                this.id = id;
        }

        public void setAppeal(Appeal appeal) {
                this.appeal = appeal;
        }

        public void setStudent(Student student) {
                this.student = student;
        }

        public void setState(STATE state) {
                this.state = state;
        }

        public void setTemporany_vote(Double temporany_vote) {
                this.temporany_vote = temporany_vote;
        }
        /*
         * END_SETTERS
         */

        public Professor getProfessor() {
                return professor;
        }

        public void setProfessor(Professor professor) {
                this.professor = professor;
        }

        public Course getCourse() {
                return course;
        }

        public void setCourse(Course course) {
                this.course = course;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((id == null) ? 0 : id.hashCode());
                result = prime * result + ((state == null) ? 0 : state.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                AppealStudent other = (AppealStudent) obj;
                if (id == null) {
                        if (other.id != null)
                                return false;
                } else if (!id.equals(other.id))
                        return false;
                if (state != other.state)
                        return false;
                return true;
        }

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Date getDatePrenotation() {
			return datePrenotation;
		}

		public void setDatePrenotation(Date datePrenotation) {
			this.datePrenotation = datePrenotation;
		}
        
        
        
}
