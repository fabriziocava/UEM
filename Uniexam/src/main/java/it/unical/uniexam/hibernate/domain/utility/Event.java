package it.unical.uniexam.hibernate.domain.utility;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4133793904157155966L;

	public String startDate;
	public String endDate;
	public String title;
	public String allDay;
	public Integer id;
//	public String url;
//	public Boolean editable;    // dragged and resized
//	public String color;   //in css format
//	public String backgroundColor;
//	public String borderColor;
	public String textColor;
	public Event(String title, String startDate, String endDate, String allDay) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.title = title;
		this.allDay = allDay;
	}
	public Event() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Event other = (Event) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
