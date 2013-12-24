package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Exam;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.URL;
import java.util.Set;

public interface ProfessorDAO {
	/**
	 * Standard method
	 */
	
/**
 * 
 */
	public Long addProfessor(String name,String surname,
			URL webSite,String email,String password,
			Address address,Long idDepartment);

	public Set<Professor> getProfessors();
	public Set<Professor> getProfessorsFromDepartment(Long idDepartment);
	public Professor getProfessor(Long idProfessor);

	public Professor removeProfessor(Long idProfessor);


	public Set listProfessors();
	public Set listProfessorsFromDepartment(Long idDepartment);

	/**
	 * Advanced method
	 */
	
	
	/**
	 * @return id PhoneNumber added
	 */
	//phone
	public Long addPhoneNumber(Long idProfessor,PhoneNumber number);
	public void removePhoneNumber(Long idProfessor, Long idPhoneNumber);
	public void removePhoneNumber(Long idProfessor, PhoneNumber idPhoneNumber);
	public Set<PhoneNumber> getPhoneNumbers(Long idProfessor);
	
	//department associated
	public Long setDepartmentAssociated(Long idProfessor,Long idDepartment);
	public boolean setDepartmentAssociated(Long idProfessor,Department department);
	// I thing that remove is not necessary
	
	
	
	
}


