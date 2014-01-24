package it.unical.uniexam.hibernate.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.util.HibernateUtil;

@Repository
public class DegreeCourseDAOImpl implements DegreeCourseDAO {

	@Override
	public Long addDegreeCourse(String name, Department department_associated) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			DegreeCourse dc = new DegreeCourse(name, department_associated);
			id  = (Long) session.save(dc);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}
	
	@Override
	public Long addDegreeCourse(DegreeCourse degreeCourse) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			id = (Long) session.save(degreeCourse);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public Set<DegreeCourse> getDegreeCourses(Long idDepartment) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<DegreeCourse> res = null;
		try {
			Query q= session.createQuery("from DegreeCourse where department_associated_department_id=:par");
			q.setParameter("par", idDepartment);
			@SuppressWarnings("unchecked")
			List<DegreeCourse> list = q.list();
			res = new HashSet<DegreeCourse>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Set<DegreeCourse> getDegreeCourses(Department department) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<DegreeCourse> res = null;
		try {
			Query q= session.createQuery("from DegreeCourse where department_associated_department_id=:par");
			q.setParameter("par", department.getId());
			@SuppressWarnings("unchecked")
			List<DegreeCourse> list = q.list();
			res = new HashSet<DegreeCourse>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Set<DegreeCourse> getDegreeCourses() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<DegreeCourse> res = null;
		try {
			Query q = session.createQuery("from DegreeCourse");
			@SuppressWarnings("unchecked")
			List<DegreeCourse> list = q.list();
			res = new HashSet<DegreeCourse>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public DegreeCourse getDegreeCourse(Long id) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		DegreeCourse res=null;
		try{
			res=(DegreeCourse)session.get(DegreeCourse.class, id);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

}
