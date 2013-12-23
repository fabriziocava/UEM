package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.DepartmentDao;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class DepartmentDaoImpl implements DepartmentDao {

	@Override
	public Long addDepartment(String code,String name,List<Professor> professors,Address address) {
		try{
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Department d=new Department();
			d.setCode(code);
			d.setName(name);
			d.setAddress(address);
			
			id=(Long) session.save(d);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		
		}catch(Exception e){e.printStackTrace();}
		return null;
	}

	@Override
	public List listDepartment() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		List<Professor> arrayList = new ArrayList<Professor>();
		Long id=null;
		try{
			transaction=session.beginTransaction();
//			Department d=(Department) session.get(Department.class, new Long(1));
//			for (Professor professor : arrayList) {
//				System.out.println(professor.toString());
			System.out.println("io");
//			}
			transaction.commit();
			
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
		return arrayList;
	}

	@Override
	public void removeDepartment(Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		List<Professor> arrayList = new ArrayList<Professor>();
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Department d=(Department) session.get(Department.class, new Long(idDepartment));
			session.delete(d);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
		}finally{
			session.close();
		}
	}

}
