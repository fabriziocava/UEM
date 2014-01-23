package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.dao.DegreeCourseDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class DegreeCourseFormatter implements Formatter<DegreeCourse> {

	@Autowired
	DegreeCourseDAO courseDAO;
	
	@Override
	public String print(DegreeCourse course, Locale locale) {
		if(course.getId()==null){
			return null;
		}
		DegreeCourse r=(DegreeCourse) courseDAO.getDegreeCourse(course.getId());
		return r.getId().toString();
	}

	@Override
	public DegreeCourse parse(String idCourse, Locale locale) throws ParseException {
		if(idCourse==null || idCourse.equals("-1")){
			return new DegreeCourse();
		}
		DegreeCourse r=(DegreeCourse) courseDAO.getDegreeCourse(Long.valueOf(idCourse));
		return r;
	}

}
