package com.drifai.rentalcarbooking.bookings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.utilities.TimeUtility;

/**
 * CarHistory extends the Car functionality to track dates where the car has already been booked.
 * 
 * @author Dorothy Rifai
 *
 */
public class CarHistory extends Car {
	List<BlackoutPeriod> blackoutDates = new ArrayList<>();
	
	public CarHistory() {
		super();
	}
	
	/**
	 * CarHistory constructor
	 * 
	 * @param id int
	 * @param size Car.CARESIZE
	 * @param type Car.CARTYPE type
	 * @param numberOfPassengers int
	 * 
	 */
	public CarHistory(int id, CARSIZE size, CARTYPE type, int numberOfPassengers) {
		super(id, size, type, numberOfPassengers);
	}
	
	public void addBlackoutPeriod(Date pickUpDate, Date dropOffDate) {
		BlackoutPeriod bp = new BlackoutPeriod(pickUpDate, dropOffDate);
		blackoutDates.add(bp);
	}
	
	public List<BlackoutPeriod> getBlackoutDates() {
		return blackoutDates;
	}
	
	/**
	 * Checks the availability of a single car for a collection of trips. New blackout periods are calculated
	 * for each trip. At the end of the 
	 * method the car's blackoutPeriods are reset.
	 * @param trips  Trips with start and end dates
	 * @return boolean
	 */
	public boolean checkAvailability(Trip[] trips) {
		boolean available = true;
		TimeUtility t = new TimeUtility();
		// Save a copy of current contents
		List<BlackoutPeriod> blackoutDatesSnapshot = new ArrayList<>();
		blackoutDatesSnapshot.addAll(blackoutDates);
		for(Trip trip : trips) {
			if(blackoutDates.size() != 0) {
		
				for(BlackoutPeriod blackoutDate : blackoutDates) {
					if(t.overlap(trip.getStartDate(), trip.getEndDate(), blackoutDate.getStartDate(),
							blackoutDate.getEndDate())) {
						available = false;
						return available;
					}					
				}
			}	
			blackoutDates.add(new BlackoutPeriod(trip.getStartDate(), trip.getEndDate()));
		}
		// Replace with previous contents
		blackoutDates = blackoutDatesSnapshot;
		return available;
	}
}
