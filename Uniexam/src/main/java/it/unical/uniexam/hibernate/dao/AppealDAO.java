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
	public Long addAppeal(Long idCourse, String name,
			Integer maxNumberOfInscribed, String location, Date examDate, Date openDate, Date closeDate,
			Long idProfessor);
	
	public Appeal removeAppeal(Long idAppeal);
	public Appeal removeAppeal(Appeal appeal);
	public Set<Appeal> removeAllAppealsFromProfessor(Long idProfessor);
	
	public Appeal modifyAppeal(Long idAppeal,Appeal appealNew);
	
	//advanced
	public Set<Appeal> getAppealsFromProfessor(Long idProfessor);
//	public Set<Appeal> getAppeals(Long DegreeCourse);
	public Set<Appeal> getAppeals();
	public Set<Student> getStudetsIscribed(Long idAppeal);
	
	public Appeal setProfessor(Long idAppeal,Professor professor);
	public Appeal setProfessor(Long idAppeal,Long idProfessor);
	
	public Appeal addStudentAtAppeal(Long idAppeal,Long idStudent);
	public Appeal addStudentAtAppeal(Long idAppeal,Student student);
	
	public boolean removeStudentAtAppeal(Long idAppeal,Long idStudent);
	public boolean removeStudentAtAppeal(Long idAppeal,Student student);
	
}
