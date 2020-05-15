package com.drifai.rentalcarbooking.service;

import java.util.Date;

import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.Car.CARTYPE;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.Location;

/***
 * 
 * This is the interface for updating the car inventory. Per requirement "1.", a collection of Car records can
 * be selected based on the given type of car (Sedan, SUV, Van, Pickup Truck).
 * 
 * @author Dorothy Rifai
 *
 */
public interface InventoryService {
	
	CarHistory[] getInventory(Location location, Car.CARTYPE type, Date dropOffDate, Date pickUpDate);
	
	boolean removeCarFromInventory(Location location, CarHistory car);

	boolean verifyNoBlackoutDates(CarHistory car, Date startDate, Date endDate);

	CarHistory getSingleCarSelection(Location location, CARTYPE type, Date dropOffDate, Date pickUpDate);
}
