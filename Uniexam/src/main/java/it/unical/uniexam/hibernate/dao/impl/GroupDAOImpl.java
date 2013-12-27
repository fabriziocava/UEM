package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;
import it.unical.uniexam.hibernate.util.HibernateUtil;

public class GroupDAOImpl implements GroupDAO {

	@Override
	public Long addGruop(String name, String object, String description,
			Professor creator, Integer politic) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Group g=new Group(name, object, description, politic, creator);
			creator.getGroups().add(g);
			res=(Long)session.save(g);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Long addGruop(String name, String object, String description,
			Long idProfessorCreator, Integer politic) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Professor creator=(Professor)session.get(Professor.class, idProfessorCreator);
			Group g=new Group(name, object, description, politic, creator);
			creator.getGroups().add(g);
			res=(Long)session.save(g);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}
	
	@Override
	public Long addGruop(Group group) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			res=(Long)session.save(group);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Group removeGroup(Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Group res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, idGroup);
			session.delete(group);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
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
	public Long addMessageAtGroup(Long idGroup,MessageOfGroup messageOfGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, idGroup);
			messageOfGroup.setDate_of_message(new Date());
			group.getMessages().add(messageOfGroup);
			res=(Long)session.save(messageOfGroup);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
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
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		MessageOfGroup res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, idGroup);
			for (MessageOfGroup message : group.getMessages()) {
				if(message.getId()==idMessageOfGroup){
					res=message;
					break;
				}
			}
			group.getMessages().remove(res);
			session.delete(res);
			
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
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
