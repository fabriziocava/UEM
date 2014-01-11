package it.unical.uniexam.hibernate.dao;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public interface StudentDAO {
	
	public Long addStundent(String name, String surname, String fiscalCode, String password, Address address, Set<Email> emails, Set<PhoneNumber> phoneNumbers, DegreeCourse degreeCourse_registered, String serialNumber);
	public Set<Student> getStudents();
	public Student getStudent(Long serialNumber);
	
	public Set<Course> getCarrier(Long serialNumber);
	public Set<Group> getGroups(Long serialNumber);	
}

