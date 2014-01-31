package it.unical.uniexam.mvc.controll.professor;

import java.net.URL;

public class News {

	
	public News(String message, String link) {
		super();
		this.message = message;
		this.link = link;
	}
	String message;
	String link;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	
	
}
