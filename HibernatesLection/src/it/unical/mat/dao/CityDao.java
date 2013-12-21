package it.unical.mat.dao;

import it.unical.mat.domain.City;

import java.util.List;

public interface CityDao {
	public void deleteCity(Long id);
	public void updateCity(Long id, String name);
	public List<City> listCities();
	public Long saveCity(String name);
	public City getCityWithId(Long id);
}
