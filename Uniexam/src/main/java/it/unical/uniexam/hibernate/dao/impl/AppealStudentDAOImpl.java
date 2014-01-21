package it.unical.uniexam.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.AppealStudent.STATE;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class AppealStudentDAOImpl implements AppealStudentDAO {

	@Override
	public Long addAppealStudent(AppealStudent appealStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			
			if(appealStudent.getAppeal().getId()==null)
				return null;
			if(appealStudent.getStudent().getId()==null)
				return null;
			if(appealStudent.getState()==null)
				appealStudent.setState(AppealStudent.STATE.NOT_SIGNED_BY_PROFESSOR);
			
			Appeal app=(Appeal)session.get(Appeal.class, appealStudent.getAppeal().getId());
			Student stud=(Student)session.get(Student.class, appealStudent.getStudent().getId());
			
			app.getAppeal_student().add(appealStudent);
			stud.getAppeal_student().add(appealStudent);
			
			id=(Long)session.save(appealStudent);	
			
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			id=null;
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Long addAppealStudent(STATE state, Long idAppeal, Long idStudent,
			String note, Double vote) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			
			
			
			if(idAppeal==null)
				return null;
			if(idStudent==null)
				return null;
			Appeal app=(Appeal)session.get(Appeal.class, idAppeal);
			Student stud=(Student)session.get(Student.class, idStudent);

			AppealStudent appealStudent=new AppealStudent(app, stud, state, vote, note);
			
			if(state==null)
				appealStudent.setState(AppealStudent.STATE.NOT_SIGNED_BY_PROFESSOR);
			
			app.getAppeal_student().add(appealStudent);
			stud.getAppeal_student().add(appealStudent);
			
			id=(Long)session.save(appealStudent);	
			
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			id=null;
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

}
