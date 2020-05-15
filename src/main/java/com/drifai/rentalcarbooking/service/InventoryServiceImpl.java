package com.drifai.rentalcarbooking.service;

import java.util.Date;

import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.Car.CARTYPE;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.CompanyInventory;
import com.drifai.rentalcarbooking.cars.Location;

public class InventoryServiceImpl implements InventoryService {
	
	// Needs null constructor for future Spring autowiring
	public InventoryServiceImpl() {
		
	}

	@Override
	public Car[] getInventory(Location location, CARTYPE type, Date dropOffDate, Date pickUpDate) {
		Car[] noCarsAvailable = new Car[0];
		CarInventory carInventory = CompanyInventory.getInstance().getLocation(location);
		int available = carInventory.getNumberOfCars(type);
		if(available == 0) {
			return noCarsAvailable;
		}
		return carInventory.getCars(type);
		
	}

	@Override
	public boolean removeCarFromInventory(Location location, Car car) {
		boolean returnCode = false;
		CarInventory carInventory = CompanyInventory.getInstance().getLocation(location);
		try {
			carInventory.removeCar(car.getId(), car);
			System.out.println(String.format("Car [%d] removed from inventory.", car.getId()));
			returnCode = true;
		}catch(Exception ex) {
			ex.getStackTrace();
		}
		return returnCode;
	}

	@Override
	public boolean checkBlackoutDates(CarHistory car, Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return false;
	}

}
