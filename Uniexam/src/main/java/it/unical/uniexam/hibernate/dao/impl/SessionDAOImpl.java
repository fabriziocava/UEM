//package it.unical.uniexam.hibernate.dao.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//
//import org.hibernate.Query;
//import org.hibernate.Transaction;
//import org.springframework.stereotype.Repository;
//
//import it.unical.uniexam.MokException;
//import it.unical.uniexam.hibernate.dao.SessionDAO;
//import it.unical.uniexam.hibernate.domain.Manager;
//import it.unical.uniexam.hibernate.domain.Professor;
//import it.unical.uniexam.hibernate.domain.Session;
//import it.unical.uniexam.hibernate.domain.User;
//import it.unical.uniexam.hibernate.domain.utility.Email;
//import it.unical.uniexam.hibernate.util.HibernateUtil;
//
///**
// * 
// * @author luigi
// *
// */
//@Repository
//public class SessionDAOImpl implements SessionDAO {
//
////	@Override
////	public Session addSession(Long user,Long timeExpire,HashMap<String, Object>attributes) {
////		org.hibernate.Session session =HibernateUtil.getSessionFactory().openSession();
////		Transaction transaction=null;
////		Session res=null;
////		try{
////			transaction = session.beginTransaction();
////
////			try{
////				Manager m=(Manager)session.get(Manager.class, user);
////				m.getName();
////
////			}catch(Exception e){
////				try{
////					Professor p=(Professor)session.get(Professor.class, user);
////					p.getName();
////					Session session2 = new Session();
////					p.setSession(session2);
////					session2.setOwner(p.getId());
////					session2.setType(User.TYPE.PROFESSOR);
////					Date now = new Date();
////					session2.setCreated(now);
////					session2.setValid(true);
////					session2.setExpire(new Date(now.getTime()+((timeExpire!=null)?timeExpire:Session.timeExpire)));
////					session2.ensureId();
////					res=session2;
////					/**
////					 * creare funzione che serializzi gli attributi nel DB
////					 */
////				}catch(Exception e2){
////					new MokException(e2);
////				}
////			}
////
////			transaction.commit();
////		}catch(Exception e){
////			transaction.rollback();
////			new MokException(e);
////		}finally{
////			session.close();
////		}
////		return res;
////	}
////
////	@Override
////	public Session getSession(Long idUser) {
////		org.hibernate.Session session =HibernateUtil.getSessionFactory().openSession();
////		Session res=null;
////		try{
////			User user=(User)session.get(User.class, idUser);
////			if(user!=null){
////				res=user.getSession();
////			}
////		}catch(Exception e){
////			new MokException(e);
////		}finally{
////			session.close();
////		}
////		return res;
////	}
////
////	@Override
////	public Session removeSession(
////			Long idSession) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public void invalidSession(Long idSession) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public Object getAttribute(Long idSession, String name) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public HashMap<String, Object> getAttributes(Long idSession) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public Session setAttribute(String name,
////			Object value) {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////	@Override
////	public Session getSessionLogin(Long idUser, String passwd) {
////		org.hibernate.Session session =HibernateUtil.getSessionFactory().openSession();
////		Session res=null;
////		try{
////			User user=(User)session.get(User.class, idUser);
////			if(user.getPassword().equals(passwd)){
////				//					if(user.getType()==User.TYPE.PROFESSOR){
////				//						Professor p=(Professor)user;
////				Session s=user.getSession();
////				if(s!=null){//&& s.getExpire().getTime()>=now.getTime()
////					Date now = new Date();
////					s.setExpire(new Date(now.getTime()+Session.timeExpire));
////					s.setValid(true);
////					res=s;
////				}else{
////					res=addSession(user.getId(), null, null);
////				}
////				//					}
////			}
////		}catch(Exception e){
////			new MokException(e);
////		}finally{
////			session.close();
////		}
////		return res;
////	}
////
////	@Override
////	public Long isUser(String email) {
////		org.hibernate.Session session =HibernateUtil.getSessionFactory().openSession();
////		Long res=null;
////		try{
////			Query q=(Query)session.createQuery("from Email where email=:par");
////			q.setParameter("par", email);
////			List<Email>li=q.list();
////			if(li.size()==1){
////				res=li.get(0).getId();
////			}
////		}catch(Exception e){
////			new MokException(e);
////		}finally{
////			session.close();
////		}
////		return res;
////	}
////
//
//}
