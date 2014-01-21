package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.mvc.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author fabrizio
 *
 */

@Service
public class StudentServiceImpl extends UserServiceImpl implements StudentService {

	@Autowired
	StudentDAO studentDAO;
	@Autowired
	CourseDAO courseDAO;
	@Autowired
	AppealDAO appealDAO;
	
	
	@Override
	public Student getStudent(Long serialNumber) {
		return studentDAO.getStudent(serialNumber);
	}
	
	@Override
	public ArrayList<Course> getCourses() {
		return courseDAO.getCourses();
	}
	
	@Override
	public Course getCourseDetails(Long idCourse) {	
		return courseDAO.getCourse(idCourse);
	}

	@Override
	public ArrayList<Appeal> getAppeal(Long idCourse) {
		ArrayList<Appeal>app = new ArrayList<Appeal>(appealDAO.getAppeals(idCourse));
		ArrayList<Appeal>removable = new ArrayList<Appeal>();
		for (Appeal appeal : app) {
			if(appeal.getCourse()!=null && appeal.getCourse().getId()!=-1){
				removable.add(appeal);
			}
		}
		app.removeAll(removable);
		Collections.sort(app, new Comparator<Appeal>(){
			@Override
			public int compare(Appeal o1, Appeal o2) {
				if(o1!=null && o2!=null)
					return (int) (o2.getExamDate().getTime()-o1.getExamDate().getTime());
				return 0;
			}
		});
		return app;
	}
	
}
