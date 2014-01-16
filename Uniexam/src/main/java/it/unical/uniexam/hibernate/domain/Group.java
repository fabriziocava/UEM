package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.utility.PostOfGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @category Event 
 * 
 * This class describe a Group
 * each Group have a code, ( map<Date,PostOfGroup> posts OR List<PostOfGroup>) ,a name,a description, a object
 * At each Exam         have a professor like holder (titolare) same prof. that is holder in the course!,
 *                                          have many professors like a commission
 *                                         refer exact a one course
 *                                         refer exact a one session of exam
 *                                        have zero or many students inscribed ? *** 
 *                                         
 *                                         something else?
 * 
 * @functions
 *         when a post is put on group, the event send a mail a each member of a group, So each member can 
 *         choose if decider receive a notification or not        
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="GROUP_TABLE")
public class Group {

        
        public Group(String name, String object, String description,
                        Integer levelOfPolicy, Professor creator,Course cours) {
                this.name = name;
                this.object = object;
                this.description = description;
                this.levelOfPolicy = levelOfPolicy;
                this.creator = creator;
                course=cours;
        }

        /**
         * only the user that have created it can publish
         */
        public static final int POLICY_1=1;
        /**
         * only the user can publish and the other user can comment
         */
        public static final int POLICY_2=2;
        /**
         * both creator's user and other can publish, And of course comments 
         */
        public static final int POLICY_3=3;
        
        public enum GroupState{
                OPEN,CLOSE;
        }
        
        @Id
        @GeneratedValue
        @Column(name="GROUP_ID")
        Long id;
        
        @Column(name="STATE")
        GroupState state;
        
        @Column(name="NAME")
        String name;
        
        @Column(name="OBJECT")
        String object;
        
        @Column(name="DESCRIPTION")
        String description;
        
        @Column(name="POLICY")
        Integer levelOfPolicy;
        
        @ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="COURSE_GROUP")
        Course course;
        
        @ManyToOne
        @JoinColumn(name="USER_GRUOP")
        User creator;

        @ManyToMany(fetch=FetchType.LAZY)
        @JoinTable(name="GROUP_USERS", 
        joinColumns={
                        @JoinColumn(name="GROUP_ID")
        }, 
        inverseJoinColumns={
                        @JoinColumn(name="ID")
        })
        Set<User>iscribed=new HashSet<User>();
        
        /**
         * per inserire un nuovo messaggio devo utilizzare 
         * posts.add(0,PostOfGruop);
         * in questo modo l'elemento viene aggiunto all'inizio della lista
         * e quindi per prenderlo devo utilizzare 
         * posts.get(0);
         * 
         */
        @OneToMany(cascade=CascadeType.ALL)
        @JoinTable(name="GROUP_POST",
        joinColumns={
                        @JoinColumn(name="GROUP_ID")
                        }, 
        inverseJoinColumns={
                        @JoinColumn(name="POST_OF_GROUP_ID")
                        })
        List<PostOfGroup>posts=new ArrayList<PostOfGroup>();

        //IMPLEMENTATION        
        
        @Override
        public String toString() {
                return "Name: "+name+"; Creator: "+creator.name;
        }
        
        public Group() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getObject() {
                return object;
        }

        public void setObject(String object) {
                this.object = object;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public Integer getLevelOfPolicy() {
                return levelOfPolicy;
        }

        public void setLevelOfPolicy(Integer levelOfPolicy) {
                this.levelOfPolicy = levelOfPolicy;
        }

        public User getCreator() {
                return creator;
        }

        public void setCreator(User creator) {
                this.creator = creator;
        }

        public List<PostOfGroup> getPosts() {
                return posts;
        }

        public void setPosts(List<PostOfGroup> posts) {
                this.posts = posts;
        }

        public Set<User> getIscribed() {
                return iscribed;
        }

        public void setIscribed(Set<User> iscribed) {
                this.iscribed = iscribed;
        }

        public GroupState getState() {
                return state;
        }

        public void setState(GroupState state) {
                this.state = state;
        }

        public Course getCourse() {
                return course;
        }

        public void setCourse(Course course) {
                this.course = course;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((course == null) ? 0 : course.hashCode());
                result = prime * result + ((creator == null) ? 0 : creator.hashCode());
                result = prime * result
                                + ((description == null) ? 0 : description.hashCode());
                result = prime * result + ((id == null) ? 0 : id.hashCode());
                result = prime * result + ((name == null) ? 0 : name.hashCode());
                result = prime * result + ((object == null) ? 0 : object.hashCode());
                result = prime * result + ((posts == null) ? 0 : posts.hashCode());
                result = prime * result + ((state == null) ? 0 : state.hashCode());
                return result;
        }

        @Override
        public boolean equals(Object obj) {
                if (this == obj)
                        return true;
                if (obj == null)
                        return false;
                if (getClass() != obj.getClass())
                        return false;
                Group other = (Group) obj;
                if (id == null) {
                        if (other.id != null)
                                return false;
                } else if (!id.equals(other.id))
                        return false;
                return true;
        }

                
        
}