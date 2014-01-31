package it.unical.uniexam.hibernate.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.EventsCalendar;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;


/**
 * 
 * @author luigi
 *
 */
@Repository
public class ProfessorDAOImp implements ProfessorDAO {

	@Override
	public ArrayList<Professor> getProfessorsMatch(String id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Professor> res = null;
		try {
			Query q = session.createQuery("from Professor where lower(name) like :id or lower(surname) like :id2");
			q.setParameter("id", "%"+id.toLowerCase()+"%");
			q.setParameter("id2", "%"+id.toLowerCase()+"%");
			@SuppressWarnings("unchecked")
			List<Professor> list = q.list();
			for (Professor professor : list) {
				Hibernate.initialize(professor);
				Hibernate.initialize(professor.getEmails());
			}
			res = new ArrayList<Professor>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}
	
	@Override
	public ArrayList<Appeal> getAppeals(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Appeal> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new ArrayList<Appeal>(p.getAppeals());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	
	@Override
	public EventsCalendar getEvents(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			ObjectInputStream oj =new ObjectInputStream(p.getEventsCalendar().getBinaryStream());
			EventsCalendar eventsCalendar=null;
			try {
				eventsCalendar=(EventsCalendar)oj.readObject();
			}catch(Exception e){
				new MokException(e);
				return null;
			}finally {
				try{
					oj.close();
				}catch(Exception e){
					new MokException(e);
					return null;
				}
			}
			return eventsCalendar;
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return null;
	}
	@Override
	public Boolean setEvents(Long idProfessor,EventsCalendar eventsCalendar) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			try {
				out = new ObjectOutputStream(bos);   
				out.writeObject(eventsCalendar);
				byte[] yourBytes = bos.toByteArray();
				Blob b=session.getLobHelper().createBlob(yourBytes);
				p.setEventsCalendar(b);
			} finally {
				try {
					if (out != null) {
						out.close();
					}
				} catch (Exception e) {
					new MokException(e);
					throw new Exception("Primo catch");
				}
				try {
					bos.close();
				} catch (Exception e) {
					new MokException(e);
					throw new Exception("Secondo catch");
				}
			}
			transaction.commit();
			return true;
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return false;
	}

	/**
	 * Last modification is this!
	 */

	@Override
	public Long addProfessor(String name, String surname, URL webSite,
			String password, Address address, Set<Email> emails,
			Set<PhoneNumber> phoneNumbers, Department department_associated) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();

			Professor p=new Professor(User.TYPE.PROFESSOR, name, surname, webSite, password, address, emails, phoneNumbers, department_associated);
			for (Email email : emails) {
				email.setUser(p);
			}
			/**
			 * Aggiungere il dipartimento di appartenenza se non nullo
			 * if(p.getDepartment_associated()!=null){do}
			 */
			id=(Long) session.save(p);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Deprecated
	@Override
	public Long addProfessor(Professor professor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			/**
			 * Aggiungere il dipartimento di appartenenza se non nullo
			 * if(professor.getDepartment_associated()!=null){do}
			 */
			for (Email email : professor.getEmails()) {
				email.setUser(professor);
			}
			id=(Long) session.save(professor);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Set<Professor> getProfessors() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Professor>res=null;
		try{
			Query q= session.createQuery("from Professor");
			@SuppressWarnings("unchecked")
			List<Professor> list = q.list();
			res=new HashSet<Professor>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Professor> getProfessorsFromDepartment(Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Professor>res=null;
		try{
			Query q= session.createQuery("from Professor where department_associated=:par");
			q.setParameter("par", idDepartment);
			@SuppressWarnings("unchecked")
			List<Professor> list = q.list();
			res=new HashSet<Professor>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Professor getProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Professor res=null;
		try{
			res=(Professor)session.get(Professor.class, idProfessor);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Professor removeProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Professor res=null;
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			res=(Professor)session.get(Professor.class, idProfessor);
			session.delete(res);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	//	@Override
	//	public Set<Professor> getSetProfessors() {
	//		Session session =HibernateUtil.getSessionFactory().openSession();
	//		Set<Professor>res=null;
	//		try{
	//			Query q= session.createQuery("from Professor");
	//			@SuppressWarnings("unchecked")
	//			List<Professor> list = q.list();
	//			res=new HashSet<Professor>(list);
	//		}catch(Exception e){
	//			new MokException(e);
	//		}finally{
	//			session.close();
	//		}
	//		return res;
	//	}

	//	@Override
	//	public Set<Professor> getSetProfessorsFromDepartment(Long idDepartment) {
	//		return null;
	//	}

	@Override
	public Long addPhoneNumber(Long idProfessor, PhoneNumber number) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			PhoneNumber removable=null;
			for (PhoneNumber e : p.getPhoneNumbers()) {
				if(e.getType().equals(number.getType())){
					removable=e;
				}
			}
			if(removable!=null){
				p.getPhoneNumbers().remove(removable);
				session.delete(removable);
			}
			p.getPhoneNumbers().add(number);
			res=(Long)session.save(number);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public PhoneNumber removePhoneNumber(Long idProfessor, Long idPhoneNumber) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		PhoneNumber res=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			PhoneNumber pn=(PhoneNumber)session.get(PhoneNumber.class, idPhoneNumber);
			p.getPhoneNumbers().remove(pn);
			session.delete(pn);
			res=pn;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	//	@Override
	//	public void removePhoneNumber(Long idProfessor, PhoneNumber idPhoneNumber) {
	//
	//	}

	@Override
	public Set<PhoneNumber> getPhoneNumbers(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<PhoneNumber> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<PhoneNumber>(p.getPhoneNumbers());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public PhoneNumber getPhoneNumber(Long idProfessor, String type) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		PhoneNumber res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (PhoneNumber pn : p.getPhoneNumbers()) {
				if(pn.getType().equals(type)){
					res=pn;
					break;
				}
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Long setDepartmentAssociated(Long idProfessor, Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setDepartmentAssociated(Long idProfessor,
			Department department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Course> getCourseHolder(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Course> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Hibernate.initialize(p.getSetHoldersCourse());// FOR LAZY LOAD
			res=p.getSetHoldersCourse();
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getCourseCommission(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Course> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=p.getSetAsCommission();
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getSetCourseAsHolder(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Course>(p.getSetHoldersCourse());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getSetCourseAsCommission(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Course>(p.getSetAsCommission());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Long addEmail(Long idProfessor, Email email) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Email removable=null;
			for (Email e : p.getEmails()) {
				if(e.getType().equals(email.getType())){
					removable=e;
				}
			}
			if(removable!=null){
				p.getEmails().remove(removable);
				session.delete(removable);
			}
			p.getEmails().add(email);
			email.setUser(p);
			res=(Long)session.save(email);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Email removeEmail(Long idProfessor, Long idEmail) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Email res=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Email email=(Email)session.get(Email.class, idEmail);
			p.getEmails().remove(email);
			session.delete(email);
			res=email;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Email> getEmails(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Email> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Email>(p.getEmails());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Email getEmail(Long idProfessor, it.unical.uniexam.hibernate.domain.utility.Email.TYPE type) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Email res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (Email email : p.getEmails()) {
				if(email.getType().equals(type)){
					res=email;
					break;
				}
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	//
	//	@Override
	//	public void getNoReadComments(Long idProfessor) {
	//		Session session =HibernateUtil.getSessionFactory().openSession();
	//		try{
	//			Professor p=(Professor)session.get(Professor.class, idProfessor);
	//			p.getNoReadComments();
	//		}catch(Exception e){
	//			new MokException(e);
	//		}finally{
	//			session.close();
	//		}
	//	}

	@Override
	public Boolean streamImage(Long idProfessor,OutputStream outputStream) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Blob fileimage = p.getFileimage();
			if(fileimage!=null){
				InputStream is = fileimage.getBinaryStream();
				if (is != null) {
					FileCopyUtils.copy(is, outputStream);
					res=true;
				}
			}

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	//	public void storeImage(Long idProfessor,InputStream is,int length){
	//		Session session =HibernateUtil.getSessionFactory().openSession();
	//		Transaction transaction=null;
	//		try{
	//			transaction = session.beginTransaction();
	//
	//			Professor p=(Professor)session.get(Professor.class, idProfessor);
	//			{
	//				//			File resume = new File("D:\\Resume.doc");
	//				byte[] fileContent = new byte[length];
	//				try {
	//					//				FileInputStream fileInputStream = new FileInputStream(resume);
	//					//				//convert file into array of bytes
	//					//				fileInputStream.read(fileContent);
	//					//				fileInputStream.close();
	//					is.read(fileContent);
	//					is.close();
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//				//		     Blob blob = Hibernate.createBlob(fileContent,session);
	//				Blob blob= session.getLobHelper().createBlob(fileContent);
	//				p.setFileimage(blob);
	//			}
	//
	//			transaction.commit();
	//		}catch(Exception e){
	//			transaction.rollback();
	//			new MokException(e);
	//		}finally{
	//			session.close();
	//		}
	//	}

	public void storeImage2(Long idProfessor,InputStream is,int length){
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		try{
			transaction = session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			//			byte[] data=new byte[length];
			Blob b=session.getLobHelper().createBlob(is, length);
			p.setFileimage(b);
			//			FileCopyUtils.copy(is, p.getFileimage().setBinaryStream(1));

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
	}
}
