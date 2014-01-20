package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ExamSessionDAOimpl implements ExamSessionDAO {

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

}
