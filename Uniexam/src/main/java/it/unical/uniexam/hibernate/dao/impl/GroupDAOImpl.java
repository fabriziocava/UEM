package it.unical.uniexam.hibernate.dao.impl;

import java.util.Set;

import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;

public class GroupDAOImpl implements GroupDAO {

	@Override
	public Long addGruop(String name, String object, String description,
			Professor creator, Integer politic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addGruop(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group removeGroup(Long idGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group removeGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long modifyGruop(Long idGruop, String name, String object,
			String description, Integer politic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long modifyGruop(Long idGruop, Group groupNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Group> removeAllGroupFromProfessor(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup addMessageAtGroup(Long idGroup,
			MessageOfGroup messageOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup addMessageAtGroup(Group group,
			MessageOfGroup messageOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup modifyMessage(Long idGroup, Long idMessageOfGroup,
			MessageOfGroup messageOfGroupNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup modifyMessage(Long idMessageOfGroup,
			MessageOfGroup messageOfGroupNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup removeMessage(Long idGroup, Long idMessageOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup removeMessage(MessageOfGroup messageOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageOfGroup removeMessage(Long idMessageOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

}
