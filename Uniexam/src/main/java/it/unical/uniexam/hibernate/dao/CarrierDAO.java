package it.unical.uniexam.hibernate.dao;

import it.unical.uniexam.hibernate.domain.Carrier;

import java.util.ArrayList;
import java.util.Date;

public interface CarrierDAO {
	
	public Long addCarrier(Long idCourse, Long idStudent, Integer vote, Date date);
	public ArrayList<Carrier> getCarrierList(Long idStudent);
	
}
