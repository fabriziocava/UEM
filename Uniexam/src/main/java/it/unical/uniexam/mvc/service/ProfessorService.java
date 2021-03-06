package it.unical.uniexam.mvc.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.EventsCalendar;

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
	
	public final static String PROFESSOR_APPEAL="professor/appeal";
	
	public Professor getProfessor(Long idUser);

	public ArrayList<CommentOfPost> getNotificationFromComments(List<Long> noReadComments);

	public ArrayList<Course> getAssociatedCourseWithGroups(User user);

	public void updatePersonalizzationValues(String stringValues,Long id);

	public Map<String, String> getPersonalizzationValues(Long id);

	public void changeNote(Long idCourse, String parameter);

	public Boolean streamImage(Professor p, OutputStream outputStream);
	
	public void putImage(Professor p, InputStream is,int length);

	public Course getCourseDetails(Professor p, Long idCourse);

	public Boolean applyCommandForRequestedCourse(Long idCourse, String commands);

	public Set<Course> getCoursesForRequestedCourseFromDepartment(Long idCourse);

	public Boolean addRequestedCourse(Long idCourse,RequestedCourse requestedCourse);

	public List<List<Object>> getStructureCourse_Appeal(Long p);

	public Boolean addAppeal(Professor p, Appeal appeal);

	public List<Course> getCourseAssociated(Long id);

	public List<Appeal> getAppealWithoutCourse(Long id);

	public Appeal getAppealDetails(Long idAppeal);

	public Boolean changeAppealAttribute(Long idAppeal, String variable,String value, String clazz);

	public Boolean removeAppeal(Long idAppeal);


	public Boolean modifyAppealStudent(Long idAppeal, String variable,
			String value);

	public ArrayList<ArrayList<Object>> getListStudentFromAppealRegularAndNot(Long idAppeal);

	public Appeal getAppealGround(Long idAppeal);

	public ArrayList<Student> getStudentsMatch(String idStud);

	public Boolean addStudentToAppeal(Long idAppeal, Long idStudent);

	public Boolean removeStudentsToAppeal(ArrayList<Long> removeStudents, Long idProfessor);

	public ArrayList<Appeal> getAppealsMatch(Long idPorfessor,String appealString);

	public ArrayList<ArrayList<Object>> getAppealStudentsForPrepareSign(Long idProfessor,Long idAppeal);

	public Boolean applyPrepareAppealStudent(ArrayList<Long> prepareStudents, Long idProfessor);

	public ArrayList<ArrayList<Object>> getAppealStudentsForSignFromAppeal(Long idProfessor,Long idAppeal);

	public Boolean declassifyStudents(ArrayList<Long> listAppealStudents, Long idProfessor);

	public String signAppealStudentsList(ArrayList<Long> listSignAppealStudents, Long id);

	public Boolean addPrepareSign(AppealStudent appealStudent,Long idProfessor);

	public ArrayList<ArrayList<Object>> getAppealStudentsForSign(Long idProfessor);

	public ArrayList<ArrayList<Object>> getAppealStudentsForSignAdCommission(
			Long id);

	public ArrayList<Professor> getListCommissionary(Long idCourse);

	public boolean signAppealStudentsListByCommissionary(
			ArrayList<Long> listSignAppealStudents, Long id);

	public EventsCalendar getEventsFromProfessor(Long id);

	public Boolean setEventsByProfessor(Long id, EventsCalendar events);

	public ArrayList<Appeal>getAppealFromProfessor(Long idProfessor);

	public ArrayList<Professor> getProfessorsMatch(String id);

	public Boolean addCommissaryAtCourse(Long idCourse, Long id);

	public Boolean removeCommissaryAtCourse(Long idCourse, Long idProf);

	public ArrayList<ExamSession> getExamsSessions(Long id);
	
//	public ArrayList<ArrayList<RequestedCourse>> getListOfRequestedCourseFromListStudentAndAppeal(
//			Long idAppeal, ArrayList<AppealStudent> appealStudentsNoRegular);

}
