package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;

import it.unical.uniexam.hibernate.domain.User;

public interface UserDAO {
	public User getUser(String email,String password,ArrayList<Object>result);
	public Boolean registerSession(String idSession,Long idUser);
	public String getIdSession(Long idUser);
	public User getUser(String idSession);
	public Boolean unRegisterSession(String idSession);
	public User getUser(Long idUser);
}
