package it.unical.uniexam.hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//	Professor professor = new Professor();
//	professor.setName("AOPDJ");
//	professor.setSurname("cognome");
//	Professor professor2 = new Professor();
//	professor2.setName("asfsad");
//	professor2.setSurname("cogsadsadnome");
//	professors.add(professor);
//	professors.add(professor2);
	List<Professor>professors=new ArrayList<Professor>();
	Address address = new Address();
	address.setCity("Acri");
	address.setState("ITALY");
	address.setStreet("Europa");
	address.setZip("83843");
	Long idDep =departmentDao.addDepartment("12", "Informatica",professors, address);
	
	professorDao.addProfessor("mok", "klui", idDep);
	professorDao.addProfessor("madsfdok2", "lDsdff", idDep);
	
}

@Test
public void prooova(){
	assertTrue(departmentDao.listDepartment().size()==2);
//	departmentDao.removeDepartment(new Long(1));
//	Set<Professor> listProfessors = professorDao.listProfessors();
//	System.out.println("iniziooooo");
//	for (Professor p: listProfessors) {
//		System.out.println(p.toString());
//	}
//	System.out.println("fiiiine");
}

}
