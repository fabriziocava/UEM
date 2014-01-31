package it.unical.uniexam.hibernate.fabrizioTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.impl.DegreeCourseDAOImpl;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDAOImpl;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.utility.Address;

public class DBTestDegreeCourseDAO {
	
	private static DepartmentDAO departmentDAO = new DepartmentDAOImpl();
	private static DegreeCourseDAO degreeCourseDAO = new DegreeCourseDAOImpl();
	
	private static Long idDepartment = null;
	
	@BeforeClass
	public static void prepareDB() {
		Department department = new Department("A1", "MATEMATICA E INFORMATICA", new Address("COSENZA", "ITALY", "87100", "VIA PIETRO BUCCI, 56"));
		idDepartment = departmentDAO.addDepartment(department);
		Long idDegreeCourse = degreeCourseDAO.addDegreeCourse("INFORMATICA", department);
	}
	
	@Test
	public void checkDegreeCourseOfADepartment() {
		assertTrue(degreeCourseDAO.getDegreeCourses(idDepartment).size()==1);
	}
	
	@Test
	public void checkDegreeCourseOfAllDepartmets() {
		assertTrue(degreeCourseDAO.getDegreeCourses().size()==1);
	}
	
}
