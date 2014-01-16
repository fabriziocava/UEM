package it.unical.uniexam.mvc.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.mvc.service.ProfessorService;

/**
 * 
 * @author luigi
 *
 */
@Service
public class ProfessorServiceImpl extends UserServiceImpl implements ProfessorService {

	@Autowired
	ProfessorDAO professorDAO;
	@Autowired
	GroupDAO groupDAO;
	@Autowired
	CourseDAO courseDAO;
	@Autowired
	UserDAO userDAO;

	@Override
	public Professor getProfessor(Long idUser) {
		return professorDAO.getProfessor(idUser);
	}

	@Override
	public ArrayList<CommentOfPost> getNotificationFromComments(
			List<Long> noReadComments) {
		ArrayList<CommentOfPost>res=new ArrayList<CommentOfPost>();
		for (Long idComment : noReadComments) {
			res.add(groupDAO.getComment(idComment));
		}
		return res;
	}

	@Override
	public ArrayList<Course> getAssociatedCourseWithGroups(User user) {
		return courseDAO.getAssociatedCourseWithGroups(user);
	}

	@Override
	public void updatePersonalizzationValues(String personalizzation,Long id) {
		userDAO.updatePersonalizzation(personalizzation, id);
	}

	@Override
	public Map<String, String> getPersonalizzationValues(Long id) {
		return userDAO.getPersonalization(id);
	}

	@Override
	public void changeNote(Long idCourse, String parameter) {
		courseDAO.setNote(idCourse, parameter);
	}

	@Override
	public Boolean streamImage(Professor p, OutputStream outputStream) {
		return professorDAO.streamImage(p.getId(), outputStream);
	}

	@Override
	public void putImage(Professor p, InputStream is,int length) {
		professorDAO.storeImage2(p.getId(), is,length);
	}

	@Override
	public Course getCourseDetails(Professor p, Long idCourse) {
		Course res=courseDAO.getCourse(idCourse);
		if(res.getHolder().getId()==p.getId()){
			res=courseDAO.getCourseAll(idCourse);
		}else{
			//sto accedendo ad un corso e non sono il docente titolare
			//quindi carica solo il necessario alla visualizzazione
		}
		return res;
	}

	/**
	 * caso remove/change 1:2,remove,no:3,change,strong:
	 */
	@Override
	public Boolean applyCommandForRequestedCourse(Long idCourse, String commands) {
		Boolean res=true;
		Course c=courseDAO.getCourse(idCourse);
		String []items=commands.split(":");
		if(items[0].equals(String.valueOf(c.getId()))){
			for(String item:items){
				try{
					String []elems=item.split(",");
					if(elems!=null && elems.length>1){
						if(elems[1].equals("change")){
							if(!courseDAO.modifyDegreeRequestedCourse(idCourse, Long.valueOf(elems[0]), elems[2]))
								return false;
						}else if(elems[1].equals("remove")){
							if(courseDAO.removeRequestedCourse(idCourse, Long.valueOf(elems[0]))==null)
								return false;
						}
					}
				}catch(Exception e){new MokException(e);res=false;}
			}
		}else{
			System.out.println("inatteso");
			res=false;
		}
		return res;
	}

}
