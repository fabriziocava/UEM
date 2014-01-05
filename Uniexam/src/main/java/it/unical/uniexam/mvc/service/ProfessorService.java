package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.domain.Professor;

public interface ProfessorService extends UserService{
	public final static String PROFESSOR_HOME="professor/home";
	public final static String PROFESSOR_QUERY_ID="professor";
	
	public Professor getProfessor(Long idUser);
	
}
