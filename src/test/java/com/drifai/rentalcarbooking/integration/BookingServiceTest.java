package com.drifai.rentalcarbooking.integration;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.bookings.Trip;
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
 * Integration tests for the BookingService.
 * 
 */
public class BookingServiceTest {
	@Before
	public void setup() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
	}
	
	@After
	public void teardown() {
		System.out.println("This is where the BookingServiceTest teardown code would run.");
	}
	
	@Test
	public void testBookingProcess() {
		// Create Customer
		BookingService bookingService = new BookingServiceImpl();
		Date pickUpDate = new Date("07/15/2020");
		Date dropOffDate = new Date("07/26/2020");
		CarHistory selectedCar = makeCarSelection(pickUpDate, dropOffDate);
		Customer customer = bookingService.createCustomer("Bob", "White", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "bwhite@gmail.com", "S-12345", "11/14/1980");
		Booking booking = bookingService.makeReservation(customer, selectedCar, pickUpDate, dropOffDate);
		Assert.assertEquals(Booking.BookingStatus.PENDING, booking.getStatus());
	}
	
	@Test
	public void testMultipleBookingProcess() {
		// Create Customer
		BookingService bookingService = new BookingServiceImpl();
		Customer customer = bookingService.createCustomer("Bob", "White", "35 Bird Lane", "Waltham", "MA", "02451",
				"781-555-1212", "bwhite@gmail.com", "S-12345", "11/14/1980");
		Trip[] trips = new Trip[2];
		trips[0] = new Trip(new Date("07/15/2020"), new Date("07/26/2020"));
		trips[1] = new Trip(new Date("08/01/2020"), new Date("08/04/2020"));

		CarHistory selectedCar = makeCarSelection(trips[0].getStartDate(), trips[0].getEndDate());
		boolean available = selectedCar == null ? false : selectedCar.checkAvailability(trips);
		if (available) {
			Booking[] bookings = null;
			for (Trip trip : trips) {
				bookings = bookingService.makeReservations(customer, selectedCar, trips);
			}
			Assert.assertEquals(2, bookings.length);
			int indx = 0;
			for (Booking booking : bookings) {
				Assert.assertEquals(Booking.BookingStatus.PENDING, booking.getStatus());
				Assert.assertEquals(trips[indx].getStartDate(), booking.getPickUpDate());
				Assert.assertEquals(trips[indx].getEndDate(), booking.getDropOffDate());
				Assert.assertEquals(1, booking.getCar().getId().intValue());
				indx++;
			}
		}
		

	}
	
	
	
	@Test
	public void testCancelBooking() {
		// Create Customer
		BookingService bookingService = new BookingServiceImpl();
		Date pickUpDate = new Date("06/15/2020");
		Date dropOffDate = new Date("06/26/2020");
		CarHistory selectedCar = makeCarSelection(pickUpDate, dropOffDate);
		Customer customer = bookingService.createCustomer("Bob", "White", "35 Bird Lane", "Waltham", "MA", "02451",
				"781-555-1212", "bwhite@gmail.com", "S-12345", "11/14/1980");
		Booking booking = bookingService.makeReservation(customer, selectedCar, pickUpDate, dropOffDate);
		bookingService.cancelReservation(booking);
		Assert.assertEquals(Booking.BookingStatus.INACTIVE, booking.getStatus());
	}
	
	private CarHistory makeCarSelection(Date pickUpDate, Date dropOffDate) {
		InventoryService inventoryService = new InventoryServiceImpl();
		Location location = new Location("Boston", "MA");
		CarInventory carInventory = CompanyInventory.getInstance().getLocation(location);
		Car.CARTYPE type = Car.CARTYPE.SEDAN;
		// Are cars available ?
		CarHistory[] availableCars = inventoryService.getInventory(location, type, dropOffDate, pickUpDate);
		return(availableCars[0]);
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
		CarHistory[] cars = new CarHistory[3];
		cars[0] = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		cars[1] = new CarHistory(2, Car.CARSIZE.MIDSIZE, Car.CARTYPE.VAN, 7);
		cars[2] = new CarHistory(3, Car.CARSIZE.FULLSIZE, Car.CARTYPE.SEDAN, 6);
		
		int id = 0;
		try {
			for(CarHistory car: cars) {
				id = car.getId();
				inventory.addCar(car.getId(), car);
			}
		}catch(Exception ex) {
			System.out.println("Exception occurred with car id: " + id);
			System.out.print(ex.getStackTrace());
		}
	}
	
	private void populateCarInventoryForProvidence(CarInventory inventory) {
		CarHistory[] cars = new CarHistory[4];
		cars[0] = new CarHistory(4, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		cars[1] = new CarHistory(5, Car.CARSIZE.MIDSIZE, Car.CARTYPE.VAN, 7);
		cars[2] = new CarHistory(6, Car.CARSIZE.FULLSIZE, Car.CARTYPE.PICKUP_TRUCK, 3);
		cars[3] = new CarHistory(7, Car.CARSIZE.ECONOMY, Car.CARTYPE.SEDAN, 4);
		
		
		int id = 0;
		try {
			for(CarHistory car: cars) {
				id = car.getId();
				inventory.addCar(car.getId(), car);
			}
		}catch(Exception ex) {
			System.out.println("Exception occurred with car id: " + id);
			System.out.print(ex.getStackTrace());
		}
	}
}

