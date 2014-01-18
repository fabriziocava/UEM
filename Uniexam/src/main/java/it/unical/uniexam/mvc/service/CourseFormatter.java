package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.domain.Course;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class CourseFormatter implements Formatter<Course> {

	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public String print(Course course, Locale locale) {
		if(course.getId()==null){
			return null;
		}
		Course r=(Course) courseDAO.getCourse(course.getId());
		return r.getId().toString();
	}

	@Override
	public Course parse(String idCourse, Locale locale) throws ParseException {
		if(idCourse==null){
			return null;
		}
		Course r=(Course) courseDAO.getCourse(Long.valueOf(idCourse));
		return r;
	}

}
