package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
import it.unical.uniexam.hibernate.util.HibernateUtil;

@Repository
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Manager> getManagerFromDepartment(Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager getManager(Long idManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager removeManager(Long idManager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addPhoneNumber(Long idManager, PhoneNumber number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removePhoneNumber(Long idManager, Long idPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePhoneNumber(Long idManager, PhoneNumber idPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<PhoneNumber> getPhoneNumbers(Long idManager) {
		// TODO Auto-generated method stub
		return null;
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
	

}
