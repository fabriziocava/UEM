package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
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

	@Override
	public void subscriptionToAppel(Appeal appeal, Student student) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			AppealStudent as = new AppealStudent(appeal, student, null, null, null);
			session.save(as);
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppealStudent> getAppealStudent(Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=null;
		try{
			Query q=session.createQuery("from AppealStudent where student.id=:studentId");
			q.setParameter("studentId", idStudent);
			res=new ArrayList<AppealStudent>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
}
