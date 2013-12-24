package it.unical.uniexam.hibernate.domain.utility;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	
	
	public Address() {
	}
	
	public Address(String city, String state, String zip, String street) {
		super();
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.street = street;
	}
/**
 * non sarà una  entità
 * ovvero non si crea una tabella per questa classe
 */
	
	
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="STATE")
	private String state;
	
	@Column(name="ZIP")
	private String zip;
	
	@Column(name="STREET")
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
