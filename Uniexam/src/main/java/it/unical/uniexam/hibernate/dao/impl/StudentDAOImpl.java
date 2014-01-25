package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.StudentDAO;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
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
	public Long addStundent(String name, String surname, String fiscalCode,
			String password, Address address, HashSet<Email> emails,
			HashSet<PhoneNumber> numbers, Long degreeCourse_registered, String serialNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			DegreeCourse degreeCourse=(DegreeCourse)session.get(DegreeCourse.class, degreeCourse_registered);
			Student s = new Student(User.TYPE.STUDENT, name, surname, fiscalCode, password, address, emails, numbers, degreeCourse, serialNumber);
			for(Email email : emails) {
				email.setUser(s);
			}
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
	public ArrayList<Student> getStudentsMatch(String idStud) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Student> res = null;
		try {
			Query q = session.createQuery("from Student where lower(name) like :id or lower(serialNumber) like :id2 or lower(surname) like :id3");
			q.setParameter("id", "%"+idStud.toLowerCase()+"%");
			q.setParameter("id2", "%"+idStud.toLowerCase()+"%");
			q.setParameter("id3", "%"+idStud.toLowerCase()+"%");
//			Criteria c=session.createCriteria(Student.class);
//			Disjunction or = Restrictions.disjunction();
//			or.add(Restrictions.ilike("name",idStud));
//			or.add(Restrictions.ilike("surname",idStud));
//			or.add(Restrictions.ilike("serialNumber",idStud));
//			Disjunction or = Restrictions.disjunction();
//			or.add(Restrictions.like("name",idStud,MatchMode.ANYWHERE));
//			or.add(Restrictions.like("surname",idStud,MatchMode.ANYWHERE));
//			or.add(Restrictions.like("serialNumber",idStud,MatchMode.ANYWHERE));
//			c.add(Restrictions.like("name", idStud, MatchMode.ANYWHERE));
//			c.add(Restrictions.like("surname", idStud, MatchMode.ANYWHERE));
			@SuppressWarnings("unchecked")
			List<Student> list = q.list();
			res = new ArrayList<Student>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}
	
	@Override
	public Long addStudent(Student student) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			for(Email email : student.getEmails()) {
				email.setUser(student);
			}
			id = (Long) session.save(student);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return id;
	}
	
	@Override
	public Long addStundent(String name, String surname, String fiscalCode, String password,
			Address address, Set<Email> emails, Set<PhoneNumber> phoneNumbers,
			DegreeCourse degreeCourse_registered, String serialNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Long id = null;
		try {
			transaction = session.beginTransaction();
			Student s = new Student(User.TYPE.STUDENT, name, surname, fiscalCode, password, address, emails, phoneNumbers, degreeCourse_registered, serialNumber);
			for(Email email : emails) {
				email.setUser(s);
			}
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
			//res = new HashSet<Course>(s.getCarrier()); CORREGGERE
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
