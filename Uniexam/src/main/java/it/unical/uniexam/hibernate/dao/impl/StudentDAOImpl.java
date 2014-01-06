package it.unical.uniexam.hibernate.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.StudentDAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Override
	public Long addStundent(String name, String surname, String password,
			Address address, Email email, Set<PhoneNumber> phoneNumbers,
			DegreeCourse degreeCourse_registered, Long serialNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			Student s = new Student(User.TYPE.STUDENT, name, surname, password, address, email, phoneNumbers, degreeCourse_registered, serialNumber);
			email.setUser(s);
			id = (Long) session.save(s);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}

	@Override
	public Set<Student> getStudents() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Set<Student> res = null;
		try {
			Query q = session.createQuery("from Student");
			@SuppressWarnings("unchecked")
			List<Student> list = q.list();
			res = new HashSet<Student>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Student getStudent(Long serialNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Student res = null;
		try {
			res = (Student) session.get(Student.class, serialNumber);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getCarrier(Long serialNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HashSet<Course> res = null;
		try {
			Student s = (Student) session.get(Student.class, serialNumber);
			res = new HashSet<Course>(s.getCarrier());
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Set<Group> getGroups(Long serialNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		HashSet<Group> res = null;
		try {
			Student s = (Student) session.get(Student.class, serialNumber);
			res = new HashSet<Group>(s.getGroups());
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}
	
}