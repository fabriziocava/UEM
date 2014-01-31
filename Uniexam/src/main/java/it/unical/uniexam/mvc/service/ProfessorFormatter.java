package it.unical.uniexam.mvc.service;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.stereotype.Component;

import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.domain.Professor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

@Component
public class ProfessorFormatter implements Formatter<Professor> {

	@Autowired
	ProfessorDAO professorDAO;
	
	
	@Override
	public String print(Professor professor, Locale locale) {
		if(professor.getId()==null)
			return null;
		Professor p=(Professor) professorDAO.getProfessor(professor.getId());
		return p.getId().toString();
	}

	@Override
	public Professor parse(String idprofessor, Locale locale) throws ParseException {
		
		if(idprofessor==null || idprofessor.equals("-1")){
			return new Professor();
		}
		Professor p=(Professor) professorDAO.getProfessor(Long.valueOf(idprofessor));
		return p;
	}

}
