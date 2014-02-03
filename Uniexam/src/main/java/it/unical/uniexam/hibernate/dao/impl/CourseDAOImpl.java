package it.unical.uniexam.hibernate.dao.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.CourseDAO;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */

@Repository
public class CourseDAOImpl implements CourseDAO{

	@Override
	public Course getCourseDetailforManager(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Course res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			Hibernate.initialize(c1);
		//	Hibernate.initialize(c1.getCommissionProfessors());
			Hibernate.initialize(c1.getRequestedCourses());
			Hibernate.initialize(c1.getDegreeCourse());
			res=c1;
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	
	@Override
	public Long addCourseforManager(String code, String name, Integer credits,
			DegreeCourse degreeCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Course c=new Course();
			c.setCode(code);
			c.setCredits(credits);
			c.setName(name);

			degreeCourse=(DegreeCourse)session.get(DegreeCourse.class, degreeCourse.getId());
			c.setDegreeCourse(degreeCourse);
			degreeCourse.getCourses().add(c);
			id=(Long) session.save(c);
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}
	
	@Override
	public Boolean removeHolderProfessor(Long idCourse, Long idprofessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			Professor p=(Professor) session.get(Professor.class, idprofessor);
			if(c1!=null){
				c1.setHolder(null);
				p.getSetHoldersCourse().remove(c1);
				p.getAppeals().remove(c1);
				p.getSetAsCommission().remove(c1);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
	}
	
//	@Override
//	public Course getCourseDetailforManager(Long idCourse) {
//		Session session =HibernateUtil.getSessionFactory().openSession();
//		Course res=null;
//		try{
//			Course c1=(Course) session.get(Course.class, idCourse);
//			Hibernate.initialize(c1);
//		//	Hibernate.initialize(c1.getCommissionProfessors());
//			Hibernate.initialize(c1.getRequestedCourses());
//			Hibernate.initialize(c1.getDegreeCourse());
//			res=c1;
//		}catch(Exception e){
//			new MokException(e);
//		}finally{
//			session.close();
//		}
//		return res;
//	}
	
	@Override
	public ArrayList<Professor> getListCommissaryCourse(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Professor> res=null;
		try{
			Course c=(Course)session.get(Course.class, idCourse);
//			Hibernate.initialize(c);
			for(Professor p:c.getCommissionProfessors()){
				Hibernate.initialize(p);
				Hibernate.initialize(p.getPhoneNumbers());
				Hibernate.initialize(p.getEmails());
			}
			res=new ArrayList<Professor>(c.getCommissionProfessors());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	
	@Override
	public Long getDepartmentFromCourse(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Long res=null;
		try{
			Course c=(Course)session.get(Course.class, idCourse);
			res=c.getDegreeCourse().getDepartment_associated().getId();
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	
	
	@Override
	public Long addCourse(String codeCourse,
			String nameCourse, Integer creditCourse, Long idProfessorHolder,
			Set<RequestedCourse> requestedCourses,URL webSite, DegreeCourse degreeCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Course c=new Course();
			c.setCode(codeCourse);
			c.setCredits(creditCourse);
			c.setName(nameCourse);
			Professor p=(Professor) session.get(Professor.class, idProfessorHolder);
			c.setHolder(p);
			c.setRequestedCourses(requestedCourses);
			c.setWebSite(webSite);
			
			degreeCourse=(DegreeCourse)session.get(DegreeCourse.class, degreeCourse.getId());
			c.setDegreeCourse(degreeCourse);
			degreeCourse.getCourses().add(c);
			p.getSetHoldersCourse().add(c);
			id=(Long) session.save(c);
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
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
			Professor holder = course.getHolder();
			if(holder!=null){
				Professor p=(Professor) session.get(Professor.class, holder.getId());
				p.getSetHoldersCourse().add(course);
			}
			DegreeCourse degreeCourse=course.getDegreeCourse();
			if(degreeCourse!=null && degreeCourse.getId()!=null){
				degreeCourse=(DegreeCourse)session.get(DegreeCourse.class, degreeCourse.getId());
				course.setDegreeCourse(degreeCourse);
				degreeCourse.getCourses().add(course);
			}
			
			id=(Long) session.save(course);
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

//	@Override
//	public Long addCourse(Course course) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction = null;
//		Long id = null;
//		try {
//			transaction = session.beginTransaction();
//			id = (Long) session.save(course);
//			transaction.commit();
//		} catch (Exception e) {
//			transaction.rollback();
//		} finally {
//			session.close();
//		}
//		return id;
//	}

	@Override
	public ArrayList<Course> getCourses() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Course>res=null;
		try{
			Query q= session.createQuery("from Course");
			@SuppressWarnings("unchecked")
			List<Course> list = q.list();
			res=new ArrayList<Course>(list);
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public ArrayList<Course> getCoursesFromDegreeCourse(Long idDegreeCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Course>res=null;
		try{
			Query q= session.createQuery("from Course where degreeCourse.id=:par");
			q.setParameter("par", idDegreeCourse);
			@SuppressWarnings("unchecked")
			
			List<Course> list = q.list();
			res=new ArrayList<Course>(list);
			for(Course c : res){
				Hibernate.initialize(c);
				Hibernate.initialize(c.getRequestedCourses());
			}
		}catch(Exception e){
			new MokException(e);
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
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Course getCourseAll(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Course res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			Hibernate.initialize(c1);
			Hibernate.initialize(c1.getCommissionProfessors());
			Hibernate.initialize(c1.getGroups());
			Hibernate.initialize(c1.getRequestedCourses());
			res=c1;
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Course removeCourse(Long idCourse, Long idDegreecourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Course res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			DegreeCourse dg=(DegreeCourse) session.get(DegreeCourse.class,idDegreecourse);
			dg.getCourses().remove(c1);
			
			session.delete(c1);
			transaction.commit();
			res=c1;
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	
	@Override
	public Course removeCourseforManager(Long idCourse, Long idDegreeCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Course res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			DegreeCourse dg=(DegreeCourse) session.get(DegreeCourse.class,idDegreeCourse);
			dg.getCourses().remove(c1);
			Professor p=c1.getHolder();
			if(p!=null){
			p.getSetHoldersCourse().remove(c1);
			}
			
			
			session.delete(c1);
			transaction.commit();
			res=c1;
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}
	
//	@Override
//	public Course removeCourse(Long idCourse) {
//		Session session =HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction=null;
//		Course res=null;
//		try{
//			transaction = session.beginTransaction();
//			Course c1=(Course) session.get(Course.class, idCourse);
//			session.delete(c1);
//			transaction.commit();
//			res=c1;
//		}catch(Exception e){
//			transaction.rollback();
//			new MokException(e);
//		}finally{
//			session.close();
//		}
//		return res;
//	}

	@Override
	public boolean addRequestedCourse(Long idCourse, Long idCourseRequested,String degree) {
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
				paramE.setPolicyOfRequested(degree);
				c1.getRequestedCourses().add(paramE);
				transaction.commit();
				ok=true;			
			}
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		if(!ok) return false;
		else return true;
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
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
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<RequestedCourse> getRequestedCourses(Long idCourse,
			String degreeOdPolicy) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<RequestedCourse> res=null;
		try{
			Course c1=(Course) session.get(Course.class, idCourse);
			for (RequestedCourse requestedCourse : c1.getRequestedCourses()) {
				if(requestedCourse.getPolicyOfRequested().equals(degreeOdPolicy)){
					if(res==null)
						res=new HashSet<RequestedCourse>();
					res.add(requestedCourse);
				}
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public ArrayList<Course> getAssociatedCourseWithGroups(User user) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Course> res=null;
		try{
			Query q=session.createQuery("from Course where holder.id=:p");
			q.setParameter("p", user.getId());
			res=(ArrayList<Course>) q.list();
			for (Course course : res) {
				Set<Group>g=course.getGroups();
				//				for (Group group : g) {
				//					System.out.println(group.getName());
				//				}
				Hibernate.initialize(course);///°°°° MOKSOL LAKY LOAD
				Hibernate.initialize(course.getGroups());
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public String getNote(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		String res=null;
		try{
			Course c=(Course) session.get(Course.class, idCourse);
			res=c.getNote();
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean setNote(Long idCourse, String note) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction=session.beginTransaction();

			Course course=(Course)session.get(Course.class, idCourse);
			course.setNote(note);

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
			System.err.println("idCourse = "+idCourse+" idCorR = "+idCourseRequested);
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Boolean modifyDegreeRequestedCourse(Long idCourse,Long idCourseRequested, String degree) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean res=false;
		try{
			transaction=session.beginTransaction();
			if(degree.equals(RequestedCourse.POLICY_LIGHT)|| degree.equals(RequestedCourse.POLICY_MEDIUM)||
					degree.equals(RequestedCourse.POLICY_STRONG)){
				Course course=(Course)session.get(Course.class, idCourse);
				for(RequestedCourse req:course.getRequestedCourses()){
					if(req.getCourse().getId()==idCourseRequested){
						req.setPolicyOfRequested(degree);
						res=true;
					}
				}
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
	public RequestedCourse removeRequestedCourse(Long idCourse, Long idCourseRequested,Session session) {
		RequestedCourse res=null;
//		if(session==null)
//			session =HibernateUtil.getSessionFactory().openSession();
		try{
//			if(transaction==null)
//				transaction=session.beginTransaction();
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
//				transaction.commit();
			}
		}catch(Exception e){
//			transaction.rollback();
			new MokException(e);
		}finally{
//			session.close();
		}
		return res;
	}

	@Override
	public Boolean modifyDegreeRequestedCourse(Long idCourse,Long idCourseRequested, String degree,Session session) {
//		if(session==null)
//			session =HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction=null;
		Boolean res=false;
		try{
//			if(transaction==null)
//				transaction=session.beginTransaction();
			if(degree.equals(RequestedCourse.POLICY_LIGHT)|| degree.equals(RequestedCourse.POLICY_MEDIUM)||
					degree.equals(RequestedCourse.POLICY_STRONG)){
				Course course=(Course)session.get(Course.class, idCourse);
				for(RequestedCourse req:course.getRequestedCourses()){
					if(req.getCourse().getId()==idCourseRequested){
						req.setPolicyOfRequested(degree);
						res=true;
					}
				}
			}
//			transaction.commit();
		}catch(Exception e){
//			transaction.rollback();
			res=false;
			new MokException(e);
		}finally{
//			session.close();
		}
		return res;
	}

	@Override
	public Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}
	
	@Override
	public Collection<? extends Course> getCoursesFromDepartment(Long idDepartment) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Course>res=null;
		try{
			Query q= session.createQuery("from Course where degreeCourse.department_associated.id=:par");
			q.setParameter("par", idDepartment);
			res=new ArrayList<Course>(q.list());
//			for (Course course : res) {
//				Hibernate.initialize(course);
//				Hibernate.initialize(course.getCommissionProfessors());
//				Hibernate.initialize(course.getAppeals());
//				Hibernate.initialize(course.getRequestedCourses());
//			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public RequestedCourse getRequestedCourse(Long idRequestedCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		RequestedCourse res=null;
		try{
			transaction=session.beginTransaction();
			
			res=(RequestedCourse)session.get(RequestedCourse.class, idRequestedCourse);
				
			transaction.commit();
		}catch(Exception e){
			transaction.rollback();
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Course> getCoursesFromStudent(Long idStudent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Course> res = null;
		try {
			Query q= session.createQuery("from Course where degreeCourse.id=:par ORDER BY name");
			Student student = (Student) session.get(Student.class, idStudent);
			q.setParameter("par", student.getDegreeCourse_registered().getId());
			res = new ArrayList<Course>(q.list());
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public RequestedCourse RemoveReqCourseForManager(Long idCourse,
			Long idCoursereq) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		RequestedCourse res=null;
		try{
			transaction = session.beginTransaction();
			Course c1=(Course) session.get(Course.class, idCourse);
			if(c1!=null){
				for(RequestedCourse r : c1.getRequestedCourses()){
					if(r.getId()==idCoursereq){
						res=r;
						break;
					}
				}
				c1.getRequestedCourses().remove(res);
				session.delete(res);
				transaction.commit();
			}
		}catch(Exception e){
			System.err.println("idCourse = "+idCourse+" idCorR = "+idCoursereq);
			transaction.rollback();
			new MokException(e);
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
