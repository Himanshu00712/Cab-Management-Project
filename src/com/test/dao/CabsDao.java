package com.test.dao;

import java.util.List;

import com.test.model.Cab;
import com.test.model.City;

public interface CabsDao {

	void registerCab(String cabNumber);
	List<Cab> getAvailableCabsByCity(String cityName);
	void addCabtoCity(City city, String cabNumber);
	void changeCabStatus(String cabNumber, Cab.Status status);
	
}
