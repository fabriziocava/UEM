package it.unical.uniexam.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.dao.AppealStudentDAO;
import it.unical.uniexam.hibernate.dao.CarrierDAO;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
import it.unical.uniexam.hibernate.domain.AppealStudent.STATE;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.util.HibernateUtil;

/**
 * 
 * @author luigi
 *
 */

@Repository
public class AppealStudentDAOImpl implements AppealStudentDAO {

	@Override
	public Boolean signAppealStudentsByCommissary(ArrayList<Long> signStudents,Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (Long idAppeal : signStudents) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				if(p.getSetAsCommission().contains(appealStudent.getAppeal().getCourse()) ||
						p.getSetAsCommission().contains(appealStudent.getCourse())){
					Course course=null;
					if((appealStudent.getAppeal()!=null && appealStudent.getAppeal().getCourse()!=null && appealStudent.getAppeal().getCourse().getId()!=null)){
						course=appealStudent.getAppeal().getCourse();
					}else if((appealStudent.getCourse()!=null && appealStudent.getCourse().getId()!=null)){
						course=appealStudent.getCourse();
					}
					if(course!=null){
						appealStudent.setState(STATE.AWAITING_ACKNOWLEDGMENT);
					}
					else
						throw new Exception("Don't have a course");
					//invio email agli studenti
				}else{
					throw new Exception("This appeal isn't of the professor");
				}
			}
			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}


	@Override
	public ArrayList<ArrayList<Object>> getListStudentFromProfessorRegularAndNotForCommissionar(
			Long idProfessor, STATE state) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<ArrayList<Object>> res=new ArrayList<ArrayList<Object>>();
		ArrayList<Object> reg=new ArrayList<Object>();
		ArrayList<Object> noreg=new ArrayList<Object>();
		res.add(reg);
		res.add(noreg);
		try{
//			Professor p=(Professor)session.get(Professor.class, idProfessor);
			/**
			 * non posso fare una query unica poichè: essendo che uno dei due
			 * appello o corso è null, quando vado a mettere nella query che bisigna fare
			 * un controllo su entrambi allora hibernate mette nella clausola from
			 * appealstudent in join sia con appeal che con course quindi essendo che almeno 
			 * uno dei due sarà null il join fallisce e come risultato non da nulla 
			 */
			/**
			 * quindi adesso devo:
			 * 	provare che questa funzione funzioni!
			 * 	fare effettuare il sign al comissario nello stesso modo di come
			 * 		lo effettua il professore con l'inserimento della passwd
			 * 	
			 * 	Lavorare sui gruppi, ma mi sa che non c'è tempo..quindi non so
			 * 
			 */
			Professor comm=(Professor)session.get(Professor.class, idProfessor);
			//			List<AppealStudent> list1=session.createCriteria(AppealStudent.class)
			////					.add(Restrictions.eq("course", "is not null"))
			//					.add(Restrictions.eq("state", AppealStudent.STATE.NOT_SIGNED_BY_COMMISSARY)).list();
			//			List<AppealStudent> list2=session.createCriteria(AppealStudent.class)
			//					.add(Restrictions.eq("appeal", "is not null"))
			//					.add(Restrictions.eq("state", AppealStudent.STATE.NOT_SIGNED_BY_COMMISSARY)).list();
			Query q=session.createQuery("from AppealStudent where state=:i");
			q.setParameter("i", state);
			List<AppealStudent> list1=q.list();
			List<AppealStudent> list=new ArrayList<AppealStudent>();
			for (AppealStudent appealStudent : list1) {
				Course c=null;
				if(appealStudent.getCourse()!=null){
					c=appealStudent.getCourse();
				}
				else if(appealStudent.getAppeal()!=null){
					c=appealStudent.getAppeal().getCourse();
				}
				if(c!=null){
					if(c.getCommissionProfessors().contains(comm))
						list.add(appealStudent);
				}
			}
			for (AppealStudent appealStudent2 : list) {
				Course c1=appealStudent2.getCourse();
				if(c1==null)
					c1=appealStudent2.getAppeal().getCourse();
				ArrayList<RequestedCourse>requested=new ArrayList<RequestedCourse>(c1.getRequestedCourses());
				if(appealStudent2.getState()==state){
					ArrayList<Carrier> carrier=new ArrayList<Carrier>(appealStudent2.getStudent().getCarrier());
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
						reg.add(appealStudent2);
					else{
						ArrayList<Object>tuple=new ArrayList<Object>();
						tuple.add(appealStudent2);
						tuple.add(miss);
						noreg.add(tuple);
					}
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
	public ArrayList<ArrayList<Object>> getListStudentFromProfessorRegularAndNotForState(
			Long idProfessor, STATE state) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<ArrayList<Object>> res=new ArrayList<ArrayList<Object>>();
		ArrayList<Object> reg=new ArrayList<Object>();
		ArrayList<Object> noreg=new ArrayList<Object>();
		res.add(reg);
		res.add(noreg);
		try{
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			List<AppealStudent> list=session.createCriteria(AppealStudent.class)
					.add(Restrictions.eq("professor", p)).list();
			for (AppealStudent appealStudent2 : list) {
				Course c1=appealStudent2.getCourse();
				if(c1==null)
					c1=appealStudent2.getAppeal().getCourse();
				ArrayList<RequestedCourse>requested=new ArrayList<RequestedCourse>(c1.getRequestedCourses());
				if(appealStudent2.getState()==state){
					ArrayList<Carrier> carrier=new ArrayList<Carrier>(appealStudent2.getStudent().getCarrier());
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
						reg.add(appealStudent2);
					else{
						ArrayList<Object>tuple=new ArrayList<Object>();
						tuple.add(appealStudent2);
						tuple.add(miss);
						noreg.add(tuple);
					}
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
	public Boolean prepareForSignAppealStudent(AppealStudent appealStudent,
			Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);

			if(p.getSetHoldersCourse().contains(appealStudent.getCourse())){
				appealStudent.setState(STATE.NOT_SIGNED_BY_PROFESSOR);
				appealStudent.setProfessor(p);
				Student s=(Student) session.get(Student.class, appealStudent.getStudent().getId());
				session.save(appealStudent);
				s.getAppeal_student().add(appealStudent);
			}else{
				throw new Exception("This course isn't of the professor");
			}

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

	@Override
	public Boolean declassifyStudents(ArrayList<Long> listAppealStudents,
			Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			ArrayList<Long>removable=new ArrayList<Long>();
			for (Long idAppeal : listAppealStudents) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				if(p.getAppeals().contains(appealStudent.getAppeal())){
					appealStudent.setState(STATE.NO_STATE);
				}else{
					if(p.getSetHoldersCourse().contains(appealStudent.getCourse())){
						removable.add(appealStudent.getId());
					}
					else
						throw new Exception("This appeal isn't of the professor");
				}
			}
			if(removable.size()>0)
				removeAppealStudents(removable, idProfessor);
			transaction.commit();
			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

	@Override
	public String signAppealStudentsByProfessor(ArrayList<Long> signStudents,Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		String ris="Success";
		try{
			transaction=session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (Long idAppeal : signStudents) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				if(p.getAppeals().contains(appealStudent.getAppeal()) ||
						p.getSetHoldersCourse().contains(appealStudent.getCourse())){
					Course course=null;
					if((appealStudent.getAppeal()!=null && appealStudent.getAppeal().getCourse()!=null && appealStudent.getAppeal().getCourse().getId()!=null)){
						course=appealStudent.getAppeal().getCourse();
					}else if((appealStudent.getCourse()!=null && appealStudent.getCourse().getId()!=null)){
						course=appealStudent.getCourse();
					}
					if(course!=null){
						Date now=new Date();
						Query q=session.createQuery("from ExamSession where dataInizio <= :p1 and dataFine >= :p2 and degreecourse.id =:p3");
						q.setParameter("p1", now);
						q.setParameter("p2", now);
						q.setParameter("p3", course.getDegreeCourse().getId());
						ExamSession examSession=(ExamSession) q.uniqueResult();
						if(examSession!=null)
							appealStudent.setState(STATE.NOT_SIGNED_BY_COMMISSARY);
						else{
							ris="ErrorSession";
							throw new Exception("Non sei in sessione");
						}
					}
					else{
						ris="ErrorCourse";
						throw new Exception("Don't have a course");
					}
					//invio email ai commissari
				}else{
					ris="ErrorAppeal";
					throw new Exception("This appeal isn't of the professor");
				}
			}
			transaction.commit();

		}catch(Exception e){
			new MokException(e);
			if(ris.equals("Success"))
				ris="ErrorHibernate";
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}

	@Override
	public Boolean prepareAppealStudentsForSign(ArrayList<Long> prepareStudents,Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();

			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (Long idAppeal : prepareStudents) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				if(p.getAppeals().contains(appealStudent.getAppeal())){
					if(((appealStudent.getAppeal()!=null 
							&& appealStudent.getAppeal().getCourse()!=null && appealStudent.getAppeal().getCourse().getId()!=null)
							|| 
							(appealStudent.getCourse()!=null && appealStudent.getCourse().getId()!=null))
							&& appealStudent.getTemporany_vote()!=null && appealStudent.getTemporany_vote()>=18
							&& appealStudent.getTemporany_vote()<=31
							)
						appealStudent.setState(STATE.NOT_SIGNED_BY_PROFESSOR);
					else
						throw new Exception("Don't have a course or vote is less than 18 or greater than 31");
				}else{
					throw new Exception("This appeal isn't of the professor");
				}
			}

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}


	@Override
	public Boolean removeAppealStudents(ArrayList<Long> idAppeals, Long idProfessor) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();
			Professor p=(Professor)session.get(Professor.class, idProfessor);
			for (Long idAppeal : idAppeals) {
				AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
				if(p.getAppeals().contains(appealStudent.getAppeal())){
					Student s=appealStudent.getStudent();
					Appeal a=appealStudent.getAppeal();
					s.getAppeal_student().remove(appealStudent);
					a.getAppeal_student().remove(appealStudent);
					session.delete(appealStudent);
				}else{
					if(p.getSetHoldersCourse().contains(appealStudent.getCourse())){
						Student s=appealStudent.getStudent();
						s.getAppeal_student().remove(appealStudent);
						session.delete(appealStudent);
					}else
						throw new Exception("This appeal isn't of the professor");
				}
			}

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}


	@Override
	public Boolean modifyNote(Long idAppeal, String value) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();
			
			AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
			appealStudent.setNote(value);
			
			transaction.commit();
			
			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}
	
	@Override
	public Boolean modifyVote(Long idAppeal, String valu) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Boolean ris=false;
		try{
			transaction=session.beginTransaction();
			AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
			Double value=Double.valueOf(valu);
			appealStudent.setTemporany_vote(value);

			transaction.commit();

			ris=true;
		}catch(Exception e){
			new MokException(e);
			ris=false;
			transaction.rollback();
		}finally{
			session.close();
		}
		return ris;
	}
	
	@Override
	public Long addAppealStudent(AppealStudent appealStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();

			if(appealStudent.getAppeal().getId()==null)
				return null;
			if(appealStudent.getStudent().getId()==null)
				return null;
			if(appealStudent.getState()==null)
				appealStudent.setState(AppealStudent.STATE.NO_STATE);
			Appeal app=(Appeal)session.get(Appeal.class, appealStudent.getAppeal().getId());
			Student stud=(Student)session.get(Student.class, appealStudent.getStudent().getId());

			app.getAppeal_student().add(appealStudent);
			stud.getAppeal_student().add(appealStudent);

			app.setCurrNumberOfInscribed(app.getCurrNumberOfInscribed()+1);
			
			id=(Long)session.save(appealStudent);	

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
	public Long addAppealStudent(STATE state, Long idAppeal, Long idStudent,
			String note, Double vote) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		Transaction transaction=null;
		Long id=null;
		try{
			transaction=session.beginTransaction();

			if(idAppeal==null)
				return null;
			if(idStudent==null)
				return null;
			Appeal app=(Appeal)session.get(Appeal.class, idAppeal);
			Student stud=(Student)session.get(Student.class, idStudent);

			AppealStudent appealStudent=new AppealStudent(app, stud, state, vote, note);

			if(state==null)
				appealStudent.setState(AppealStudent.STATE.NO_STATE);
			app.getAppeal_student().add(appealStudent);
			stud.getAppeal_student().add(appealStudent);

			app.setCurrNumberOfInscribed(app.getCurrNumberOfInscribed()+1);
			
			id=(Long)session.save(appealStudent);	

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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppealStudent> getAppealStudent(Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=null;
		try{
			Query q=session.createQuery("from AppealStudent where student.id=:studentId");
			q.setParameter("studentId", idStudent);
			res=new ArrayList<AppealStudent>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public AppealStudent removeAppealStudent(Long idAppealStudent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AppealStudent appealStudent = null;
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			appealStudent = (AppealStudent) session.get(AppealStudent.class, idAppealStudent);
			Student student = (Student) session.get(Student.class, appealStudent.getStudent().getId());
			Appeal appeal = (Appeal) session.get(Appeal.class, appealStudent.getAppeal().getId());
			student.getAppeal_student().remove(appealStudent);
			appeal.getAppeal_student().remove(appealStudent);
			
			appeal.setCurrNumberOfInscribed(appeal.getCurrNumberOfInscribed()-1);
			if(appeal.getCurrNumberOfInscribed()<0)
				appeal.setCurrNumberOfInscribed(0);
			
			session.delete(appealStudent);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			appealStudent = null;
			new MokException(e);
		} finally {
			session.close();
		}
		return appealStudent;
	}
	
    @Override
    public Boolean prepareAppealStudentsForSign(ArrayList<Long> prepareStudents) {
            Session session =HibernateUtil.getSessionFactory().openSession();
            Transaction transaction=null;
            Boolean ris=false;
            try{
                    transaction=session.beginTransaction();

                    for (Long idAppeal : prepareStudents) {
                            AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
                            appealStudent.setState(STATE.NOT_SIGNED_BY_PROFESSOR);
                    }

                    transaction.commit();

                    ris=true;
            }catch(Exception e){
                    new MokException(e);
                    ris=false;
                    transaction.rollback();
            }finally{
                    session.close();
            }
            return ris;
    }

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppealStudent> getAppealStudentForCarrier(Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=null;
		try{
			Query q=session.createQuery("from AppealStudent where student.id = :studentId and state <> :appealState");
			q.setParameter("studentId", idStudent);
			q.setParameter("appealState", AppealStudent.STATE.NO_STATE);
			res=new ArrayList<AppealStudent>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<AppealStudent> getAppealStudentForVerbalToBeSigned(
			Long idStudent) {
		Session session =HibernateUtil.getSessionFactory().openSession();
		ArrayList<AppealStudent> res=null;
		try{
			Query q=session.createQuery("from AppealStudent where student.id = :studentId and state = :appealState");
			q.setParameter("studentId", idStudent);
			q.setParameter("appealState", AppealStudent.STATE.AWAITING_ACKNOWLEDGMENT);
			res=new ArrayList<AppealStudent>(q.list());
		}catch(Exception e){
			new MokException(e);
		}finally{
			session.close();
		}
		return res;
	}

	@Override
	public boolean setState(ArrayList<Long> idAppealStudentList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Boolean ris = false;
        try {
        	transaction=session.beginTransaction();
        	Long idCarrier = null;
        	CarrierDAO carrierDAO = new CarrierDAOImpl();
        	for (Long idAppeal : idAppealStudentList) {
        		AppealStudent appealStudent=(AppealStudent)session.get(AppealStudent.class, idAppeal);
        		appealStudent.setState(STATE.LOADED_IN_SECRETERY);
        		idCarrier = carrierDAO.addCarrier(appealStudent.getAppeal().getCourse().getId(), appealStudent.getStudent().getId(), appealStudent.getTemporany_vote().intValue(), appealStudent.getAppeal().getExamDate());
        		if(idCarrier!=null) {
        			removeAppealStudent(appealStudent.getId());
        		}
        	}
        	transaction.commit();
        	ris=true;
        } catch(Exception e){
        	new MokException(e);
        	ris=false;
        	transaction.rollback();
        } finally {
        	session.close();
        }
        return ris;
	}


	@Override
	public AppealStudent getThisAppealStudent(Long idAppealStudent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		AppealStudent appealStudent = null;
		try {
			appealStudent = (AppealStudent) session.get(AppealStudent.class, idAppealStudent);
		} catch (Exception e) {
			new MokException(e);
		} finally {
			session.close();
		}
		return appealStudent;
	}

}
