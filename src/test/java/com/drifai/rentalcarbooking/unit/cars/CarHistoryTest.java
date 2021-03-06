package com.drifai.rentalcarbooking.unit.cars;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.bookings.Trip;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;

/**
 * @author Dorothy Rifai
 * 
 */
public class CarHistoryTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.Car#Car()} .
	 */
	@Test
	public void testCar() {
		final CarHistory car = new CarHistory();
		Assert.assertNull(car.getId());
		Assert.assertNull(car.getSize());
		Assert.assertNull(car.getType());
		Assert.assertEquals(0, car.getNumberOfPassengers());
	}	

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.Car#Car(java.lang.Integer, Car.CARSIZE,
	 *  Car.CARTYPE, int)}
	 * 
	 */
	@Test
	public void testCarAllFields() {
		final CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		System.out.println("Car: " + car.toString());
		Assert.assertEquals(1, car.getId().intValue());
		Assert.assertEquals(Car.CARSIZE.MIDSIZE, car.getSize());
		Assert.assertEquals(Car.CARTYPE.SEDAN, car.getType());
		Assert.assertEquals(5, car.getNumberOfPassengers());
	}
	
	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.Car#checkAvailability(Trip[] trips)}
	 * 
	 */
	@Test
	public void testCheckAvailability() {
		final CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		Trip[] trips = new Trip[2];
		trips[0] = new Trip(new Date("06/15/2020"), new Date("06/26/2020"));
		trips[1] = new Trip(new Date("07/01/2020"), new Date("07/04/2020"));
   		Assert.assertEquals(true, car.checkAvailability(trips));
	}
	
	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.Car#checkAvailability(Trip[] trips)}
	 * 
	 */
	@Test
	public void testCheckAvailabilityWithOverlap() {
		final CarHistory car = new CarHistory(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		Trip[] trips = new Trip[2];
		trips[0] = new Trip(new Date("06/15/2020"), new Date("06/26/2020"));
		trips[1] = new Trip(new Date("06/17/2020"), new Date("06/28/2020"));
  		Assert.assertEquals(false, car.checkAvailability(trips));
	}
	
}

