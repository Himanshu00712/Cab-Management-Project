package com.test.dao;

import java.util.HashMap;
import java.util.Map;
import com.test.model.City;

public class CityDaoImpl implements CityDao {

	private final Map<String, City> newCity;

	public CityDaoImpl() {
		this.newCity = new HashMap<>();
	}

	@Override
	public void addCity(String cityName) {

		if(newCity.containsKey(cityName)) {
			System.out.println("City alreday present!");
		}

		City city = new City(cityName);
		newCity.put(cityName, city);

	}

	@Override
	public City getCity(String cityName) {
		if (!newCity.containsKey(cityName)) {
			System.out.println("City does not exist!");
		}
		return newCity.get(cityName);
		
		

	}

}
