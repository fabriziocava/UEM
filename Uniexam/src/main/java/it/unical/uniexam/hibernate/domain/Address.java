package it.unical.uniexam.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
/**
 * non sarà una  entità
 * ovvero non si crea una tabella per questa classe
 */
	
	@Column(name="CITY")
	private String city;
	@Column(name="STATE")
	private String state;
	private String zip;
	private String street;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	
	
	
}
