package it.unical.uniexam.hibernate.util;


import it.unical.uniexam.MokException;
import it.unical.uniexam.hibernate.domain.AppealStudent;
import it.unical.uniexam.hibernate.domain.Carrier;
import it.unical.uniexam.hibernate.domain.Course;
import it.unical.uniexam.hibernate.domain.DegreeCourse;
import it.unical.uniexam.hibernate.domain.Department;
import it.unical.uniexam.hibernate.domain.Appeal;
import it.unical.uniexam.hibernate.domain.ExamSession;
import it.unical.uniexam.hibernate.domain.Group;
import it.unical.uniexam.hibernate.domain.Manager;
import it.unical.uniexam.hibernate.domain.Professor;
import it.unical.uniexam.hibernate.domain.RequestedCourse;
//import it.unical.uniexam.hibernate.domain.Session;
import it.unical.uniexam.hibernate.domain.Student;
import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.utility.CommentOfPost;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author luigi
 *
 */
public class HibernateUtil {

        private static SessionFactory sessionFactory;
        static{
                try{
                        sessionFactory=new Configuration()
                        .configure("resources/hibernate.cfg.xml")
                        .addPackage("it.unical.uniexam.hibernate.domain")
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Department.class)
                        .addAnnotatedClass(RequestedCourse.class)
                        .addAnnotatedClass(Course.class)
                        .addAnnotatedClass(DegreeCourse.class)
                        .addAnnotatedClass(Appeal.class)
                        .addAnnotatedClass(Group.class)
                        .addAnnotatedClass(PhoneNumber.class)
                        .addAnnotatedClass(Email.class)
                        .addAnnotatedClass(CommentOfPost.class)
                        .addAnnotatedClass(PostOfGroup.class)
                        .addAnnotatedClass(Professor.class)
                        .addAnnotatedClass(Manager.class)
                        .addAnnotatedClass(Student.class)
                        .addAnnotatedClass(ExamSession.class)
                        .addAnnotatedClass(AppealStudent.class)
			.addAnnotatedClass(Carrier.class)
                        .buildSessionFactory();
                }catch(Exception e){
                        new MokException(e);
                }
        }
        //si devono aggiungere tutte le classi che vogliamo siano utilizzate nel db

        public static SessionFactory getSessionFactory() {
                return sessionFactory;
        }


}
