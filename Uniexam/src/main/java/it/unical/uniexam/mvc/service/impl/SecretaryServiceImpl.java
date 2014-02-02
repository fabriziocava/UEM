package it.unical.uniexam.mvc.service.impl;

import java.util.Set;

import it.unical.uniexam.hibernate.dao.DepartmentDAO;
import it.unical.uniexam.hibernate.dao.ManagerDao;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.mvc.service.SecretaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author fabrizio
 *
 */

@Service
public class SecretaryServiceImpl extends UserServiceImpl implements SecretaryService {

	@Autowired
	DepartmentDAO departmentDAO;
	@Autowired
	ManagerDao managerDAO;
	@Autowired
	ProfessorDAO professorDAO;
	@Autowired
	StudentDAO studentDAO;
	

	@Override
	public Long addDepartment(Department department) {
		return departmentDAO.addDepartment(department);
	}
	
	@Override
	public Long addManager(Manager manager) {
		return managerDAO.addManager(manager);
	}

	@Override
	public Long addProfessor(Professor professor) {
		return professorDAO.addProfessor(professor);
	}

	@Override
	public Long addStudent(Student student) {
		return studentDAO.addStudent(student);
	}

	@Override
	public Set<Department> getDepartment() {
		return departmentDAO.getDepartments();
	}

}
