package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.utility.Address;

public class DBTestDepartmentDAO {
	
	private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	
	@BeforeClass
	public static void prepareDB() {
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		Long idDepartment = departmentDAO.addDepartment(department);
	}
	
	@Test
	public void checkDepartment() {
		assertTrue(departmentDAO.getDepartments().size()==1);
	}
	
}
