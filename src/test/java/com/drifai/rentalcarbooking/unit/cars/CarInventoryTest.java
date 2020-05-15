package com.drifai.rentalcarbooking.unit.cars;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.Car.CARSIZE;
import com.drifai.rentalcarbooking.cars.Car.CARTYPE;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.Location;

/**
 * @author Dorothy Rifai
 * 
 */
public class CarInventoryTest {
	
	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.CarInventory#CarInventory()} .
	 */
	@Test
	public void testCarInventory() {
		final CarInventory inventory = new CarInventory();
		
			Assert.assertEquals(false, inventory.isLocationPopulated());
			Assert.assertNull(inventory.getLocation());
	
	}

	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.cars.CarInventory#CarInventory(com.drifai.rentalcarbooking.cars.Location)}
	 * .
	 */
	@Test
	public void testCarInventoryWithLocation() {
		final CarInventory inventory = new CarInventory(new Location("Boston", "MA"));
		populateCarInventory(inventory);
	
			Assert.assertEquals(true, inventory.isLocationPopulated());
			Assert.assertNotNull(inventory.getLocation());

		Assert.assertEquals(3, inventory.getNumberOfCars());
	}

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.CarInventory#numberOfCars()} .
	 */
	@Test
	public void testCarInventorySize() {
		CarInventory inventory = new CarInventory();
		populateCarInventory(inventory);
		Assert.assertEquals(3, inventory.getNumberOfCars());
		Assert.assertEquals(2,  inventory.getNumberOfCars(CARSIZE.MIDSIZE));
		Assert.assertEquals(1,  inventory.getNumberOfCars(CARTYPE.VAN));
	}	

	
	private void populateCarInventory(CarInventory inventory) {
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
	
}

