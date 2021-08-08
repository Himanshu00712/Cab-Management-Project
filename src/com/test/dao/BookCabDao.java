package com.test.dao;

import java.util.List;

import com.test.model.Cab;
import com.test.model.City;

public interface BookCabDao {

	Cab bookCab(List<Cab> cabs, City fromLocation, City toLocation);
	
}
