package it.unical.uniexam.hibernate.domain.utility;

public interface SessionAttribute {
	public Object getInstance(String attributes);
	public String getAttributes(Object instance);
}
