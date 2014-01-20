package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.ExamSessionDAO;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ExamSessionDAOimpl implements ExamSessionDAO {

	@Override
	public Long addExamSession(Date dataInizio, Date dataFine,
			DegreeCourse degreecourseAssociated) {

		  Session session = HibernateUtil.getSessionFactory().openSession();
          Transaction transaction = null;
          Long id = null;
          try {
              transaction = session.beginTransaction();
              ExamSession e=new ExamSession(dataInizio, dataFine, degreecourseAssociated);
              id = (Long) session.save(e);
              transaction.commit();
      } catch (Exception e) {
              transaction.rollback();
      } finally {
              session.close();
      }
      return id;
	}

}
