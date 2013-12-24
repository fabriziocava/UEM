package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.Set;

import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;

public class AppealDAOImpl implements AppealDAO {

	@Override
	public Long addAppeal(Appeal appeal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addAppeal(Course course, String name,
			Integer maxNumberOfInscribed, String location, Date examDate,
			Date openDate, Date closeDate, Professor creatorProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addAppeal(Course course, String name,
			Integer maxNumberOfInscribed, String location, Date examDate,
			Date openDate, Date closeDate, Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal removeAppeal(Long idAppeal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal removeAppeal(Appeal appeal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Appeal> removeAppeals(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal modifyAppeal(Long idAppeal, Appeal appealNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal setProfessor(Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal setProfessor(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal addStudentAtAppeal(Long idAppeal, Long idStudent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal addStudentAtAppeal(Long idAppeal, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal removeStudentAtAppeal(Long idAppeal, Long idStudent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Appeal removeStudentAtAppeal(Long idAppeal, Student student) {
		// TODO Auto-generated method stub
		return null;
	}

}
