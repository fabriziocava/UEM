package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.CarrierDAO;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class CarrierDAOImpl implements CarrierDAO {

	@Override
	public Long addCarrier(Long idCourse, Long idStudent, Integer vote, Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			Course course = (Course) session.get(Course.class, idCourse);
			Student student = (Student) session.get(Student.class, idStudent);
			Carrier carrier = new Carrier(course, student, vote, date);
			id = (Long)session.save(carrier);
			transaction.commit();
		} catch (Exception e) {
			new MokException(e);
			id = null;
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Carrier> getCarrierList(Long idStudent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Carrier> res = null;
		try {
			Query q = session.createQuery("from Carrier where student.id=:studentId");
			q.setParameter("studentId", idStudent);
			res = new ArrayList<Carrier>(q.list());
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

}
