package com.drifai.rentalcarbooking.unit.cars;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;

/**
 * @author Dorothy Rifai
 * 
 */
public class CarTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.Car#Car()} .
	 */
	@Test
	public void testCar() {
		final Car car = new Car();
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
		final Car car = new Car(1, Car.CARSIZE.MIDSIZE, Car.CARTYPE.SEDAN, 5);
		System.out.println("Car: " + car.toString());
		Assert.assertEquals(1, car.getId().intValue());
		Assert.assertEquals(Car.CARSIZE.MIDSIZE, car.getSize());
		Assert.assertEquals(Car.CARTYPE.SEDAN, car.getType());
		Assert.assertEquals(5, car.getNumberOfPassengers());
	}
	
}

