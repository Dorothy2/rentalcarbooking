package com.drifai.rentalcarbooking.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.drifai.rentalcarbooking.bookings.BlackoutPeriod;
import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.cars.Car.CARTYPE;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.CompanyInventory;
import com.drifai.rentalcarbooking.cars.Location;
import com.drifai.rentalcarbooking.utilities.TimeUtility;


public class InventoryServiceImpl implements InventoryService {
	
	// Needs null constructor for future Spring autowiring
	public InventoryServiceImpl() {
		
	}
	
	@Override
	public CarHistory getSingleCarSelection(Location location, CARTYPE type, Date dropOffDate, Date pickUpDate) {
		// Are cars available ?
		CarHistory[] availableCars = getInventory(location, type, dropOffDate, pickUpDate);
		if (availableCars.length == 0) {
			return null;
		}
		return availableCars[0];
	}

	@Override
	public CarHistory[] getInventory(Location location, CARTYPE type, Date dropOffDate, Date pickUpDate) {
		CarHistory[] noCarsAvailable = new CarHistory[0];
		CarInventory carInventory = CompanyInventory.getInstance().getLocation(location);
		int available = carInventory.getNumberOfCars(type);
		if(available == 0) {
			return noCarsAvailable;
		}
		//return carInventory.getCars(type);
		List<CarHistory> filteredList = carInventory.getCars(type);
		Iterator<CarHistory> it = filteredList.iterator();
		while(it.hasNext()) {
			CarHistory car = it.next();
			// Remove cars from the list if there is a conflict with previous rentals
			if(! verifyNoBlackoutDates(car, dropOffDate, pickUpDate)) {
				it.remove();
			}
		}
		return filteredList.toArray(new CarHistory[filteredList.size()]);
		
	}

	@Override
	public boolean removeCarFromInventory(Location location, CarHistory car) {
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
	public boolean verifyNoBlackoutDates(CarHistory car, Date startDate, Date endDate) {
		boolean returnCode = true;
		TimeUtility t = new TimeUtility();
		List<BlackoutPeriod> blackoutDates = car.getBlackoutDates();
		for(BlackoutPeriod blackoutPeriod : blackoutDates) {
			if(t.overlap(blackoutPeriod.getStartDate(), blackoutPeriod.getEndDate(), startDate, endDate)) {
				returnCode = false;
				break;
			}
		}
		return returnCode;
	}

}
