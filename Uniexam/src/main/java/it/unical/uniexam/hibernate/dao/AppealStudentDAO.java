package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.AppealStudent;

public interface AppealStudentDAO {
	
	Long addAppealStudent(AppealStudent appealStudent);
	Long addAppealStudent(AppealStudent.STATE state,Long idAppeal,Long idStudent,
			String note,Double vote);
	Boolean modifyVote(Long idAppeal, String value);
	Boolean modifyNote(Long idAppeal, String value);
	Boolean removeAppealStudent(Long idAppeal);
	
}

