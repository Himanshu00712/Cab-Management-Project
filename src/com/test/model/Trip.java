package com.test.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Trip {

	private final String id;
	private Cab cab;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private City fromLocation;
	private City toLocation;

	public enum Status {
		IN_PROGRESS, COMPLETE

	}

	private Status status;

	public Trip(Cab cab, City fromLocation, City toLocation) {
		this.id = UUID.randomUUID().toString();
		this.cab = cab;
		this.startTime = LocalDateTime.now();
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.status = Status.IN_PROGRESS;

	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public City getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(City fromLocation) {
		this.fromLocation = fromLocation;
	}

	public City getToLocation() {
		return toLocation;
	}

	public void setToLocation(City toLocation) {
		this.toLocation = toLocation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void endTrip() {
		this.cab.setStatus(Cab.Status.IDLE);
		this.cab.setLastTripFinishedAt(LocalDateTime.now());
		this.cab.setCurrentLocation(this.toLocation);
		this.status = Status.COMPLETE;
		this.endTime = LocalDateTime.now();

	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", cab=" + cab.getNumber() + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", fromLocation=" + fromLocation.getName() + ", toLocation=" + toLocation.getName() + ", status="
				+ status + "]";
	}

}
