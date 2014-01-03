package it.unical.uniexam.hibernate.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.UserDAO;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public User getUser(String email, String password) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		User res=null;
		try{
			
//			res=(User)session.get(User.class, arg1);
			
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

}
