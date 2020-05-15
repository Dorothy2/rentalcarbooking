package com.drifai.rentalcarbooking.unit.cars;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.Car.CARSIZE;
import com.drifai.rentalcarbooking.cars.Car.CARTYPE;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.CompanyInventory;
import com.drifai.rentalcarbooking.cars.Location;

/**
 * @author Dorothy Rifai
 * 
 */
public class CompanyInventoryTest {
	
	@Before
	public void setup() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		CarInventory inventory = new CarInventory(new Location("Boston", "MA"));
		populateCarInventory(inventory);
		companyInventory.addLocation(inventory);
		
		CarInventory inventory2 = new CarInventory(new Location("Providence", "RI"));
		populateCarInventory(inventory2);
		companyInventory.addLocation(inventory2);
	}
	
	@After
	public void teardown() {
		
	}
	
	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.CompanyInventory#getNumberOfCars()}. 
	 */
	@Test
	public void testCarInventoryNumberOfCars() {
		Assert.assertEquals(6, CompanyInventory.getInstance().getNumberOfCars());
	}	
	
	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.cars.CompanyInventory#getLocations()}. 
	 */
	@Test
	public void testCompanyInventoryLocations() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		Assert.assertEquals(2, companyInventory.getNumberOfLocations());
		Set<String> expectedSet = new HashSet();
		expectedSet.add("Boston MA");
		expectedSet.add("Providence RI");
		Assert.assertEquals(expectedSet.toString(),
				companyInventory.getLocations().toString());					
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

