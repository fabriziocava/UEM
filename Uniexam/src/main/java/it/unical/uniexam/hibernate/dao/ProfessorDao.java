package it.unical.uniexam.hibernate.dao;

import java.util.Set;

public interface ProfessorDao {
public Long addProfessor(String name,String surname,Long idDepartment);
public Set listProfessors();
public Set listProfessorsFromDepartment(Long idDepartment);
}
