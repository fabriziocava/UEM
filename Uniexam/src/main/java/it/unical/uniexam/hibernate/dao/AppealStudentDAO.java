package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;

import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.AppealStudent.STATE;

public interface AppealStudentDAO {
	
	Long addAppealStudent(AppealStudent appealStudent);
	Long addAppealStudent(AppealStudent.STATE state,Long idAppeal,Long idStudent,
			String note,Double vote);
	Boolean modifyVote(Long idAppeal, String value);
	Boolean modifyNote(Long idAppeal, String value);
	Boolean removeAppealStudents(ArrayList<Long> idAppealStudents, Long idProfessor);
	Boolean prepareAppealStudentsForSign(ArrayList<Long> prepareStudents, Long idProfessor);
	String signAppealStudentsByProfessor(ArrayList<Long> signStudents,Long idProfessor);
	Boolean declassifyStudents(ArrayList<Long> listAppealStudents,
			Long idProfessor);
	Boolean prepareForSignAppealStudent(AppealStudent appealStudent,
			Long idProfessor);
	ArrayList<ArrayList<Object>> getListStudentFromProfessorRegularAndNotForState(
			Long idProfessor, STATE notSignedByProfessor);
	ArrayList<ArrayList<Object>> getListStudentFromProfessorRegularAndNotForCommissionar(
			Long idProfessor, STATE state);
	Boolean signAppealStudentsByCommissary(ArrayList<Long> signStudents,
			Long idProfessor);
}

