package it.unical.uniexam.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Structure 
 * 
 * This class describe a Degree Course (CDL)
 * each DC have a code, a name; 
 *                 and belongs at an arrangement (ordinamento), and a department
 * At each DC we have a number of course and a number of student inscribed 
 * 
 * @author luigi
 * modified by fabrizio
 *
 */

@Entity
@Table(name="DEGREE_COURSE")
public class DegreeCourse {
        
        /*
         * CONSTRUCTORS
         */
        public DegreeCourse() {
        
        }
        
        public DegreeCourse(String name, Department department_associated) {
                this.name = name;
                this.department_associated = department_associated;
        }
        /*
         * END_CONSTRUCTORS
         */
        
        @Id
        @GeneratedValue
        @Column(name="DEGREE_COURSE_ID")
        private Long id;
        
        @Column(name="NAME", nullable=false, unique=true)
        private String name;
        
        @ManyToOne(fetch=FetchType.LAZY)
        Department department_associated;
        
        @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
        @JoinTable(name="DEGREECOURSE_STUDENT",
                        joinColumns = {
                                @JoinColumn(name="DEGREE_COURSE_ID")
                        },
                        inverseJoinColumns = {
                                @JoinColumn(name="STUDENT_ID")
                        }
                )
        private Set<Student> students = new HashSet<Student>();

        @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
        @JoinTable(name="DEGREECOURSE_COURSE",
                        joinColumns = {
                                @JoinColumn(name="DEGREE_COURSE_ID")
                        },
                        inverseJoinColumns = {
                                @JoinColumn(name="COURSE_ID")
                        }
                )
        private Set<Course> courses = new HashSet<Course>();
        
        /*
         * SETTER
         */
        public void setId(Long id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setDepartment_associated(Department department_associated) {
                this.department_associated = department_associated;
        }

        public void setStudents(Set<Student> students) {
                this.students = students;
        }
        
        public void setCourses(Set<Course> courses) {
                this.courses = courses;
        }        
        /*
         * END_SETTER
         */


        /*
         * GETTER
         */
        public Long getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public Department getDepartment_associated() {
                return department_associated;
        }

        public Set<Student> getStudents() {
                return students;
        }
        
        public Set<Course> getCourses() {
                return courses;
        }        
        /*
         * END_GETTER
         */
        
}
