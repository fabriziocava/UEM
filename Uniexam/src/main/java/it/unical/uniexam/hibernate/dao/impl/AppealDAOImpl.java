package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.bytecode.annotation.CharMemberValue;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.AppealStudent.STATE;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */
@Repository
public class AppealDAOImpl implements AppealDAO {


	@Override
	public ArrayList<ArrayList<Object>> getListStudentFromAppealRegularAndNotForState(Long idProfessor,Long idAppeal,STATE state) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<ArrayList<Object>> res=new ArrayList<ArrayList<Object>>();
		ArrayList<Object> reg=new ArrayList<Object>();
		ArrayList<Object> noreg=new ArrayList<Object>();
		res.add(reg);
		res.add(noreg);
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			Appeal appeal=(Appeal)session.get(Appeal.class, idAppeal);
			if(appeal!=null && appeal.getCreatorProfessor().equals(p)){
				ArrayList<AppealStudent> list=new ArrayList<AppealStudent>(appeal.getAppeal_student());
				if(appeal.getCourse()!=null){
					Course c1=(Course) session.get(Course.class, appeal.getCourse().getId());
					ArrayList<RequestedCourse>requested=new ArrayList<RequestedCourse>(c1.getRequestedCourses());
					if(requested.size()>0){
						for (AppealStudent appealStudent : list) {
							if(appealStudent.getState()==state){
								ArrayList<Carrier> carrier=new ArrayList<Carrier>(appealStudent.getStudent().getCarrier());
								ArrayList<RequestedCourse>miss=new ArrayList<RequestedCourse>();
								boolean good=false;
								for (RequestedCourse requestedCourse : requested) {
									good=false;
									for (Carrier carrier2 : carrier) {
										if(requestedCourse.getCourse().getId()==carrier2.getCourse().getId()){
											good=true; break;
										}
									}
									if(!good){
										miss.add(requestedCourse);
									}
								}
								if(miss.size()==0)
									reg.add(appealStudent);
								else{
									ArrayList<Object>tuple=new ArrayList<Object>();
									tuple.add(appealStudent);
									tuple.add(miss);
									noreg.add(tuple);
								}
							}
						}
					}else{
						for (AppealStudent appealStudent : list) {
							reg.add(appealStudent);
						}
					}
				}else{
					//					for (AppealStudent appealStudent : list) {
					//						if(appealStudent.getState()==state){
					//							reg.add(appealStudent);
					//						}
					//					}
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
	public ArrayList<AppealStudent> getAppealForPrepareSign(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=new ArrayList<AppealStudent>();
		try{

			Appeal appeal=(Appeal)session.get(Appeal.class, idAppeal);
			ArrayList<AppealStudent>appealStudent=new ArrayList<AppealStudent>(appeal.getAppeal_student());
			for (AppealStudent appealStudent2 : appealStudent) {
				if(appealStudent2.getState()==AppealStudent.STATE.NO_STATE){
					res.add(appealStudent2);
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
	public ArrayList<Appeal> getAppealsMatch(Long idProfessor,String appealString) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		ArrayList<Appeal> res = null;
		try {
			//			Query q = session.createQuery("from Appeal where creatorProfessor.id =:idProfessor and "
			//					+ "(lower(name) like :id or lower(location) like :id2 or (course is not null and lower(course.name) like :id3))");
			Query q = session.createQuery("from Appeal where creatorProfessor.id =:idProfessor and"
					+ "(lower(name) like :id or lower(location) like :id2 or (course is not null and lower(course.name) like :id3))");

			q.setParameter("idProfessor", idProfessor);
			q.setParameter("id", "%"+appealString.toLowerCase()+"%");
			q.setParameter("id2", "%"+appealString.toLowerCase()+"%");
			q.setParameter("id3", "%"+appealString.toLowerCase()+"%");
			@SuppressWarnings("unchecked")
			List<Appeal> list = q.list();
			res = new ArrayList<Appeal>(list);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return res;
	}

	@Override
	public Appeal getAppealGround(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Appeal res=null;
		try{

			res=(Appeal)session.get(Appeal.class, idAppeal);

		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public ArrayList<ArrayList<Object>> getListStudentFromAppealRegularAndNot(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<ArrayList<Object>> res=new ArrayList<ArrayList<Object>>();
		ArrayList<Object> reg=new ArrayList<Object>();
		ArrayList<Object> noreg=new ArrayList<Object>();
		res.add(reg);
		res.add(noreg);
		try{
			Appeal appeal=(Appeal)session.get(Appeal.class, idAppeal);
			if(appeal!=null){
				if(appeal.getCourse()!=null){
					Course c1=(Course) session.get(Course.class, appeal.getCourse().getId());
					ArrayList<RequestedCourse>requested=new ArrayList<RequestedCourse>(c1.getRequestedCourses());
					ArrayList<AppealStudent> list=new ArrayList<AppealStudent>(appeal.getAppeal_student());
					if(requested!=null && requested.size()>0){
					for (AppealStudent appealStudent : list) {
						ArrayList<Carrier> carrier=new ArrayList<Carrier>(appealStudent.getStudent().getCarrier());
						ArrayList<RequestedCourse>miss=new ArrayList<RequestedCourse>();
						boolean good=false;
						for (RequestedCourse requestedCourse : requested) {
							good=false;
							for (Carrier carrier2 : carrier) {
								if(requestedCourse.getCourse().getId()==carrier2.getCourse().getId()){
									good=true; break;
								}
							}
							if(!good){
								miss.add(requestedCourse);
							}
						}
						if(miss.size()==0)
							reg.add(appealStudent);
						else{
							ArrayList<Object>tuple=new ArrayList<Object>();
							tuple.add(appealStudent);
							tuple.add(miss);
							noreg.add(tuple);
						}
					}
					}else{
						reg.addAll(appeal.getAppeal_student());
					}
				}else{
					reg.addAll(appeal.getAppeal_student());
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
	public Set<Appeal> getAppealsFromProfessorDetails(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Appeal> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Appeal>(p.getAppeals());
			for (Appeal appeal : res) {
				Hibernate.initialize(appeal);
				Hibernate.initialize(appeal.getAppeal_student());
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Appeal getAppealDetails(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Appeal res=null;
		try{

			res=(Appeal)session.get(Appeal.class, idAppeal);
			Hibernate.initialize(res);
			Hibernate.initialize(res.getAppeal_student());

		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public List<List<Object>> getStructureCourse_Appeal(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		List<List<Object>> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			List<Course>courses=new ArrayList<Course>(p.getSetHoldersCourse());
			int count=0;
			for (Course course : courses) {
				if(res==null)
					res=new ArrayList<List<Object>>();
				res.add(new ArrayList<Object>());
				res.get(count).add(course);
				//				Query q=session.createQuery("from Appeal where course=:courId and creatorProfessor=:idProf");
				//				q.setParameter("courId", course);
				//				q.setParameter("idProf", ((Professor)session.get(Professor.class, idProfessor)));
				Query q=session.createQuery("from Appeal where course.id=:courId and creatorProfessor.id=:idProf");
				q.setParameter("courId", course.getId());
				q.setParameter("idProf", idProfessor);
				ArrayList<Appeal>ris=(ArrayList<Appeal>) q.list();
				for (Appeal appeal : ris) {
					Hibernate.initialize(appeal);
					Hibernate.initialize(appeal.getAppeal_student());
				}
				Collections.sort(ris, new Comparator<Appeal>(){
					@Override
					public int compare(Appeal o1, Appeal o2) {
						if(o1!=null && o2!=null)
							return (int) (o2.getExamDate().getTime()-o1.getExamDate().getTime());
						return 0;
					}
				});
				if(ris!=null && ris.size()>0){
					res.get(count).addAll(ris);
				}
				count++;
			}
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}


	@Deprecated
	@Override
	public Long addAppeal(Appeal appeal) {
		return null;
	}

	@Deprecated
	@Override
	public Long addAppeal(Course course, String name,
			Integer maxNumberOfInscribed, String location,String description, Date examDate,
			Date openDate, Date closeDate, Professor creatorProfessor) {
		return null;
	}

	@Override
	public Long addAppeal(Long idCourse, String name,
			Integer maxNumberOfInscribed, String location,String description, Date examDate,
			Date openDate, Date closeDate, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();
			Course course=null;
			if(idCourse!=null)
				course=(Course)session.get(Course.class, idCourse);
			Professor creatorProfessor=(Professor)session.get(Professor.class, idProfessor);
			Appeal a=new Appeal(course, name, maxNumberOfInscribed, location,description, examDate, openDate, closeDate, creatorProfessor);
			creatorProfessor.getAppeals().add(a);
			id=(Long)session.save(a);

			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			id=null;
			transaction.rollback();
		}finally{
			session.close();
		}
		return id;
	}

	@Override
	public Appeal removeAppeal(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();
			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);

			Professor p=a.getCreatorProfessor();
			p.getAppeals().remove(a);

			for(AppealStudent app:a.getAppeal_student()){
				app.getStudent().getAppeal_student().remove(app);
				session.delete(app);
			}

			session.delete(a);
			res=a;

			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Deprecated
	@Override
	public Appeal removeAppeal(Appeal appeal) {
		return null;
	}

	@Override
	public Set<Appeal> removeAllAppealsFromProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Set<Appeal> res=null;
		try{
			transaction=session.beginTransaction();
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			if(p.getAppeals()!=null && p.getAppeals().size()>0){
				res=new HashSet<Appeal>(p.getAppeals());
				for (Appeal appeal : res) {
					session.delete(appeal);
				}
			}
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Appeal modifyAppeal(Long idAppeal, Appeal appealNew) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();
			Appeal old=(Appeal)session.get(Appeal.class, idAppeal);
			if(appealNew.getCloseDate()!=null && appealNew.getCloseDate().compareTo(old.getCloseDate())!=0)
				old.setCloseDate(appealNew.getCloseDate());
			if(appealNew.getCourse()!=null && !appealNew.getCourse().equals(old.getCourse()))
				old.setCourse(appealNew.getCourse());
			if(appealNew.getDescription()!=null && !appealNew.getDescription().equals(old.getDescription()))
				old.setDescription(appealNew.getDescription());
			if(appealNew.getExamDate()!=null && appealNew.getExamDate().compareTo(old.getExamDate())!=0)
				old.setExamDate(appealNew.getExamDate());
			if(appealNew.getLocation()!=null && !appealNew.getLocation().equals(old.getLocation()))
				old.setLocation(appealNew.getLocation());
			if(appealNew.getMaxNumberOfInscribed()!=null && appealNew.getMaxNumberOfInscribed()!=old.getMaxNumberOfInscribed())
				old.setMaxNumberOfInscribed(appealNew.getMaxNumberOfInscribed());
			if(appealNew.getName()!=null && !appealNew.getName().equals(old.getName()))
				old.setName(appealNew.getName());
			if(appealNew.getOpenDate()!=null && appealNew.getOpenDate().compareTo(old.getOpenDate())!=0)
				old.setOpenDate(appealNew.getOpenDate());
			//	if(appealNew.getStudentsInscribed()!=null && appealNew.getStudentsInscribed().size()>0) CORREGGERE
			//		old.setStudentsInscribed(appealNew.getStudentsInscribed());
			res=old;
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Appeal> getAppealsFromProfessor(Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Appeal> res=null;
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			res=new HashSet<Appeal>(p.getAppeals());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public Set<Student> getStudetsIscribed(Long idAppeal) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Student> res=null;
		try{
			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			//	res=new HashSet<Student>(a.getStudentsInscribed()); CORREGGERE
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Deprecated
	@Override
	public Appeal setProfessor(Long idAppeal,Professor professor) {
		return null;
	}

	@Override
	public Appeal setProfessor(Long idAppeal,Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Appeal res=null;
		try{
			transaction=session.beginTransaction();

			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			Professor creatorProfessor =(Professor)session.get(Professor.class, idProfessor);
			a.setCreatorProfessor(creatorProfessor);
			res=a;
			transaction.commit();
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		return res;
	}
	//
	//	@Override
	//	public Appeal addStudentAtAppeal(Long idAppeal, Long idStudent) {
	//		Session session =HibernateUtil.getSessionFactory().openSession();
	//		Transaction transaction=null;
	//		Appeal res=null;
	//		try{
	//			transaction=session.beginTransaction();
	//
	//			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
	//			Student s=(Student)session.get(Student.class, idStudent);
	//			AppealStudent app=new AppealStudent(a, s, null, null, null);
	//				a.getAppeal_student().add(app);
	//				s.getAppeal_student().add(app);
	//			res=a;
	//			transaction.commit();
	//		}catch(Exception e){
	//			new MokException(e);
	//			transaction.rollback();
	//		}finally{
	//			session.close();
	//		}
	//		return res;
	//	}

	//<<<<<<< HEAD
	//	@Override
	//	public Appeal addStudentAtAppeal(Long idAppeal, Long idStudent) {
	//		Session session =HibernateUtil.getSessionFactory().openSession();
	//		Transaction transaction=null;
	//		Appeal res=null;
	//		try{
	//			transaction=session.beginTransaction();
	//
	//			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
	//			Student s=(Student)session.get(Student.class, idStudent);
	//			//	a.getStudentsInscribed().add(s); CORREGGERE
	//			res=a;
	//			transaction.commit();
	//		}catch(Exception e){
	//			new MokException(e);
	//			transaction.rollback();
	//		}finally{
	//			session.close();
	//		}
	//		return res;
	//	}
	//
	//	@Deprecated
	//	@Override
	//	public Appeal addStudentAtAppeal(Long idAppeal, Student student) {
	//		return null;
	//	}
	//=======
	//	@Override
	//	public Appeal addStudentAtAppeal(Long idAppeal, Long idStudent) {
	//		Session session =HibernateUtil.getSessionFactory().openSession();
	//		Transaction transaction=null;
	//		Appeal res=null;
	//		try{
	//			transaction=session.beginTransaction();
	//
	//			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
	//			Student s=(Student)session.get(Student.class, idStudent);
	//			//	a.getStudentsInscribed().add(s); CORREGGERE
	//			res=a;
	//			transaction.commit();
	//		}catch(Exception e){
	//			new MokException(e);
	//			transaction.rollback();
	//		}finally{
	//			session.close();
	//		}
	//		return res;
	//	}
	//
	//	@Deprecated
	//	@Override
	//	public Appeal addStudentAtAppeal(Long idAppeal, Student student) {
	//		return null;
	//	}
	//>>>>>>> refs/heads/master

	@Override
	public boolean removeStudentAtAppeal(Long idAppeal, Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		boolean ok=false;
		try{
			transaction=session.beginTransaction();
			/**
			 * TODO
			 * vedere cosa fare su studente
			 */
			Appeal a=(Appeal)session.get(Appeal.class, idAppeal);
			Student s=(Student)session.get(Student.class, idStudent);
			//	if(a.getStudentsInscribed().contains(s)){  CORREGGERE
			//		a.getStudentsInscribed().remove(s);
			//	}
			transaction.commit();
			ok=true;
		}catch(Exception e){
			new MokException(e);
			transaction.rollback();
		}finally{
			session.close();
		}
		if(ok)return true;
		else return false;
	}

	@Deprecated
	@Override
	public boolean removeStudentAtAppeal(Long idAppeal, Student student) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Appeal> getAppeals() {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Set<Appeal> res=null;
		try{
			Query q= session.createQuery("from Appeal");
			res=new HashSet<Appeal>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Appeal> getAppeals(Long idCourse) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<Appeal> res=null;
		try{
			Query q=session.createQuery("from Appeal where course.id=:courseId");
			q.setParameter("courseId", idCourse);
			res=new ArrayList<Appeal>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

}
