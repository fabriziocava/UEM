package it.unical.uniexam.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.mvc.service.ProfessorService;

/**
 * 
 * @author luigi
 *
 */
@Service
public class ProfessorServiceImpl extends UserServiceImpl implements ProfessorService {

	@Autowired
	ProfessorDAO professorDAO;
	
	@Override
	public Professor getProfessor(Long idUser) {
		return professorDAO.getProfessor(idUser);
	}

}
