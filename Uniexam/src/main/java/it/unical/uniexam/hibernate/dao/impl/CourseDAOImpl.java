package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */
public class CourseDAOImpl implements CourseDAO{

	@Override
	public Long addCourse(Long idDegreeCourse, String codeCourse,
			String nameCourse, Integer creditCourse, Long idProfessorHolder,
			Set<RequestedCourse> requestedCourses,URL webSite) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			/**
			 * will add degreeCourse
			 */
			Course c=new Course();
			c.setCode(codeCourse);
			c.setCredits(creditCourse);
			c.setName(nameCourse);
			Professor p=(Professor) session.get(Professor.class, idProfessorHolder);
			c.setHolder(p);
			c.setRequestedCourses(requestedCourses);
			c.setWebSite(webSite);

			p.getSetHoldersCourse().add(c);
			id=(Long) session.save(c);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Long addCourse(Course course) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			/**
			 * will add degreeCourse
			 */
			Professor holder = course.getHolder();
			if(holder!=null){
				Professor p=(Professor) session.get(Professor.class, holder.getId());
				p.getSetHoldersCourse().add(course);
			}
			id=(Long) session.save(course);
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}


	@Override
	public Set<Course> getCourses() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Query q= session.createQuery("from Course");
			@SuppressWarnings("unchecked")
			List<Course> list = q.list();
			res=new HashSet<Course>(list);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Course> getCoursesFromDegreeCourse(Long idDegreeCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		HashSet<Course>res=null;
		try{
			Query q= session.createQuery("from Course where underDegreeCourse=:par");
			q.setParameter("par", idDegreeCourse);
			@SuppressWarnings("unchecked")
			List<Course> list = q.list();
			res=new HashSet<Course>(list);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Course getCourse(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Course res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			res=c1;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Course removeCourse(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Course res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			session.delete(c1);
			transaction.commit();
			res=c1;
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public boolean addRequestedCourse(Long idCourse, Long idCourseRequested,Integer degree) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			Course c2=(Course) session.get(Course.class, idCourseRequested);
			if(c1!=null && c2!=null){
				RequestedCourse paramE=new RequestedCourse();
				paramE.setCourse(c2);
				paramE.setPolicyOfRequest(degree);
				c1.getRequestedCourses().add(paramE);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}

	@Override
	public RequestedCourse removeRequestedCourse(Long idCourse, Long idCourseRequested) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		RequestedCourse res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			if(c1!=null){
				for(RequestedCourse r : c1.getRequestedCourses()){
					if(r.getCourse().getId()==idCourseRequested){
						res=r;
						break;
					}
				}
				c1.getRequestedCourses().remove(res);
				session.delete(res);
				transaction.commit();
			}
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public boolean setHolderProfessor(Long idCourse, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			Professor p=(Professor) session.get(Professor.class, idProfessor);
			if(c1!=null && p!=null){
				if(c1.getHolder()!=null)
					c1.getHolder().getSetHoldersCourse().remove(c1);
				c1.setHolder(p);
				p.getSetHoldersCourse().add(c1);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}

	@Override
	public boolean setHolderProfessor(Long idCourse, Professor professor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			if(c1!=null){
				c1.setHolder(professor);
				professor.getSetHoldersCourse().add(c1);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}

	@Override
	public Professor getHolderProfessor(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Professor res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			res=c1.getHolder();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Long addProfessorAtCommission(Long idCourse, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Course c=(Course) session.get(Course.class,idCourse );
			Professor p=(Professor) session.get(Professor.class,idProfessor);

			c.getCommissionProfessors().add(p);
			p.getSetAsCommission().add(c);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Long addProfessorAtCommission(Long idCourse, Professor p) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Course c=(Course) session.get(Course.class,idCourse );

			c.getCommissionProfessors().add(p);
			p.getSetAsCommission().add(c);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public boolean setCommission(Long idCourse, Set<Professor> commission) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction=session.beginTransaction();

			Course c=(Course) session.get(Course.class,idCourse );
			c.getCommissionProfessors().clear();
			c.setCommissionProfessors(commission);

			transaction.commit();
			ok=true;
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}

	@Override
	public Professor removeProfessorFromCommission(Long idCourse, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Professor p=null;
		try{
			transaction=session.beginTransaction();
			Course c=(Course) session.get(Course.class,idCourse );
			p=(Professor)session.get(Professor.class, idProfessor);

			c.getCommissionProfessors().remove(p);
			p.getSetAsCommission().remove(c);

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return p;
	}

	@Override
	public Set<Professor> removeCommission(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Set<Professor> pSet=null;
		try{
			transaction=session.beginTransaction();
			Course c=(Course) session.get(Course.class,idCourse );

			pSet=new HashSet<Professor>(c.getCommissionProfessors());

			for (Professor professor : pSet) {
				professor.getSetAsCommission().remove(c);
			}
			c.getCommissionProfessors().clear();

			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return pSet;
	}

	@Override
	public Set<RequestedCourse> getRequestedCourses(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<RequestedCourse> res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			res=new HashSet<RequestedCourse>(c1.getRequestedCourses());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<RequestedCourse> getRequestedCourses(Long idCourse,
			Integer degreeOdPolicy) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<RequestedCourse> res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			for (RequestedCourse requestedCourse : c1.getRequestedCourses()) {
				if(requestedCourse.getPolicyOfRequest()==degreeOdPolicy){
					if(res==null)
						res=new HashSet<RequestedCourse>();
					res.add(requestedCourse);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return res;
	}

}

//Session session =HibernateUtil.getSessionFactory().openSession();
//Transaction transaction=null;
//try{
//	transaction=session.beginTransaction();
//	
//	
//	transaction.commit();
//}catch(Exception e){
//	transaction.rollback();
//}finally{
//	session.close();
//}
//return null;