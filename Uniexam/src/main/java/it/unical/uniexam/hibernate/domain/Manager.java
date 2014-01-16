package it.unical.uniexam.hibernate.domain;

import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

import java.net.URL;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @category Actor 
 * 
 * This class describe the actor Manager
 * each Manager extend User class, in addition have a set of a phone numbers
 * At each Manager 	is associated with a department
 * 					
 * 					something else?
 * 
 * 
 * @author luigi
 *
 */

@Entity
@Table(name="MANAGER")
@PrimaryKeyJoinColumn(name="ID")
public class Manager extends User{
	

	public Manager(TYPE type, String name, String surname, URL webSite,
			String password, Address address, Set<Email> emails,
			Set<PhoneNumber> phoneNumbers, Department department_associated) {
		super(type, name, surname, webSite, password, address,emails,phoneNumbers);
		this.department_associated=department_associated;
	}

	
	
	public Manager(){
		
	}

	
	@OneToOne(fetch=FetchType.LAZY) // da controllare
	Department department_associated;
	

	
	@Column( name = "IMAGE" )
    @Lob
    private Blob fileimage;
    
    /**
     * Implementation
     */

    public Blob getFileimage() {
            return fileimage;
    }

    public void setFileimage(Blob fileimage) {
            this.fileimage = fileimage;
    }

	/**
	 * 
	 * Second part of implementation
	 * 
	 */
	
	public Long getManager_id() {
		return id;
	}

	
	public void setManager_id(Long manager_id) {
		this.id = manager_id;
	}

	
	
	public Department getDepartment_associated() {
		return department_associated;
	}

	public void setDepartment_associated(Department department_associated) {
		this.department_associated = department_associated;
	}
	
}
