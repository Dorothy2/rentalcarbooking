package com.drifai.rentalcarbooking.system;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.CompanyInventory;
import com.drifai.rentalcarbooking.cars.Location;
import com.drifai.rentalcarbooking.service.BookingService;
import com.drifai.rentalcarbooking.service.BookingServiceImpl;
import com.drifai.rentalcarbooking.service.InventoryService;
import com.drifai.rentalcarbooking.service.InventoryServiceImpl;

/**
 * @author Dorothy Rifai
 * 
 */
public class SystemTest {
	@Before
	public void setup() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
	}
	
	@After
	public void teardown() {
		System.out.println("This is where the SystemTest teardown code would run.");
	}
	
	@Test
	public void testInventoryProcessing() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
		
		InventoryService inventoryService = new InventoryServiceImpl();
		Location location = new Location("Boston", "MA");
		Car.CARTYPE type = Car.CARTYPE.SEDAN;
		Date dropOffDate = new Date("05/15/2020");
		Date pickUpDate = new Date("05/26/2020");
		
		// Are cars available ?
		Car[] availableCars = inventoryService.getInventory(location, type, dropOffDate, pickUpDate);
		int counter = 0;
		for(Car c : availableCars) {
			System.out.println(++counter + " " + c.toString());
		}
		if(availableCars.length == 0) {
			System.out.println("No cars are available for customer's requested search parameters.");
		}
		Car selectedCar = availableCars[0];
		
		// Create Customer
		BookingService bookingService = new BookingServiceImpl();
		Customer customer = bookingService.createCustomer("Bob", "White", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "bwhite@gmail.com", "S-12345", "11/14/1980");
		Booking booking = bookingService.makeReservation(customer, selectedCar, pickUpDate, dropOffDate);
		
		if(booking != null && ! BookingStatus.CANCELLED.equals(booking.getStatus())) {
			inventoryService.removeCarFromInventory(location, selectedCar);
		}
		
		
		
		
		
	}	
	
	private void initializeInventory(CompanyInventory companyInventory) {
		CarInventory bostonInventory = new CarInventory(new Location("Boston", "MA"));
		populateCarInventoryForBoston(bostonInventory);		
		
		
		CarInventory providenceInventory = new CarInventory(new Location("Providence", "RI"));
		populateCarInventoryForProvidence(providenceInventory);
		companyInventory.addLocation(bostonInventory);
		companyInventory.addLocation(providenceInventory);	
		
	}

	private void populateCarInventoryForBoston(CarInventory inventory) {
		Car[] cars = new Car[3];
		cars[0] = new Car(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		cars[1] = new Car(2, Car.CARSIZE.MIDSIZE, Car.CARTYPE.VAN, 7);
		cars[2] = new Car(3, Car.CARSIZE.FULLSIZE, Car.CARTYPE.SEDAN, 6);
		
		int id = 0;
		try {
			for(Car car: cars) {
				id = car.getId();
				inventory.addCar(car.getId(), car);
			}
		}catch(Exception ex) {
			System.out.println("Exception occurred with car id: " + id);
			System.out.print(ex.getStackTrace());
		}
	}
	
	private void populateCarInventoryForProvidence(CarInventory inventory) {
		Car[] cars = new Car[4];
		cars[0] = new Car(4, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		cars[1] = new Car(5, Car.CARSIZE.MIDSIZE, Car.CARTYPE.VAN, 7);
		cars[2] = new Car(6, Car.CARSIZE.FULLSIZE, Car.CARTYPE.PICKUP_TRUCK, 3);
		cars[3] = new Car(7, Car.CARSIZE.ECONOMY, Car.CARTYPE.SEDAN, 4);
		
		
		int id = 0;
		try {
			for(Car car: cars) {
				id = car.getId();
				inventory.addCar(car.getId(), car);
			}
		}catch(Exception ex) {
			System.out.println("Exception occurred with car id: " + id);
			System.out.print(ex.getStackTrace());
		}
	}
}

