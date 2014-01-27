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
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Group.GroupState;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
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
			Professor creator, Integer politic, Course course) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Group g=new Group(name, object, description, politic, creator,course);
			creator.getGroups().add(g);
			g.getIscribed().add(creator);
			g.setState(GroupState.OPEN);
			res=(Long)session.save(g);

			Course c=(Course)session.get(Course.class, course.getId());
			c.getGroups().add(g);

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
			Long idProfessorCreator, Integer politic,Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Professor creator=(Professor)session.get(Professor.class, idProfessorCreator);
			Course c=null;
			if(idCourse!=null)
				c=(Course)session.get(Course.class, idCourse);
			Group g=new Group(name, object, description, politic, creator,c);
			creator.getGroups().add(g);
			g.getIscribed().add(creator);
			g.setState(GroupState.OPEN);
			res=(Long)session.save(g);

			if(c!=null)
				c.getGroups().add(g);

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
			group.getIscribed().add(group.getCreator());
			group.getCreator().getGroups().add(group);
			group.setState(GroupState.OPEN);

			Course course = group.getCourse();
			if(course!=null){
				Course c=(Course)session.get(Course.class, course.getId());
				c.getGroups().add(group);
			}

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
			if(group.getCourse()!=null){
				Course c=group.getCourse();
				c.getGroups().remove(group);
			}

			User creator=group.getCreator();
			creator.getGroups().remove(group);

			Set<User> iscribed = group.getIscribed();
			for (User user : iscribed) {
				user.getGroups().remove(group);
				for(PostOfGroup p:group.getPosts()){
					for(CommentOfPost com:p.getComments()){
						if(user.getComments().contains(com)){
							user.getComments().remove(com);
						}
					}

				}
			}

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

	@Deprecated
	@Override
	public Group removeGroup(Group group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public Long modifyGruop(Long idGruop, String name, String object,
			String description, Integer politic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public Long modifyGruop(Long idGruop, Group groupNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public Set<Group> removeAllGroupFromProfessor(Long idProfessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addPostAtGroup(Long idGroup,PostOfGroup postOfGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, idGroup);
			postOfGroup.setDate_of_post(new Date());
			postOfGroup.setGroup(group);
			group.getPosts().add(postOfGroup);
			res=(Long)session.save(postOfGroup);

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
	public PostOfGroup addPostAtGroup(Group group,
			PostOfGroup postOfGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		PostOfGroup res=null;
		try{
			transaction = session.beginTransaction();
			group=(Group)session.get(Group.class, group.getId());
			postOfGroup.setDate_of_post(new Date());
			postOfGroup.setGroup(group);
			group.getPosts().add(postOfGroup);
			session.save(postOfGroup);
			res=postOfGroup;
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Deprecated
	@Override
	public PostOfGroup modifyPost(Long idGroup, Long idPostOfGroup,
			PostOfGroup postOfGroupNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public PostOfGroup modifyPost(Long idPostOfGroup,
			PostOfGroup postOfGroupNew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostOfGroup removePost(Long idGroup, Long idPostOfGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		PostOfGroup res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, idGroup);
			for (PostOfGroup post : group.getPosts()) {
				if(post.getId()==idPostOfGroup){
					res=post;
					break;
				}
			}
			group.getPosts().remove(res);
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

	@Deprecated
	@Override
	public PostOfGroup removePost(PostOfGroup postOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public PostOfGroup removePost(Long idPostOfGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long addCommentAtPost(Long idPost, CommentOfPost comment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long res=null;
		try{
			transaction = session.beginTransaction();

			PostOfGroup mog=(PostOfGroup)session.get(PostOfGroup.class, idPost);
			mog.getComments().add(comment);
			res=(Long)session.save(comment);
			comment.setOfPost(mog);

			User creator=(User)session.get(User.class, comment.getUser().getId());
			for (User user : mog.getGroup().getIscribed()) {
				if(!user.getNoReadComments().contains(comment) && user.getId()!=creator.getId())
					user.getNoReadComments().add(comment.getId());
			}
			creator.getComments().add(comment);//togliere LAZY °°°°MOKSOL

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
	public CommentOfPost removeCommentFromPost(Long idPost, Long idComment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		CommentOfPost res=null;
		try{
			transaction = session.beginTransaction();

			PostOfGroup mog=(PostOfGroup)session.get(PostOfGroup.class, idPost);
			CommentOfPost com=(CommentOfPost)session.get(CommentOfPost.class, idComment);
			User u=com.getUser();
			u.getComments().remove(com);
			for (User user : mog.getGroup().getIscribed()) {
				if(user.getNoReadComments().contains(com))
					user.getNoReadComments().remove(com.getId());
			}
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
	public CommentOfPost modifyCommentFromPost(Long idComment,CommentOfPost newComment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		CommentOfPost res=null;
		try{
			transaction = session.beginTransaction();

			CommentOfPost com=(CommentOfPost)session.get(CommentOfPost.class, idComment);
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
	public Set<CommentOfPost> getCommentsFromPost(Long idPost) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<CommentOfPost> res=null;
		try{
			PostOfGroup mog=(PostOfGroup)session.get(PostOfGroup.class, idPost);
			res=new HashSet<CommentOfPost>(mog.getComments());
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
	public PostOfGroup getPost(Long idPost) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		PostOfGroup res=null;
		try{
			return (PostOfGroup)session.get(PostOfGroup.class, idPost);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public CommentOfPost getComment(Long idComment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		CommentOfPost res=null;
		try{
			return(CommentOfPost)session.get(CommentOfPost.class, idComment);
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
	public Set<PostOfGroup> getPostsOfGroup(Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<PostOfGroup> res=null;
		try{
			Query q= session.createQuery("from PostOfGroup where group.id=:par");
			q.setParameter("par", idGroup);
			@SuppressWarnings("unchecked")
			List<PostOfGroup> list = q.list();
			res=new HashSet<PostOfGroup>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean iscribeUserAtGroup(User user,Group group) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=null;
		try{
			transaction = session.beginTransaction();

			user=(User)session.get(User.class, user.getId());
			group=(Group)session.get(Group.class, group.getId());
			user.getGroups().add(group);
			group.getIscribed().add(user);

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
	public Boolean iscribeUserAtGroup(Long idUser,Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=null;
		try{
			transaction = session.beginTransaction();

			User user=(User)session.get(User.class, idUser);
			Group group=(Group)session.get(Group.class, idGroup);
			user.getGroups().add(group);
			group.getIscribed().add(user);

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
	public Boolean cancelUserFromGroup(User user, Group group) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=null;
		try{
			transaction = session.beginTransaction();

			user=(User)session.get(User.class, user.getId());
			group=(Group)session.get(Group.class, group.getId());
			user.getGroups().remove(group);
			group.getIscribed().remove(user);

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
	public Boolean cancelUserFromGroup(Long idUser, Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=null;
		try{
			transaction = session.beginTransaction();

			User user=(User)session.get(User.class, idUser);
			Group group=(Group)session.get(Group.class, idGroup);
			user.getGroups().remove(group);
			group.getIscribed().remove(user);

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
	public Group closeGroup(Long idGroup) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Group res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, idGroup);
			group.setState(GroupState.CLOSE);
			res=group;

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
	public Group closeGroup(Group grou) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Group res=null;
		try{
			transaction = session.beginTransaction();

			Group group=(Group)session.get(Group.class, grou.getId());
			group.setState(GroupState.CLOSE);
			res=group;

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

}
