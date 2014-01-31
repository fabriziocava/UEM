package it.unical.uniexam.mvc.service;

import java.util.ArrayList;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;

public interface StudentService extends UserService {
	public static final String STUDENT_HOME="student/home";
	public static final String STUDENT_QUERY_ID="idStudent";
	public static final String STUDENT_OBJECT="studentObj";
	public static final String STUDENT_COURSE = "student/course";
	public static final String STUDENT_APPEAL = "student/appeal";
	public static final String STUDENT_GROUP = "student/group";
	public static final String STUDENT_CARRIER = "student/carrier";
	public static final String STUDENT_VERBALTOBESIGNED = "student/verbalToBeSigned";
	
	public Student getStudent(Long serialNumber);
	public ArrayList<Course> getCourses(Long idStudent);
	
	public Course getCourseDetails(Long idCourse);
	public ArrayList<Appeal> getAppeals(Long idCourse);
	public Boolean inscriptionToAppeal(Long idAppeal, Long idStudent);
	public Boolean removeInscriptionToAppeal(Long idAppealStudent);
	public ArrayList<AppealStudent> getAppealStudentList(Long idStudent);
	public ArrayList<AppealStudent> getAppealStudentForCarrier(Long idStudent);
	public ArrayList<AppealStudent> getAppealStudentForVerbalToBeSigned(Long idStudent);
	public boolean setStateAppealStudent(ArrayList<Long> idAppealStudentList);
	public Set<Group> getGroups();
	public ArrayList<Carrier> getCarrier(Long idStudent);
	public Set<RequestedCourse> getRequestCourse(Long idCourse);
	public Appeal getAppeal(Long idAppeal);
	public AppealStudent getAppealStudent(Long idAppealStudent);
}
