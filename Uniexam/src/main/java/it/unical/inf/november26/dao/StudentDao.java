package it.unical.inf.november26.dao;

import it.unical.inf.november26.domain.Address;

public interface StudentDao {
public Long saveStudent(String name,Address address,String matrNumber);
public void deleteStudent(Long id);

public void listStudent();
public void addPhoneNumber(Long id,String type,String number);
}
