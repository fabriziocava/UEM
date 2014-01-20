package it.unical.uniexam.hibernate.dao;

import java.util.Date;

import it.unical.uniexam.hibernate.domain.DegreeCourse;



public interface ExamSessionDAO {
	
	public Long addExamSession(Date dataInizio,Date dataFine,DegreeCourse degreecourseAssociated );
	

}
