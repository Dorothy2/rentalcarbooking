package com.drifai.rentalcarbooking.service;

import java.util.Date;

import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.Car.CARTYPE;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.Location;

public interface InventoryService {
	
	CarHistory[] getInventory(Location location, Car.CARTYPE type, Date dropOffDate, Date pickUpDate);
	
	boolean removeCarFromInventory(Location location, CarHistory car);

	boolean verifyNoBlackoutDates(CarHistory car, Date startDate, Date endDate);

	CarHistory getSingleCarSelection(Location location, CARTYPE type, Date dropOffDate, Date pickUpDate);
}
