package it.unical.mat.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="CITY")
public class City {

	//molto importante le classi devono avere un costruttore vuoto
	// e devono avere per tutti i campi i metodi get and set
	//serve a hinernate, siccome usa la reflexion 
	
	public City() {}
	
	//un altro per semplicità me lo creo io
	
	public City(String name) {
		this.name = name;
	}
	
	
	
	@Id
	@GeneratedValue
	@Column(name="ID_CITY")
	private Long id;
	


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

	@Column(name="CITY")
	private String name;



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id+" "+name;
	}
	
	
	
}
