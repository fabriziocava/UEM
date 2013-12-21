//package it.unical.uniexam.hibernate.dao.impl;
//
//import it.unical.uniexam.hibernate.dao.StudentDao;
//import it.unical.uniexam.hibernate.domain.Address;
//import it.unical.uniexam.hibernate.domain.MatrDetails;
//import it.unical.uniexam.hibernate.domain.Student;
//import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;
//import it.unical.uniexam.hibernate.util.HibernateUtil;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//
//public class StudentDaoImpl implements StudentDao {
//
//	@Override
//	public void addPhoneNumber(Long id, String type, String number) {
//		Session session =HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction=null;
//		try{
//			transaction=session.beginTransaction();
//				
//			Student student=(Student)session.get(Student.class, id);
//			
//			PhoneNumber phone=new PhoneNumber();
//			phone.setNumber(number);
//			phone.setType(type);
//			
//			student.getPhoneNumbers().add(phone);
//
//			session.update(student);
//			
//			transaction.commit();
//			session.close();
//		}catch(Exception e){
//			transaction.rollback();
//		}
//	}
//	
//	
//	@Override
//	public Long saveStudent(String name, Address addres, String matrNumber) {
//		Session session =HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction=null;
//		Long id=null;
//		try{
//			transaction=session.beginTransaction();
//			
//			Student student=new Student();
//			
//			student.setName(name);
//			
//			MatrDetails m=new MatrDetails();
//			m.setNumber(matrNumber);
//			student.setMatrDetails(m);
//			
//			student.setAddress(addres);
//			
//			id=(Long)session.save(student);
//			transaction.commit();
//			session.close();
//		}catch(Exception e){
//			transaction.rollback();
//		}
//		
//		return id;
//	}
//
//	@Override
//	public void deleteStudent(Long id) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public List listStudent() {
//		Session session =HibernateUtil.getSessionFactory().openSession();
//		Long id=null;
//		try{
//			List<Student>list=session.createQuery("from Student").list();
//			session.close();
//			return list;
////			for (Student student : list) {
////				System.out.println(student.toString());
////			}
//		}catch(Exception e){
//		}
//		return null;
//	}
//
//}
