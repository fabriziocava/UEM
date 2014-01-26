package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;

import it.unical.uniexam.hibernate.domain.AppealStudent;

public interface AppealStudentDAO {
	
	Long addAppealStudent(AppealStudent appealStudent);
	Long addAppealStudent(AppealStudent.STATE state,Long idAppeal,Long idStudent,
			String note,Double vote);
	Boolean modifyVote(Long idAppeal, String value);
	Boolean modifyNote(Long idAppeal, String value);
	Boolean removeAppealStudents(ArrayList<Long> idAppealStudents);
	Boolean prepareAppealStudentsForSign(ArrayList<Long> prepareStudents);
	
}

