package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Student;

public interface AppealStudentDAO {
	
	Long addAppealStudent(AppealStudent appealStudent);
	Long addAppealStudent(AppealStudent.STATE state,Long idAppeal,Long idStudent,
			String note,Double vote);
	ArrayList<AppealStudent> getAppealStudent(Long idStudent);
	AppealStudent removeAppealStudent(Long idAppealStudent);
    Boolean prepareAppealStudentsForSign(ArrayList<Long> prepareStudents);
    ArrayList<AppealStudent> getAppealStudentForCarrier(Long idStudent);
    ArrayList<AppealStudent> getAppealStudentForVerbalToBeSigned(Long idStudent);
    
}
