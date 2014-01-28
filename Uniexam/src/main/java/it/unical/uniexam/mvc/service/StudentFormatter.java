package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.dao.StudentDAO;
import it.unical.uniexam.hibernate.domain.Student;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class StudentFormatter implements Formatter<Student> {

	@Autowired
	StudentDAO studentDAO;
	
	@Override
	public String print(Student student, Locale locale) {
		if(student.getId()==null){
			return null;
		}
		return student.getId().toString();
	}

	@Override
	public Student parse(String idStudent, Locale locale) throws ParseException {
		if(idStudent==null || idStudent.equals("-1")){
			return null;
		}
		Student r=(Student) studentDAO.getStudent(Long.valueOf(idStudent));
		return r;
	}

}
