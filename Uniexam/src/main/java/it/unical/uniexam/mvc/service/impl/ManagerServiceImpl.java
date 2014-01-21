package it.unical.uniexam.mvc.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
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

	

}
