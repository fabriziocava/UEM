package it.unical.uniexam.hibernate.dao;

import java.util.Set;

import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;

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
	public Long addPostAtGroup(Long idGroup,PostOfGroup postOfGroup);
	public PostOfGroup addPostAtGroup(Group group,PostOfGroup postOfGroup);
	
	
	public PostOfGroup modifyPost(Long idGroup,Long idPostOfGroup,PostOfGroup postOfGroupNew);
	public PostOfGroup modifyPost(Long idPostOfGroup,PostOfGroup postOfGroupNew);
	
	public PostOfGroup removePost(Long idGroup,Long idPostOfGroup);
	public PostOfGroup removePost(PostOfGroup postOfGroup);
	public PostOfGroup removePost(Long idPostOfGroup);
	
	public Long addCommentAtPost(Long idPost,CommentOfPost comment);
	public CommentOfPost removeCommentFromPost(Long idPost,Long idComment);
	public CommentOfPost modifyCommentFromPost(Long idComment,CommentOfPost newComment);

	public Group getGroup(Long idGroup);
	public PostOfGroup getPost(Long idPost);
	public CommentOfPost getComment(Long idComment);
	
	public Set<Group>getGroups();
	public Set<Group>getGroupsFromProfessor(Long idProfessor);
	public Set<PostOfGroup>getPostsOfGroup(Long idGroup);
	public Set<CommentOfPost> getCommentsFromPost(Long idPost);
	
	public Boolean iscribeUserAtGroup(User u,Group group);
	public Boolean iscribeUserAtGroup(Long idUser,Long idGroup);
	
	public Boolean cancelUserFromGroup(User u,Group group);
	public Boolean cancelUserFromGroup(Long idUser,Long idGroup);
}
