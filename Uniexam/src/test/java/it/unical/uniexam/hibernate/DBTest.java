package it.unical.uniexam.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.uniexam.hibernate.dao.DepartmentDao;
import it.unical.uniexam.hibernate.dao.ProfessorDao;
import it.unical.uniexam.hibernate.dao.impl.DepartmentDaoImpl;
import it.unical.uniexam.hibernate.dao.impl.ProfessorDaoImp;
import it.unical.uniexam.hibernate.domain.Address;
import it.unical.uniexam.hibernate.domain.Professor;

import static org.junit.Assert.*;

public class DBTest {
private static DepartmentDao departmentDao=new DepartmentDaoImpl();
private static ProfessorDao professorDao=new ProfessorDaoImp();


@BeforeClass
public static void prepareBD(){
	List<Professor>professors=new ArrayList<Professor>();
	Professor professor = new Professor();
	professor.setName("AOPDJ");
	professor.setSurname("cognome");
	professors.add(professor);
	departmentDao.addDepartment("12", "INFormatica",professors, new Address());
}

@Test
public void prooova(){
	assertTrue(departmentDao.listDepartment().size()==0);
}

}
