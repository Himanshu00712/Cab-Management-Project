package com.test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.test.dao.BookCabDaoImpl;
import com.test.dao.CabsDao;
import com.test.dao.CabsDaoImpl;
import com.test.dao.CityDao;
import com.test.dao.CityDaoImpl;
import com.test.dao.TripsDao;
import com.test.dao.TripsDaoImpl;
import com.test.model.Cab;
import com.test.model.Trip;
import com.test.service.BookCabService;
import com.test.service.CabService;
import com.test.service.CityService;
import com.test.service.TripService;

public class Main {

	public static void main(String [] args) throws InterruptedException
	{
		
		CabsDao cabsDao = new CabsDaoImpl();
		CityDao cityDao = new CityDaoImpl();
		TripsDao tripsDao = new TripsDaoImpl();
		
		CabService cabService = new CabService(cabsDao);
		CityService cityService = new CityService(cityDao);
		BookCabService bookCabService = new BookCabService(new BookCabDaoImpl(), cabsDao, cityDao, tripsDao);
		TripService tripService = new TripService(tripsDao);
		
		cityService.addCity("ALD");
		cityService.addCity("LKO");
		cityService.addCity("NDLS");
		cityService.addCity("MZP");
		
		cabService.registerCab("UP70-T-2345");
		cabService.registerCab("UP72-Y-7865");
		cabService.registerCab("DL21-A-2654");
		cabService.registerCab("UP70-Z-8876");
		cabService.registerCab("UP70-M-8709");
		
		cabService.addCabLocation(cityDao.getCity("LKO"), "UP72-Y-7865");
		cabService.addCabLocation(cityDao.getCity("MZP"), "UP70-T-2345");
		cabService.addCabLocation(cityDao.getCity("NDLS"), "DL21-A-2654");
		cabService.addCabLocation(cityDao.getCity("LKO"), "UP70-Z-8876");
		cabService.addCabLocation(cityDao.getCity("ALD"), "UP70-M-8709");		
		
		Trip bookTrip1 = bookCabService.book(cityDao.getCity("LKO"), cityDao.getCity("ALD"));
		System.out.println("Cab Booked for LKO->ALD : " +bookTrip1.getCab().getNumber());
		
		Trip bookTrip2 = bookCabService.book(cityDao.getCity("ALD"), cityDao.getCity("LKO"));
		System.out.println("Cab Booked for ALD->LKO : " +bookTrip2.getCab().getNumber());
		
		
		
		tripService.endTrip(bookTrip1.getId());
		Thread.sleep(2000);
		tripService.endTrip(bookTrip2.getId());
		
		Trip bookTrip3 = bookCabService.book(cityDao.getCity("NDLS"), cityDao.getCity("LKO"));
		System.out.println("Cab Booked for NDLS->LKO : " +bookTrip3.getCab().getNumber());
		
		tripService.endTrip(bookTrip3.getId());
		
		Trip bookTrip4 = bookCabService.book(cityDao.getCity("MZP"), cityDao.getCity("NDLS"));
		System.out.println("Cab Booked for MZP->NDLS : " +bookTrip4.getCab().getNumber());
		
		tripService.endTrip(bookTrip4.getId());
		
		Trip bookTrip5 = bookCabService.book(cityDao.getCity("NDLS"), cityDao.getCity("ALD"));
		System.out.println("Cab Booked for NDLS->ALD : " +bookTrip5.getCab().getNumber());
		
		
		System.out.println("Cab History for UP70-T-2345 : "+tripService.getCabHistory("UP70-T-2345"));
		
		LocalDateTime fromIntervalTime = LocalDateTime.now().minusHours(1);
		LocalDateTime toIntervalTime = LocalDateTime.now();
		
		System.out.print("Idle Time for Cab UP72-Y-7865 : ");
		long ms = tripService.getCabIdleTime("UP72-Y-7865", fromIntervalTime, toIntervalTime);
		long min = TimeUnit.MILLISECONDS.toMinutes(ms);
		long sec = TimeUnit.MILLISECONDS.toSeconds(ms)%60;
		System.out.println(min+ " minutes and "+ sec +" seconds");
		System.out.println("Highest Demand in last 1 hr : "+ tripService.getCityWithHighestDemand(fromIntervalTime, toIntervalTime));
		
	}
	
}
