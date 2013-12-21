package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.Professor;

import java.util.List;

public interface DepartmentDao {
public Long addDepartment(String code,String name,List<Professor> professors,Address address);
public List listDepartment();
public void removeDepartment(Long idDepartment);
}
