package it.unical.uniexam.hibernate.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.Address;

/**
 * 
 * @author luigi
 *
 */

@Repository
public class DepartmentDAOImpl implements DepartmentDAO {

	@Override
	public Long addDepartment(String code, Professor president, String name,
			Address address, List<Professor> professors) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Department> getDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department getDepartment(Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department removeDepartment(Long idDepartment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department removeDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Department modifyDepartment(Long idDepartment,
			Department departmentNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addProfessorAtDepartment(Long idDepartment, Professor professor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long removeProfessorAtDepartment(Long idDepartment, Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveProfessorFromDepartmentToDepartment(Long idProfessor,
			Long idDepartmentOld, Long idDepartmentNew) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Professor setPresident(Professor president) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor setPresident(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
//Session session =HibernateUtil.getSessionFactory().openSession();
//Transaction transaction=null;
//try{
//	transaction=session.beginTransaction();
//	
//	
//	transaction.commit();
//}catch(Exception e){
//	transaction.rollback();
//}finally{
//	session.close();
//}
//return null;
