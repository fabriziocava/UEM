package it.unical.uniexam.hibernate.domain.utility;

import java.io.Serializable;
import java.util.ArrayList;

public class EventsCalendar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8204155046860543514L;

	ArrayList<Event>events=new ArrayList<Event>();

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
}
