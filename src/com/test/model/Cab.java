package com.test.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cab {

	private final String id;
	private String number;
	private City currentLocation;
	private LocalDateTime lastTripFinishedAt;

	public enum Status {
		IDLE, ON_TRIP
	}

	Status status;

	public Cab(String number, City location) {

		this.id = UUID.randomUUID().toString();
		this.number = number;
		this.currentLocation = location;
		this.status = Status.IDLE;
		this.lastTripFinishedAt = LocalDateTime.now();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public City getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(City currentLocation) {
		this.currentLocation = currentLocation;
	}

	public LocalDateTime getLastTripFinishedAt() {
		return lastTripFinishedAt;
	}

	public void setLastTripFinishedAt(LocalDateTime lastTripFinishedAt) {
		this.lastTripFinishedAt = lastTripFinishedAt;
	}

	public String getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
