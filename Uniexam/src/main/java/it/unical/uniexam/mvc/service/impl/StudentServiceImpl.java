package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.Set;

import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.dao.CarrierDAO;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Group;
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
	@Autowired
	AppealStudentDAO appealStudentDAO;
	@Autowired
	GroupDAO groupDAO;
	@Autowired
	CarrierDAO carrierDAO;
	
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
//		ArrayList<Appeal>removable = new ArrayList<Appeal>();
//		for (Appeal appeal : app) {
//			if(appeal.getCourse()!=null && appeal.getCourse().getId()!=-1){
//				removable.add(appeal);
//			}
//		}
//		app.removeAll(removable);
//		Collections.sort(app, new Comparator<Appeal>(){
//			@Override
//			public int compare(Appeal o1, Appeal o2) {
//				if(o1!=null && o2!=null)
//					return (int) (o2.getExamDate().getTime()-o1.getExamDate().getTime());
//				return 0;
//			}
//		});
//		return app;
		return appealDAO.getAppeals(idCourse);
	}

	@Override
	public Boolean inscriptionToAppeal(Long idAppeal, Long idStudent) {
		Long id = appealStudentDAO.addAppealStudent(AppealStudent.STATE.NO_STATE, idAppeal, idStudent, null, null);
		if(id!=null)
			return true;
		return false;
	}

	@Override
	public ArrayList<AppealStudent> getAppealStudent(Long idStudent) {
		return appealStudentDAO.getAppealStudent(idStudent);
	}
	
	@Deprecated
	@Override
	public ArrayList<AppealStudent> getAppealStudentForCarrier(Long idStudent) {
		return appealStudentDAO.getAppealStudentForCarrier(idStudent);
	}

	@Override
	public ArrayList<AppealStudent> getAppealStudentForVerbalToBeSigned(
			Long idStudent) {
		return appealStudentDAO.getAppealStudentForVerbalToBeSigned(idStudent);
	}
	
	@Override
	public Boolean removeInscriptionToAppeal(Long idAppealStudent) {
		AppealStudent appealStudent = appealStudentDAO.removeAppealStudent(idAppealStudent);
		if(appealStudent!=null)
			return true;
		return false;
	}
	
	@Override
	public boolean setStateAppealStudent(ArrayList<Long> idAppealStudentList) {
		return appealStudentDAO.setState(idAppealStudentList);
	}

	@Override
	public Set<Group> getGroups() {
		return groupDAO.getGroups();
	}

	@Override
	public ArrayList<Carrier> getCarrier(Long idStudent) {
		return carrierDAO.getCarrierList(idStudent);
	}
	
}
