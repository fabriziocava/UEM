package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Department;
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

	public Long addProfessor(Professor professor);

	public Set<Professor> getProfessors();
	public Set<Professor> getProfessorsFromDepartment(Long idDepartment);
	public Professor getProfessor(Long idProfessor);

	public Professor removeProfessor(Long idProfessor);


	public Set<Professor> listProfessors();
	public Set<Professor> listProfessorsFromDepartment(Long idDepartment);

	/**hibernate
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
	
	//la gestione del holder lo si fa da CourseDAO
	public Set<Course> getCourseHolder(Long idProfessor);
	public Set<Course> getCourseCommission(Long idProfessor);
	
//	gestione dei gruppi la faccio dal professore e nel GroupDAO andrò a mettere solo l'inserimento di un nuovo messaggio
	
	
	
}


