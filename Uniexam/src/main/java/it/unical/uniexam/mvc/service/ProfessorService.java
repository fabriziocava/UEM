package it.unical.uniexam.mvc.service;

import java.util.ArrayList;
import java.util.List;

import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;

public interface ProfessorService extends UserService{
	public final static String PROFESSOR_HOME="professor/home";
	public final static String PROFESSOR_QUERY_ID="idProfessor";
	public final static String PROFESSOR_OBJECT="profObj";
	
	public Professor getProfessor(Long idUser);

	public ArrayList<CommentOfPost> getNotificationFromComments(List<Long> noReadComments);

}
