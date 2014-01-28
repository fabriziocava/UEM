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

@Repository
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
			
			app.setCurrNumberOfInscribed(app.getCurrNumberOfInscribed()+1);
			
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

	@Override
	public AppealStudent removeAppealStudent(Long idAppealStudent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AppealStudent appealStudent = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			appealStudent = (AppealStudent) session.get(AppealStudent.class, idAppealStudent);
			Student student = (Student) session.get(Student.class, appealStudent.getStudent().getId());
			Appeal appeal = (Appeal) session.get(Appeal.class, appealStudent.getAppeal().getId());
			student.getAppeal_student().remove(appealStudent);
			appeal.getAppeal_student().remove(appealStudent);
			
			appeal.setCurrNumberOfInscribed(appeal.getCurrNumberOfInscribed()-1);
			if(appeal.getCurrNumberOfInscribed()<0)
				appeal.setCurrNumberOfInscribed(0);
			
			session.delete(appealStudent);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			appealStudent = null;
			new MokException(e);
		} finally {
			session.close();
		}
		return appealStudent;
	}
	
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppealStudent> getAppealStudentForCarrier(Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=null;
		try{
			Query q=session.createQuery("from AppealStudent where student.id = :studentId and state <> :appealState");
			q.setParameter("studentId", idStudent);
			q.setParameter("appealState", AppealStudent.STATE.NO_STATE);
			res=new ArrayList<AppealStudent>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public ArrayList<AppealStudent> getAppealStudentForVerbalToBeSigned(
			Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=null;
		try{
			Query q=session.createQuery("from AppealStudent where student.id = :studentId and state = :appealState");
			q.setParameter("studentId", idStudent);
			q.setParameter("appealState", AppealStudent.STATE.AWAITING_ACKNOWLEDGMENT);
			res=new ArrayList<AppealStudent>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public boolean setState(ArrayList<Long> idAppealStudentList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Boolean ris = false;
        try {
        	transaction=session.beginTransaction();
        	for (Long idAppeal : idAppealStudentList) {
        		AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
        		appealStudent.setState(STATE.LOADED_IN_SECRETERY);
        	}
        	transaction.commit();
        	ris=true;
        } catch(Exception e){
        	new MokException(e);
        	ris=false;
        	transaction.rollback();
        } finally {
        	session.close();
        }
        return ris;
	}

}
