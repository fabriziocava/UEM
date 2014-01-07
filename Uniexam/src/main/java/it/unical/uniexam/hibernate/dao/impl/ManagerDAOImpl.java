package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class ManagerDAOImpl implements ManagerDao {

	@Override
	public Long addManager(String name, String surname, URL webSite,
			 String password, Address address) {
		
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			
//			Manager manager=new Manager(name, surname, webSite, password, address);
			/**
			 * Aggiungere il dipartimento di appartenenza se non nullo
			 */
//			id=(Long) session.save(manager);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
		
	}

	@Override
	public Long addManager(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Manager> getManagers() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Manager>res=null;
		try{
			Query q= session.createQuery("from Manager");
			@SuppressWarnings("unchecked")
			List<Manager> list = q.list();
			res=new HashSet<Manager>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Manager> getManagerFromDepartment(Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Manager>res=null;
		try{
			Query q= session.createQuery("from Manager where department_associated=:par");
			q.setParameter("par", idDepartment);
			@SuppressWarnings("unchecked")
			List<Manager> list = q.list();
			res=new HashSet<Manager>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Manager getManager(Long idManager) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Manager res=null;
		try{
			res=(Manager)session.get(Manager.class, idManager);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Manager removeManager(Long idManager) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Manager res=null;
		Transaction transaction=null;
		try{
			transaction=session.beginTransaction();
			res=(Manager)session.get(Manager.class, idManager);
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

	@Override
	public Long addPhoneNumber(Long idManager, PhoneNumber number) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Manager m=(Manager)session.get(Manager.class, idManager);
			PhoneNumber removable=null;
			for (PhoneNumber e : m.getPhoneNumbers()) {
				if(e.getType().equals(number.getType())){
					removable=e;
				}
			}
			if(removable!=null){
				m.getPhoneNumbers().remove(removable);
				session.delete(removable);
			}
			m.getPhoneNumbers().add(number);
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
	public PhoneNumber removePhoneNumber(Long idManager, Long idPhoneNumber) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		PhoneNumber res=null;
		try{
			transaction = session.beginTransaction();

			Manager m=(Manager)session.get(Manager.class, idManager);
			PhoneNumber pn=(PhoneNumber)session.get(PhoneNumber.class, idPhoneNumber);
			m.getPhoneNumbers().remove(pn);
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

	@Override
	public void removePhoneNumber(Long idManager, PhoneNumber idPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<PhoneNumber> getPhoneNumbers(Long idManager) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<PhoneNumber> res=null;
		try{
			Manager m=(Manager)session.get(Manager.class, idManager);
			res=new HashSet<PhoneNumber>(m.getPhoneNumbers());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public PhoneNumber getPhoneNumber(Long idManager, String type) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		PhoneNumber res=null;
		try{
			Manager m=(Manager)session.get(Manager.class, idManager);
			for (PhoneNumber pn : m.getPhoneNumbers()) {
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
	public Long setDepartmentAssociated(Long idManager, Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setDepartmentAssociated(Long idManager, Department department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long addEmail(Long idManager, Email email) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Manager m=(Manager)session.get(Manager.class, idManager);
			Email removable=null;
			for (Email e : m.getEmails()) {
				if(e.getType().equals(email.getType())){
					removable=e;
				}
			}
			if(removable!=null){
				m.getEmails().remove(removable);
				session.delete(removable);
			}
			m.getEmails().add(email);
			email.setUser(m);
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
	public Email removeEmail(Long idManager, Long idEmail) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Email res=null;
		try{
			transaction = session.beginTransaction();

			Manager m=(Manager)session.get(Manager.class, idManager);
			Email email=(Email)session.get(Email.class, idEmail);
			m.getEmails().remove(email);
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
	public Set<Email> getEmails(Long idManager) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Email> res=null;
		try{
			Manager m=(Manager)session.get(Manager.class, idManager);
			res=new HashSet<Email>(m.getEmails());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Email getEmail(Long idManager, String type) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Email res=null;
		try{
			Manager m=(Manager)session.get(Manager.class, idManager);
			for (Email email : m.getEmails()) {
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


	

}
