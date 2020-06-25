package com.drifai.rentalcarbooking.unit.bookings;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.Location;

/**
 * @author Dorothy Rifai
 * 
 */
public class BookingTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.bookings.Booking#Booking()} .
	 */
	@Test
	public void testBooking() {
		final Booking booking = new Booking();
		Assert.assertNull(booking.getPickUpDate());
		Assert.assertNull(booking.getDropOffDate());
		Assert.assertNull(booking.getCar());
	    Assert.assertNull(booking.getCustomer());
	}	

	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.Booking#Booking(java.util.Date,
	 *  java.util.Date, com.drifai.rentalcarbooking.cars.CarHistory, com.drifai.rentalcarbooking.bookings.Customer)}
	 * 
	 */
	@Test
	public void testBookingAllFields() {
		CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		Customer customer = new Customer("Bob", "White", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "bwhite@gmail.com", "S-12345", new Date("11/14/1980"), true);
		final Booking booking = new Booking(new Date("06/15/2020"), new Date("06/25/2020"), car, customer);
		Assert.assertEquals("06/15/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getPickUpDate()));
		Assert.assertEquals("06/25/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getDropOffDate()));
		Assert.assertEquals(Car.CARSIZE.MIDSIZE, booking.getCar().getSize());
		Assert.assertEquals(Car.CARTYPE.SEDAN, booking.getCar().getType());
	}
	
	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.Booking#Booking(java.lang.Integer, java.util.Date,
	 *  int, com.drifai.rentalcarbooking.cars.CarHistory, com.drifai.rentalcarbooking.bookings.Customer)}
	 * 
	 */
	@Test
	public void testBookingPickUpDatePlusDays() {
		CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		Customer customer = new Customer("Bob", "White", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "bwhite@gmail.com", "S-12345", new Date("11/14/1980"), true);
		final Booking booking = new Booking(new Date("06/15/2020"), 10, car, customer);
		Assert.assertEquals("06/15/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getPickUpDate()));
		Assert.assertEquals("06/25/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getDropOffDate()));
		Assert.assertEquals(true, booking.getCustomer().isMinimumAgeVerified());		
	}
	
	@Test
	public void testBookingStatusCalculation() {
		Date now = new Date();
		//System.out.println("Date: " + DRifaiConstants.DD_MM_YYYY_HH_MM_SS.format(now));
		CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		Customer customer = new Customer("Bob", "White", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "bwhite@gmail.com", "S-12345", new Date("11/14/1980"), true);
		Booking booking = new Booking(new Date("07/15/2020"), new Date("07/25/2020"), car, customer);
		
		booking.determineBookingStatus(now);
		Assert.assertEquals(BookingStatus.PENDING, booking.getStatus());
		
		now = new Date("07/16/2020");
		booking.determineBookingStatus(now);
		Assert.assertEquals(BookingStatus.ACTIVE, booking.getStatus());
		
		now = new Date("08/01/2020");
		booking.determineBookingStatus(now);
		Assert.assertEquals(BookingStatus.PAST, booking.getStatus());
	}
	
	/**
	 * If driver is under the age of 25, a Customer record is created with isMinimumAgeVerified set to false
	 * and the Booking is created, but then marked with CANCELLED status
	 */
	@Test
	public void testBookingUnderAgedDriver() {
		CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		Customer customer = new Customer("David", "Duckling", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "dduckling@gmail.com", "S-45678", new Date("11/14/2006"), false);
		final Booking booking = new Booking(new Date("07/15/2020"), 10, car, customer);
		Assert.assertEquals("07/15/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getPickUpDate()));
		Assert.assertEquals("07/25/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getDropOffDate()));
		Assert.assertEquals(false, booking.getCustomer().isMinimumAgeVerified());
		Assert.assertEquals(Booking.BookingStatus.CANCELLED, booking.getStatus());
	}
	
	private void populateCarInventory(CarInventory inventory) {
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


}

