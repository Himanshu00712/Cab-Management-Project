package com.test.service;

import java.util.List;

import com.test.dao.BookCabDao;
import com.test.dao.CabsDao;
import com.test.dao.CityDao;
import com.test.dao.TripsDao;
import com.test.model.Cab;
import com.test.model.City;
import com.test.model.Trip;

public class BookCabService {

	private final BookCabDao bookCabDaoImpl;
	private final CabsDao cabsDaoImpl;
	private final CityDao cityDaoImpl;
	private final TripsDao tripsDaoImpl;
	
	

	public BookCabService(BookCabDao bookCabDaoImpl, CabsDao cabsDaoImpl, CityDao cityDaoImpl, TripsDao tripsDaoImpl) {
		super();
		this.bookCabDaoImpl = bookCabDaoImpl;
		this.cabsDaoImpl = cabsDaoImpl;
		this.cityDaoImpl = cityDaoImpl;
		this.tripsDaoImpl = tripsDaoImpl;
	}



	public Trip book(City fromLocation, City toLocation)
	{
		List<Cab> cabs = cabsDaoImpl.getAvailableCabsByCity(fromLocation.getName());
		Cab cab = bookCabDaoImpl.bookCab(cabs, fromLocation, toLocation);
		return tripsDaoImpl.createTrip(cab, fromLocation, toLocation);
		
	}
	
}
