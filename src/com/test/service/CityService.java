package com.test.service;

import com.test.dao.CityDao;
import com.test.dao.CityDaoImpl;

public class CityService {

	private final CityDao cityDaoImpl;
	
	
	public CityService(CityDao cityDaoImpl) {
		super();
		this.cityDaoImpl = cityDaoImpl;
	}





	public void addCity(String cityName)
	{
		cityDaoImpl.addCity(cityName);
	}
}
