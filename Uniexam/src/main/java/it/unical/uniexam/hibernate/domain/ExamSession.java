package it.unical.uniexam.hibernate.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="EXAM_SESSION")
public class ExamSession {

	@Id
	@Column(name="EXAM_SESSION_ID")
    @GeneratedValue
    Long id;
	
	@Column(name="DATA_INIZIO", nullable=false)
    Date dataInizio;
	
	@Column(name="DATA_FINE", nullable=false)
    Date dataFine;
	
	 @OneToMany(fetch=FetchType.LAZY)
     @JoinTable(name="SESSION_APPEALS",
     joinColumns={
                     @JoinColumn(name="EXAM_SESSION_ID")
     }, 
     inverseJoinColumns={
                     @JoinColumn(name="APPEAL_ID")
     })
	Set<Appeal> appelli=new HashSet<Appeal>();
	 
	 @OneToOne
	 DegreeCourse degreecourse;
	
	public ExamSession(){
		
	}
	
	public ExamSession(Date dataInizio,Date dataFine,DegreeCourse degreecourseAssociated){
		
		this.dataInizio=dataInizio;
		this.dataFine=dataFine;
		this.degreecourse=degreecourseAssociated;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Set<Appeal> getAppelli() {
		return appelli;
	}

	public void setAppelli(Set<Appeal> appelli) {
		this.appelli = appelli;
	}

	public DegreeCourse getDegreecourse() {
		return degreecourse;
	}

	public void setDegreecourse(DegreeCourse degreecourse) {
		this.degreecourse = degreecourse;
	}

	
	
}
