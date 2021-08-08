package com.test.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import com.test.dao.TripsDao;
import com.test.model.City;
import com.test.model.Trip;

public class TripService {

	private final TripsDao tripsDaoImpl;

	public TripService(TripsDao tripsDaoImpl) {
		super();
		this.tripsDaoImpl = tripsDaoImpl;
	}

	public List<Trip> getCabHistory(String cabNumber) {
		return tripsDaoImpl.getTripsInfoByCab(cabNumber);
	}

	public City getCityWithHighestDemand(LocalDateTime fromTime, LocalDateTime toTime) {
		return tripsDaoImpl.getCityWithHighDemand(fromTime, toTime).entrySet().stream()
				.max((e1, e2) -> e1.getValue() > e2.getValue() ? 1 : -1).get().getKey();
	}

	public void endTrip(String tripId) {
		tripsDaoImpl.endTrip(tripId);
	}

	public Long getCabIdleTime(String cabNumber, LocalDateTime fromIntervalTime, LocalDateTime toIntervalTime) {
		List<Trip> onTrip = tripsDaoImpl.getTripInfoByCabInInterval(cabNumber, fromIntervalTime, toIntervalTime);

		Long tripTime = 0L;

		for (Trip t : onTrip) {
			LocalDateTime start;
			LocalDateTime end;

			if (t.getStartTime().isBefore(fromIntervalTime))
				start = fromIntervalTime;
			else
				start = t.getStartTime();

			end = toIntervalTime;
			tripTime += end.toInstant(ZoneOffset.UTC).toEpochMilli() - start.toInstant(ZoneOffset.UTC).toEpochMilli();

		}

		return toIntervalTime.toInstant(ZoneOffset.UTC).toEpochMilli()
				- fromIntervalTime.toInstant(ZoneOffset.UTC).toEpochMilli() - tripTime;

	}

}
