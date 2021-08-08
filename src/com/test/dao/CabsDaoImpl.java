package com.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.test.model.Cab;
import com.test.model.Cab.Status;
import com.test.model.City;

public class CabsDaoImpl implements CabsDao {

	private final Map<String, Cab> newCab;

	public CabsDaoImpl() {
		this.newCab = new HashMap<>();
	}

	@Override
	public void registerCab(String cabNumber) {

		if (newCab.containsKey(cabNumber)) {
			System.out.println("Cab Already Exist!");
		}
		Cab cab = new Cab(cabNumber, null);
		newCab.put(cabNumber, cab);

	}

	@Override
	public List<Cab> getAvailableCabsByCity(String cityName) {
		return newCab
				.values().stream().filter(c -> c.getCurrentLocation() != null
						&& c.getCurrentLocation().getName().equals(cityName) && c.getStatus().equals(Cab.Status.IDLE))
				.collect(Collectors.toList());
	}

	@Override
	public void addCabtoCity(City city, String cabNumber) {
		if (!newCab.containsKey(cabNumber)) {
			System.out.println("Cab is not registered!");
		}
		newCab.get(cabNumber).setCurrentLocation(city);
	}

	@Override
	public void changeCabStatus(String cabNumber, Status status) {
		if (!newCab.containsKey(cabNumber)) {
			System.out.println("Cab is not registered!");
		}
		newCab.get(cabNumber).setStatus(status);
	}

}
