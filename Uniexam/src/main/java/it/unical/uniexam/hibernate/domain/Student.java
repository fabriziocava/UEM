package it.unical.uniexam.hibernate.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;




@Entity
@Table(name="STUDENT")
public class Student {


	public Student() {}

	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue
	private Long id;

	@Column(name="STUDENT_NAME",
			nullable=false,
			length=100)
	private String name;

	@Embedded
	private Address address;


	@OneToOne(mappedBy="student", cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private MatrDetails matrDetails;


	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="STUDENT_PHONE",
	joinColumns={
			@JoinColumn(name="STUDENT_ID")
			}, 
	inverseJoinColumns={
			@JoinColumn(name="PHONE_ID")
			})
	private Set<PhoneNumber> phoneNumbers=new HashSet<PhoneNumber>();


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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}


	public MatrDetails getMatrDetails() {
		return matrDetails;
	}


	public void setMatrDetails(MatrDetails matrDetails) {
		this.matrDetails = matrDetails;
	}


	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}


	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Override
	public String toString() {
//		StringBuilder sb=new StringBuilder("");
//		
//		sb.append(name+" "+address.getCity()+" "+ address.getState()+" "+matrDetails.getNumber());
//		
//		sb.append("\n");
//		Set<PhoneNumber> phoneNumbers2 = getPhoneNumbers();
//		if(phoneNumbers2!=null && phoneNumbers2.size()>0)
//		for (PhoneNumber phone : phoneNumbers2) {
//			sb.append(phone.getType()+" "+phone.getNumber()+"\n");
//		}
		
		return name+" "+id;
	
	
	}

}
