package it.unical.uniexam.mvc.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.mvc.service.ManagerService;

@Service
public class ManagerServiceImpl extends UserServiceImpl implements ManagerService{

	@Autowired
	ManagerDao managerDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	DegreeCourseDAO degreecourseDAO;
	
	@Autowired
	ExamSessionDAO examsessionDAO;
	
	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public Manager getManager(Long idUser) {
		return managerDAO.getManager(idUser);
	}


	@Override
	public Boolean streamImage(Manager m, OutputStream outputStream) {
		return managerDAO.streamImage(m.getId(), outputStream);
	}

	@Override
	public void putImage(Manager m, InputStream is, int length) {
		 managerDAO.storeImage2(m.getId(), is, length);
		
	}


	@Override
	public Map<String, String> getPersonalizzationValues(Long id) {

		return userDAO.getPersonalization(id);
	}


	@Override
	public void updatePersonalizzationValues(String personalizzation, Long id) {
		userDAO.updatePersonalizzation(personalizzation, id);
		
	}


	@Override
	public Set<DegreeCourse> getAssociatedCourseWithDepartment(Long id) {
		return degreecourseDAO.getDegreeCourses(id);
	}


	@Override
	public Set<ExamSession> getExamSession() {
		return examsessionDAO.getExamsession();
	}


	@Override
	public ExamSession getExamsession(Long id) {
		return examsessionDAO.getExamsession(id);
	}


	@Override
	public Boolean addExamsession(ExamSession es) {
		return examsessionDAO.addExamSession(es.getDescription(), es.getDataInizio(), es.getDataFine(), es.getDegreecourse()) != null;
	}


	@Override
	public Boolean removeExamsession(Long id) {
		return examsessionDAO.removeExamSession(id)!=null;
	}


	@Override
	public Boolean changeExamSessionField(Long idexamsession, String variable,
			String value, String clazz) {

		ExamSession examsession=new ExamSession(null, null, null, null);
		try{
			Object valuee=null;
			value=value.replaceAll("\n", "");
			if(clazz.equals("Integer")){
				valuee=Integer.valueOf(value);
			}else if(clazz.equals("String")){
				valuee=String.valueOf(value);
			}else if(clazz.equals("Date")){
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd kk:mm");
				Date result =  df.parse(value);
				valuee=result;
			}
			Class<ExamSession> loadClass=ExamSession.class;
			Field declaredField = loadClass.getDeclaredField(variable);
			declaredField.setAccessible(true);
			declaredField.set(examsession, valuee);
		}catch (Exception e) {
			new MokException(e);
			return false;
		}
		
		examsessionDAO.modifyExamSession(idexamsession, examsession);
		return true;
	}


	@Override
	public Set<ExamSession> getExamsessionfromdepartment(Department department) {
		return examsessionDAO.getExamsessionfromdepartment(department);
	}


	@Override
	public Set<ExamSession> getExamSessionfromDegreeCourse(
			DegreeCourse degreecourse) {
		return examsessionDAO.getExamsessionfromDegreeCourse(degreecourse);
	}


	@Override
	public ArrayList<Course> getCourses() {
		return courseDAO.getCourses();
	}

	
	

}
