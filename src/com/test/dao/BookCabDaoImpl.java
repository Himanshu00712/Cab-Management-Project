package com.test.dao;

import java.util.List;

import com.test.model.Cab;
import com.test.model.City;

public class BookCabDaoImpl implements BookCabDao {

	@Override
	public Cab bookCab(List<Cab> cabs, City fromLocation, City toLocation) {
		
		if (cabs.size() == 0) {
			System.out.println("Cab not found");	
		}
		return cabs.stream()
				.min((c1, c2) -> c1.getLastTripFinishedAt().isBefore(c2.getLastTripFinishedAt()) == true ? -1 : 1)
				.get();
		
	}

}
