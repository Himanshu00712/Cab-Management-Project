package com.test.dao;

import com.test.model.City;

public interface CityDao {

	void addCity(String cityName);
	City getCity(String cityName);
	
}
