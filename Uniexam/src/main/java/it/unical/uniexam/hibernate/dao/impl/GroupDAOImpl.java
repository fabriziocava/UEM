package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.GroupDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.utility.CommentOfMessage;
import it.unical.uniexam.hibernate.domain.utility.MessageOfGroup;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */
@Repository
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			messageOfGroup.setGroup(group);
			group.getMessages().add(messageOfGroup);
			res=(Long)session.save(messageOfGroup);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public MessageOfGroup addMessageAtGroup(Group group,
			MessageOfGroup messageOfGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		MessageOfGroup res=null;
		try{
			transaction = session.beginTransaction();
			group=(Group)session.get(Group.class, group.getId());
			messageOfGroup.setDate_of_message(new Date());
			messageOfGroup.setGroup(group);
			group.getMessages().add(messageOfGroup);
			session.save(messageOfGroup);
			res=messageOfGroup;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
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
			new MokException(e);
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

	@Override
	public Long addCommentAtMessage(Long idMessage, CommentOfMessage comment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			MessageOfGroup mog=(MessageOfGroup)session.get(MessageOfGroup.class, idMessage);
			mog.getComments().add(comment);
			res=(Long)session.save(comment);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public CommentOfMessage removeCommentFromMessage(Long idMessage, Long idComment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		CommentOfMessage res=null;
		try{
			transaction = session.beginTransaction();

			MessageOfGroup mog=(MessageOfGroup)session.get(MessageOfGroup.class, idMessage);
			CommentOfMessage com=(CommentOfMessage)session.get(CommentOfMessage.class, idComment);
			mog.getComments().remove(com);
			session.delete(com);
			res=com;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public CommentOfMessage modifyCommentFromMessage(Long idComment,CommentOfMessage newComment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		CommentOfMessage res=null;
		try{
			transaction = session.beginTransaction();

			CommentOfMessage com=(CommentOfMessage)session.get(CommentOfMessage.class, idComment);
			com.setComment(newComment.getComment());
			com.setDate_of_comment(new Date());
			res=com;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<CommentOfMessage> getCommentsFromMessage(Long idMessage) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<CommentOfMessage> res=null;
		try{
			MessageOfGroup mog=(MessageOfGroup)session.get(MessageOfGroup.class, idMessage);
			res=new HashSet<CommentOfMessage>(mog.getComments());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}


	@Override
	public Group getGroup(Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Group res=null;
		try{
			res=(Group)session.get(Group.class, idGroup);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public MessageOfGroup getMessage(Long idMessage) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		MessageOfGroup res=null;
		try{
			return (MessageOfGroup)session.get(MessageOfGroup.class, idMessage);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public CommentOfMessage getComment(Long idComment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		CommentOfMessage res=null;
		try{
			return(CommentOfMessage)session.get(CommentOfMessage.class, idComment);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Group> getGroups() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Group> res=null;
		try{
			Query q= session.createQuery("from Group");
			@SuppressWarnings("unchecked")
			List<Group> list = q.list();
			res=new HashSet<Group>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Group> getGroupsFromProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Group> res=null;
		try{
			Query q= session.createQuery("from Group where creator.id=:par");
			q.setParameter("par", idProfessor);
			@SuppressWarnings("unchecked")
			List<Group> list = q.list();
			res=new HashSet<Group>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}


	@Override
	public Set<MessageOfGroup> getMessagesOfGroup(Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<MessageOfGroup> res=null;
		try{
			Query q= session.createQuery("from MessageOfGroup where group.id=:par");
			q.setParameter("par", idGroup);
			@SuppressWarnings("unchecked")
			List<MessageOfGroup> list = q.list();
			res=new HashSet<MessageOfGroup>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

}
