package com.test.service;

import com.test.dao.CabsDao;
import com.test.dao.CabsDaoImpl;
import com.test.model.Cab;
import com.test.model.City;

public class CabService {

	private final CabsDao cabsDaoImpl;


	public CabService(CabsDao cabsDaoImpl) {
		super();
		this.cabsDaoImpl = cabsDaoImpl;
	}

	public void registerCab(String cabNumber) {
		cabsDaoImpl.registerCab(cabNumber);
	}

	public void addCabLocation(City city, String cabNumber) {
		cabsDaoImpl.addCabtoCity(city, cabNumber);
	}

	public void changeCabStatus(String cabNumber, Cab.Status status) {
		cabsDaoImpl.changeCabStatus(cabNumber, status);
	}

}
