package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;

import java.util.Date;
import java.util.Set;

public interface AppealDAO {

	//standard
	public Long addAppeal(Appeal appeal);
	public Long addAppeal(Course course, String name,
			Integer maxNumberOfInscribed, String location, Date examDate, Date openDate, Date closeDate,
			Professor creatorProfessor);
	public Long addAppeal(Course course, String name,
			Integer maxNumberOfInscribed, String location, Date examDate, Date openDate, Date closeDate,
			Long idProfessor);
	
	public Appeal removeAppeal(Long idAppeal);
	public Appeal removeAppeal(Appeal appeal);
	public Set<Appeal> removeAppeals(Long idProfessor);
	
	public Appeal modifyAppeal(Long idAppeal,Appeal appealNew);
	
	//advanced
	
	public Appeal setProfessor(Professor professor);
	public Appeal setProfessor(Long idProfessor);
	
	public Appeal addStudentAtAppeal(Long idAppeal,Long idStudent);
	public Appeal addStudentAtAppeal(Long idAppeal,Student student);
	
	public Appeal removeStudentAtAppeal(Long idAppeal,Long idStudent);
	public Appeal removeStudentAtAppeal(Long idAppeal,Student student);
	
}
