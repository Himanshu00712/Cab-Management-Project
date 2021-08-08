package com.test.dao;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.test.model.Cab;
import com.test.model.City;
import com.test.model.Trip;

public class TripsDaoImpl implements TripsDao {

	private final Map<String, Trip> newTrip;

	public TripsDaoImpl() {
		this.newTrip = new HashMap<>();
	}

	@Override
	public Trip createTrip(Cab cab, City fromLocation, City toLocation) {
		cab.setStatus(Cab.Status.ON_TRIP);
		Trip trip = new Trip(cab, fromLocation, toLocation);
		newTrip.put(trip.getId(), trip);
		return trip;
	}

	@Override
	public void endTrip(String tripId) {
		if (!newTrip.containsKey(tripId)) {
			System.out.println("Trip does not exist!");
		}
		Trip trip = newTrip.get(tripId);
		trip.endTrip();
	}

	@Override
	public Trip getTripInfo(String tripId) {
		if (!newTrip.containsKey(tripId)) {
			System.out.println("Trip does not exist!");
		}
		return newTrip.get(tripId);

	}

	@Override
	public List<Trip> getTripsInfoByCab(String cabNumber) {

		return newTrip.values().stream().filter(t -> t.getCab().getNumber().equals(cabNumber))
				.collect(Collectors.toList());
	}

	@Override
	public List<Trip> getTripInfoByCabInInterval(String cabNumber, LocalDateTime fromTime, LocalDateTime toTime) {
		return newTrip.values().stream()
				.filter(t -> t.getCab().getNumber().equals(cabNumber) && t.getStatus().equals(Trip.Status.COMPLETE)
						&& (t.getStartTime().isAfter(fromTime) || t.getEndTime().isBefore(toTime)))
				.collect(Collectors.toList());

	}

	@Override
	public Map<City, Long> getCityWithHighDemand(LocalDateTime fromTime, LocalDateTime toTime) {

		return newTrip.values().stream()
				.filter(t -> t.getStatus().equals(Trip.Status.COMPLETE) && t.getStartTime().isAfter(fromTime)
						&& t.getEndTime().isBefore(toTime))
				.collect(Collectors.groupingBy(t -> t.getFromLocation(), Collectors.counting()));

	}

}
