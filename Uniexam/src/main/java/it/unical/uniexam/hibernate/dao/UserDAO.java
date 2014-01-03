package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.User;

public interface UserDAO {
public User getUser(String email,String password);
}
