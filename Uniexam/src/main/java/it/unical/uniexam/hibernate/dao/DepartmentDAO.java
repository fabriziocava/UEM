package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;

import java.util.List;
import java.util.Set;

public interface DepartmentDAO {
public Long addDepartment(String code,Professor president,
		String name,Address address,List<Professor> professors);

public Set<Department> getDepartment();
public Department getDepartment(Long idDepartment);
public void removeDepartment(Long idDepartment);

/**
 * @return id of the professor added
 */
public Long addProfessorAtDepartment(Long idDepartment, Professor professor);
/**
 * @return id of the professor removed
 */
public Long removeProfessorAtDepartment(Long idDepartment, Long idProfessor);

public void moveProfessorFromDepartmentToDepartment(Long idProfessor, Long idDepartmentOld, Long idDepartmentNew);

}
