package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;
import java.util.Set;

import it.unical.uniexam.hibernate.domain.User;
import it.unical.uniexam.hibernate.domain.User.TYPE;
import it.unical.uniexam.hibernate.domain.utility.Address;
import it.unical.uniexam.hibernate.domain.utility.Email;
import it.unical.uniexam.hibernate.domain.utility.PhoneNumber;

public interface UserDAO {
	public Long addUser(String name, String surname, String password, Address address, Set<Email> emails, Set<PhoneNumber> phoneNumbers);
	public User getUser(String email,String password,ArrayList<Object>result);
	public Boolean registerSession(String idSession,Long idUser);
	public String getIdSession(Long idUser);
	public User getUser(String idSession);
	public Boolean unRegisterSession(String idSession);
	public User getUser(Long idUser);
}
