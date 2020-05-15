package com.drifai.rentalcarbooking.service;

import java.util.Date;

import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.Location;

public interface InventoryService {
	
	Car[] getInventory(Location location, Car.CARTYPE type, Date dropOffDate, Date pickUpDate);
	
	boolean removeCarFromInventory(Location location, Car car);
	
	boolean checkBlackoutDates(CarHistory car, Date startDate, Date endDate);
}
