package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Professor;

import java.util.List;
import java.util.Set;

public interface DepartmentDAO {

	//standard
	public Long addDepartment(String code,Professor president,
			String name,Address address,List<Professor> professors);
	public Long addDepartment(Department department);

	public Set<Department> getDepartments();
	public Department getDepartment(Long idDepartment);
	
	public Department removeDepartment(Long idDepartment);
	public Department removeDepartment(Department department);

	public Department modifyDepartment(Long idDepartment,Department departmentNew);
	
	//advanced
	/**
	 * @return id of the professor added
	 */
	public Long addProfessorAtDepartment(Long idDepartment, Professor professor);
	/**
	 * @return id of the professor removed
	 */
	public Long removeProfessorAtDepartment(Long idDepartment, Long idProfessor);

	public void moveProfessorFromDepartmentToDepartment(Long idProfessor, Long idDepartmentOld, Long idDepartmentNew);

	
	public Professor setPresident(Professor president);
	public Professor setPresident(Long idProfessor);
	
}
