package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.domain.RequestedCourse;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RequestedCourseFormatter implements Formatter<RequestedCourse> {

	@Autowired
	CourseDAO courseDAO;
	
	@Override
	public String print(RequestedCourse requestedCourse, Locale locale) {
		if(requestedCourse.getId()==null){
			return null;
		}
		RequestedCourse r=(RequestedCourse) courseDAO.getRequestedCourse(requestedCourse.getId());
		return r.getId().toString();
	}

	@Override
	public RequestedCourse parse(String idRequestedCourse, Locale locale) throws ParseException {
		if(idRequestedCourse==null){
			return null;
		}
		RequestedCourse r=(RequestedCourse) courseDAO.getRequestedCourse(Long.valueOf(idRequestedCourse));
		return r;
	}

}
