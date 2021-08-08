package com.test.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.test.model.Cab;
import com.test.model.City;
import com.test.model.Trip;

public interface TripsDao {

	Trip createTrip(Cab cab, City fromLocation, City toLocation);
	void endTrip(String tripId);
	Trip getTripInfo(String tripId);
	List<Trip> getTripsInfoByCab(String cabNumber);
	List<Trip> getTripInfoByCabInInterval(String cabNumber, LocalDateTime fromTime, LocalDateTime toTime);
	Map<City, Long> getCityWithHighDemand(LocalDateTime fromTime, LocalDateTime toTime);
	
	
}
