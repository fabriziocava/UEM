package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.util.HibernateUtil;


@Repository
public class ExamSessionDAOimpl implements ExamSessionDAO {

	@Override
	public ArrayList<ExamSession> getExamSessionsFromProfessor(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<ExamSession> res = null;
		try {
			Professor prof=(Professor)session.get(Professor.class, id);
			Query q = session.createQuery("from ExamSession where degreecourse.id=:p");
			q.setParameter("p", prof.getDepartment_associated().getId());
			@SuppressWarnings("unchecked")
			List<ExamSession> list = q.list();
			res = new ArrayList<ExamSession>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}
	
	@Override
	public Long addExamSession(ExamSession examsession) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			id = (Long) session.save(examsession);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}
	
	
	
	@Override
	public Long addExamSession(String description,Date dataInizio, Date dataFine,
			DegreeCourse degreecourseAssociated) {

		  Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = null;
          Long id = null;
          try {
              transaction = session.beginTransaction();
              ExamSession e=new ExamSession(description,dataInizio, dataFine, degreecourseAssociated);
              id = (Long) session.save(e);
              transaction.commit();
      } catch (Exception e) {
              transaction.rollback();
      } finally {
              session.close();
      }
      return id;
	}

	@Override
	public boolean canRegisterCourse(Course c) {

		  Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = null;
          try {
              transaction = session.beginTransaction();
              DegreeCourse d=c.getDegreeCourse();
              Date datacorrente=new Date();
              Set<ExamSession> examsession=getExamsession();
              for (ExamSession examSession2 : examsession) {
				
            	  if(examSession2.getDegreecourse()==d)
            		  if(examSession2.getDataInizio().getTime()>=datacorrente.getTime() && examSession2.getDataFine().getTime()<=datacorrente.getTime())
            			  return true;
			}
              transaction.commit();
              
      } catch (Exception e) {
              transaction.rollback();
      } finally {
              session.close();
      }
      return false;
	}

	@Override
	public Set<ExamSession> getExamsession() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<ExamSession> res = null;
		try {
			Query q = session.createQuery("from ExamSession");
			@SuppressWarnings("unchecked")
			List<ExamSession> list = q.list();
			res = new HashSet<ExamSession>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}



	@Override
	public ExamSession removeExamSession(Long idexamsession) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		ExamSession res=null;
		try{
			transaction=session.beginTransaction();
			ExamSession es=(ExamSession)session.get(ExamSession.class, idexamsession);
			session.delete(es);
			res=es;

			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}



	@Override
	public ExamSession getExamsession(Long id) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ExamSession res=null;
		try{
			res=(ExamSession)session.get(ExamSession.class, id);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}



	@Override
	public ExamSession modifyExamSession(Long id, ExamSession newexamsession) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		ExamSession res=null;
		try{
			transaction=session.beginTransaction();
			ExamSession oldexamsession=(ExamSession) session.get(ExamSession.class, id);
			if(newexamsession.getDescription()!=null && newexamsession.getDescription().compareTo(oldexamsession.getDescription())!=0)
				oldexamsession.setDescription(newexamsession.getDescription());
			if(newexamsession.getDataInizio()!=null && newexamsession.getDataInizio().compareTo(oldexamsession.getDataInizio())!=0)
				oldexamsession.setDataInizio(newexamsession.getDataInizio());
			if(newexamsession.getDataFine()!=null && newexamsession.getDataFine().compareTo(oldexamsession.getDataFine())!=0)
				oldexamsession.setDataFine(newexamsession.getDataFine());
			res=oldexamsession;
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}



	@Override
	public Set<ExamSession> getExamsessionfromdepartment(Department department) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<ExamSession> res = null;
		try {
			Query q= session.createQuery("from ExamSession where department_department_id=:par");
			q.setParameter("par", department.getId());
			@SuppressWarnings("unchecked")
			List<ExamSession> list = q.list();
			res = new HashSet<ExamSession>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}



	@Override
	public Set<ExamSession> getExamsessionfromDegreeCourse(
			DegreeCourse degreecourse) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<ExamSession> res = null;
		try {
			Query q= session.createQuery("from ExamSession where degreecourse_degree_course_id=:par");
			q.setParameter("par", degreecourse.getId());
			@SuppressWarnings("unchecked")
			List<ExamSession> list = q.list();
			res = new HashSet<ExamSession>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}






}
