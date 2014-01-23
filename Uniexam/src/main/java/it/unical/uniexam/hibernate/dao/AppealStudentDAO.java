package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Student;

public interface AppealStudentDAO {
	
	Long addAppealStudent(AppealStudent appealStudent);
	Long addAppealStudent(AppealStudent.STATE state,Long idAppeal,Long idStudent,
			String note,Double vote);
	void subscriptionToAppel(Appeal appeal, Student student);
	ArrayList<AppealStudent> getAppealStudent(Long idStudent);
	
}
