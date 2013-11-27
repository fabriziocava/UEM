package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Address;

public interface StudentDao {
public Long saveStudent(String name,Address address,String matrNumber);
public void deleteStudent(Long id);

public void listStudent();
public void addPhoneNumber(Long id,String type,String number);
}
