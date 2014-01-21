package it.unical.uniexam.mvc.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.User;


public interface ManagerService extends UserService {
	
	public final static String MANAGER_HOME="manager/home";
	public final static String MANAGER_QUERY_ID="idManager";
	public static final String MANAGER_IMAGE = "manager/image";
	public static final String MANAGER_ACCOUNT = "manager/account";
	public static final String MANAGER_UPLOAD ="manager/upload";
	public static final String MANAGER_EXAM ="manager/exam_session";
	public static final String MANAGER_COURSE ="manager/course";
	public static final String MANAGER_COURSELIST= "manager/course/list";
	
	public Manager getManager(Long idUser);
	public Boolean streamImage(Manager m, OutputStream outputStream);
	public void putImage(Manager m, InputStream is,int length);
	public Map<String, String> getPersonalizzationValues(Long id);
	public void updatePersonalizzationValues(String stringValues,Long id);
	public Set<DegreeCourse> getAssociatedCourseWithDepartment(Long id);
	public Set<ExamSession> getExamSession();
	public ExamSession getExamsession(Long id);

}
