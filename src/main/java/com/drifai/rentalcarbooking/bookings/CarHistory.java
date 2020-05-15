package com.drifai.rentalcarbooking.bookings;

import java.util.ArrayList;
import java.util.List;

import com.drifai.rentalcarbooking.cars.Car;

public class CarHistory extends Car {
	List<BlackoutPeriod> blackoutDates = new ArrayList<>();
	
	public void addBlackoutPeriod(BlackoutPeriod bd) {
		blackoutDates.add(bd);
	}
}
