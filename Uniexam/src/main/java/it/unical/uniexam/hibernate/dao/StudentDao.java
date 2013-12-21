package it.unical.uniexam.hibernate.dao;

import java.util.List;

import it.unical.uniexam.hibernate.domain.utility.Address;

public interface StudentDao {
public Long saveStudent(String name,Address address,String matrNumber);
public void deleteStudent(Long id);

public List listStudent();
public void addPhoneNumber(Long id,String type,String number);
}
