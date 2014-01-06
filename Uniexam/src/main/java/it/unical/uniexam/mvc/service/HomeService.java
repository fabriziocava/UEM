package it.unical.uniexam.mvc.service;

import java.util.ArrayList;


public interface HomeService {
	public final static int NO_ERROR=0;
	public final static int ERROR_USER=1;
	public final static int ERROR_PASSWD=2;
	
	public ArrayList<Object> loginUser(String email,String password);
	public void registerSession(String idSession, Long idUser);
	public Boolean unRegisterSession(String idSession);
	
}
