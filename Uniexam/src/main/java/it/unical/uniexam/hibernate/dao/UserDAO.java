package it.unical.uniexam.hibernate.dao;

import java.util.ArrayList;
import java.util.Map;

import it.unical.uniexam.hibernate.domain.User;

public interface UserDAO {
	public User getUser(String email,String password,ArrayList<Object>result);
	public Boolean registerSession(String idSession,Long idUser);
	public String getIdSession(Long idUser);
	public User getUser(String idSession);
	public Boolean unRegisterSession(String idSession);
	public User getUser(Long idUser);
	public Map<String,String> getPersonalization(User user);
	public Map<String,String> getPersonalization(Long userId);
	public Boolean updatePersonalizzation(Map<String,String> personalizzation,Long userId);
	public Boolean updatePersonalizzation(String personalizzation,Long userId);
	public Boolean resetPersonalizzation(Long userId);
}
