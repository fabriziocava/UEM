package it.unical.uniexam.mvc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.dao.ProfessorDAO;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
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
	public ArrayList<CommentOfPost> getNotificationFromComments(
			List<Long> noReadComments) {
		ArrayList<CommentOfPost>res=new ArrayList<CommentOfPost>();
		for (Long idComment : noReadComments) {
			res.add(groupDAO.getComment(idComment));
		}
		return res;
	}

}
