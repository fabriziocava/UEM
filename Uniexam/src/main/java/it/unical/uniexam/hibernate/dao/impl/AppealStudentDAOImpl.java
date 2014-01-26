package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.AppealStudent.STATE;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */

@Repository
public class AppealStudentDAOImpl implements AppealStudentDAO {

	@Override
	public Boolean prepareAppealStudentsForSign(ArrayList<Long> prepareStudents) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			for (Long idAppeal : prepareStudents) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				appealStudent.setState(STATE.NOT_SIGNED_BY_PROFESSOR);
			}

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

	
	@Override
	public Boolean removeAppealStudents(ArrayList<Long> idAppeals) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			for (Long idAppeal : idAppeals) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				Student s=appealStudent.getStudent();
				Appeal a=appealStudent.getAppeal();
				s.getAppeal_student().remove(appealStudent);
				a.getAppeal_student().remove(appealStudent);
				session.delete(appealStudent);
			}

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

	@Override
	public Boolean modifyNote(Long idAppeal, String value) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
			appealStudent.setNote(value);

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

	@Override
	public Boolean modifyVote(Long idAppeal, String valu) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();
			AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
			Double value=Double.valueOf(valu);
			appealStudent.setTemporany_vote(value);

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

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
				appealStudent.setState(AppealStudent.STATE.NO_STATE);

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
				appealStudent.setState(AppealStudent.STATE.NO_STATE);

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
