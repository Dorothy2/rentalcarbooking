package com.drifai.rentalcarbooking.bookings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.utilities.TimeUtility;

public class CarHistory extends Car {
	List<BlackoutPeriod> blackoutDates = new ArrayList<>();
	
	public CarHistory() {
		super();
	}
	
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
