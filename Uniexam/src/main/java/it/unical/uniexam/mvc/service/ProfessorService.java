package it.unical.uniexam.mvc.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;

public interface ProfessorService extends UserService{
	public final static String PROFESSOR_HOME="professor/home";
	public final static String PROFESSOR_QUERY_ID="idProfessor";
	public final static String PROFESSOR_OBJECT="profObj";
	public static final String PROFESSOR_COURSE = "professor/course";
	public static final String PROFESSOR_ACCOUNT = "professor/account";
	public static final String PROFESSOR_PERSONALIZZATION = "professor/personalizzation";
	public static final String PROFESSOR_CHANGE_NOTE = "professor/changeNote";
	public static final String PROFESSOR_IMAGE = "professor/image";
	public static final String PROFESSOR_UPLOAD ="professor/upload";
	
	public Professor getProfessor(Long idUser);

	public ArrayList<CommentOfPost> getNotificationFromComments(List<Long> noReadComments);

	public ArrayList<Course> getAssociatedCourseWithGroups(User user);

	public void updatePersonalizzationValues(String stringValues,Long id);

	public Map<String, String> getPersonalizzationValues(Long id);

	public void changeNote(Long idCourse, String parameter);

	public Boolean streamImage(Professor p, OutputStream outputStream);
	
	public void putImage(Professor p, InputStream is,int length);
}
