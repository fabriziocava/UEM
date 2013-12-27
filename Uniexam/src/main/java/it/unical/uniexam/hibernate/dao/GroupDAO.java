package it.unical.uniexam.hibernate.dao;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;

public interface GroupDAO {

	//starndard
	public Long addGruop(String name,String object,String description,Professor creator,Integer politic);
	public Long addGruop(String name,String object,String description,Long idProfessorCreator,Integer politic);
	public Long addGruop(Group group);
	public Group removeGroup(Long idGroup);
	public Group removeGroup(Group group);
	public Long modifyGruop(Long idGruop,String name,String object,String description,Integer politic);
	public Long modifyGruop(Long idGruop,Group groupNew);
	public Set<Group> removeAllGroupFromProfessor(Long idProfessor);
	
	//advanced
	public Long addMessageAtGroup(Long idGroup,MessageOfGroup messageOfGroup);
	public MessageOfGroup addMessageAtGroup(Group group,MessageOfGroup messageOfGroup);
	
	public MessageOfGroup modifyMessage(Long idGroup,Long idMessageOfGroup,MessageOfGroup messageOfGroupNew);
	public MessageOfGroup modifyMessage(Long idMessageOfGroup,MessageOfGroup messageOfGroupNew);
	
	public MessageOfGroup removeMessage(Long idGroup,Long idMessageOfGroup);
	public MessageOfGroup removeMessage(MessageOfGroup messageOfGroup);
	public MessageOfGroup removeMessage(Long idMessageOfGroup);
	
	
}
