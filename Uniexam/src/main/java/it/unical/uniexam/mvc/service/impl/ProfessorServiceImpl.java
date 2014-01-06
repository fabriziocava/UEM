package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.CommentOfMessage;
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
	@Autowired
	GroupDAO groupDAO;
	
	@Override
	public Professor getProfessor(Long idUser) {
		return professorDAO.getProfessor(idUser);
	}

	@Override
	public ArrayList<CommentOfMessage> getNotificationFromComments(
			List<Long> noReadComments) {
		ArrayList<CommentOfMessage>res=new ArrayList<CommentOfMessage>();
		for (Long idComment : noReadComments) {
			res.add(groupDAO.getComment(idComment));
		}
		return res;
	}

}
