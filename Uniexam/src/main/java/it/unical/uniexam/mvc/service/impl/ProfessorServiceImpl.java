package it.unical.uniexam.mvc.service.impl;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
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
	@Autowired
	AppealDAO appealDAO;
	@Autowired
	AppealStudentDAO appealStudentDAO;
	@Autowired
	StudentDAO studentDAO;
	
	@Override
	public Boolean removeStudentToAppeal(Long idAppeal) {
		return appealStudentDAO.removeAppealStudent(idAppeal);
	}
	
	@Override
	public Boolean addStudentToAppeal(Long idAppeal, Long idStudent) {
		return appealStudentDAO.addAppealStudent(null, idAppeal, idStudent, null, null)!=null;
	}
	
	@Override
	public ArrayList<Student> getStudentsMatch(String idStud) {
		return studentDAO.getStudentsMatch(idStud);
	}
	
	@Override
	public Boolean modifyAppealStudent(Long idAppeal, String variable,
			String value) {
		if(variable.equals("temporany_vote")){
			return appealStudentDAO.modifyVote(idAppeal,value);
		}else if(variable.equals("note")){
			return appealStudentDAO.modifyNote(idAppeal,value);
		}

		return false;
	}
	
//	@Override
//	public ArrayList<ArrayList<RequestedCourse>> getListOfRequestedCourseFromListStudentAndAppeal(
//			Long idAppeal, ArrayList<AppealStudent> appealStudentsNoRegular) {
//		
//		return null;
//	}
	
	@Override
	public ArrayList<ArrayList<Object>> getListStudentFromAppealRegularAndNot(Long idAppeal) {
		return appealDAO.getListStudentFromAppealRegularAndNot(idAppeal);
	}
	
	@Override
	public Boolean removeAppeal(Long idAppeal) {
		return appealDAO.removeAppeal(idAppeal)!=null;
	}
	
	@Override
	public Boolean changeAppealAttribute(Long idAppeal, String variable,String value,String clazz) {
		Appeal ap=new Appeal(null, null, null, null, null, 
				null, null, null, null);
		try {
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
			Class<Appeal> loadClass = Appeal.class;
			Field declaredField = loadClass.getDeclaredField(variable);
			declaredField.setAccessible(true);
			declaredField.set(ap, valuee);
		} catch (Exception e) {
			new MokException(e);
			return false;
		}
		appealDAO.modifyAppeal(idAppeal, ap);
		return true;
	}
	
	@Override
	public Appeal getAppealGround(Long idAppeal) {
		return appealDAO.getAppealGround(idAppeal);
	}
	
	@Override
	public Appeal getAppealDetails(Long idAppeal) {
		return appealDAO.getAppealDetails(idAppeal);
	}
	
	@Override
	public List<Appeal> getAppealWithoutCourse(Long id) {
		ArrayList<Appeal>app=new ArrayList<Appeal>(appealDAO.getAppealsFromProfessorDetails(id));
		ArrayList<Appeal>removable=new ArrayList<Appeal>();
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
	
	@Override
	public List<Course> getCourseAssociated(Long id) {
		return new ArrayList<Course>(professorDAO.getCourseHolder(id));//°°°°TODOMOK
	}
	
	@Override
	public Boolean addAppeal(Professor p, Appeal appeal) {
		return appealDAO.addAppeal(appeal.getCourse().getId(),appeal.getName(),
				appeal.getMaxNumberOfInscribed(),appeal.getLocation(),appeal.getDescription()
				, appeal.getExamDate(), appeal.getOpenDate(),appeal.getCloseDate()
				,p.getId())!=null;
	}
	
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
			Session session=null;
			Transaction transaction=null;
			try{
				session=courseDAO.getSession();
				transaction=session.beginTransaction();
				for(String item:items){

					String []elems=item.split(",");
					if(elems!=null && elems.length>1){
						if(elems[1].equals("change")){
							if(!courseDAO.modifyDegreeRequestedCourse(idCourse, Long.valueOf(elems[0]), elems[2],session))
								//								return false;
								throw new Exception();
						}else if(elems[1].equals("remove")){
							if(courseDAO.removeRequestedCourse(idCourse, Long.valueOf(elems[0]))==null)
								//								return false;
								throw new Exception();
						}
					}

				}
				transaction.commit();
			}catch(Exception e){
				new MokException(e);
				res=false;
				transaction.rollback();
			}finally{
				session.close();
			}
		}else{
			System.out.println("inatteso");
			res=false;
		}
		return res;
	}

	@Override
	public Set<Course> getCoursesForRequestedCourseFromDepartment(Long idCourse) {
		return  new HashSet<Course>(courseDAO.getCoursesFromDepartment(courseDAO.getDepartmentFromCourse(idCourse)));
	}

	@Override
	public Boolean addRequestedCourse(Long idCourse,RequestedCourse requestedCourse) {
		return courseDAO.addRequestedCourse(idCourse, requestedCourse.getCourse().getId(), requestedCourse.getPolicyOfRequested());
	}

	@Override
	public List<List<Object>> getStructureCourse_Appeal(Long p) {
		return appealDAO.getStructureCourse_Appeal(p);
	}
}
