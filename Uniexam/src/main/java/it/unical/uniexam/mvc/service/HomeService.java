package it.unical.uniexam.mvc.service;

import it.unical.uniexam.hibernate.domain.Session;

public interface HomeService {
public Session loginUser(String email,String password);
}
