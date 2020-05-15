package com.drifai.rentalcarbooking.integration;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.CompanyInventory;
import com.drifai.rentalcarbooking.cars.Location;
import com.drifai.rentalcarbooking.service.InventoryService;
import com.drifai.rentalcarbooking.service.InventoryServiceImpl;

/**
 * @author Dorothy Rifai
 * 
 * Integration tests for the InventoryService.
 * 
 */
public class InventoryServiceTest {
	@Before
	public void setup() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
	}
	
	@After
	public void teardown() {
		System.out.println("This is where the InventoryServiceTest teardown code would run.");
	}
	
	@Test
	public void testInventoryProcessing() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
		
		InventoryService inventoryService = new InventoryServiceImpl();
		Location location = new Location("Boston", "MA");
		Car.CARTYPE type = Car.CARTYPE.SEDAN;
		Date dropOffDate = new Date("06/15/2020");
		Date pickUpDate = new Date("06/26/2020");
		int carsBeforeSelectedCarRemoved = companyInventory.getNumberOfCars();
		CarInventory locationCarInventory = companyInventory.getLocation(location);
		int locationCarsBeforeSelectedCarRemoved = locationCarInventory.getNumberOfCars();
		
		// Are cars available ?
		CarHistory[] availableCars = inventoryService.getInventory(location, type, dropOffDate, pickUpDate);
		
		if (availableCars.length == 0) {
			System.out.println("No cars are available for customer's requested search parameters.");
		} else {
			int counter = 0;
			for(CarHistory c : availableCars) {
				System.out.println(++counter + " " + c.toString());
			}
			CarHistory selectedCar = availableCars[0];
			inventoryService.removeCarFromInventory(location, selectedCar);
			int carsAfterSelectedCarRemoved = companyInventory.getNumberOfCars();
			int locationCarsAfterSelectedCarRemoved = locationCarInventory.getNumberOfCars();
			Assert.assertEquals((carsBeforeSelectedCarRemoved - 1), carsAfterSelectedCarRemoved);
			Assert.assertEquals((locationCarsBeforeSelectedCarRemoved - 1), locationCarsAfterSelectedCarRemoved);
		}
	}	
	
	@Test
	public void testInventoryProcessingNoAvailableCarForCarType() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
		
		InventoryService inventoryService = new InventoryServiceImpl();
		Location location = new Location("Boston", "MA");
		Car.CARTYPE type = Car.CARTYPE.PICKUP_TRUCK;
		Date dropOffDate = new Date("06/15/2020");
		Date pickUpDate = new Date ("06/25/2020");
		int carsBeforeSelectedCarRemoved = companyInventory.getNumberOfCars();
		CarInventory locationCarInventory = companyInventory.getLocation(location);
		int locationCarsBeforeSelectedCarRemoved = locationCarInventory.getNumberOfCars();
		
		// Are cars available ?
		CarHistory[] availableCars = inventoryService.getInventory(location, type, dropOffDate, pickUpDate);
		if(availableCars.length  == 0) {;
			System.out.println("No cars are available for customer's requested search parameters.");
		}
		Assert.assertEquals(0, availableCars.length);
	}	
	
	@Test
	public void testInventoryProcessingWithBlackoutDates() {
		CompanyInventory companyInventory = CompanyInventory.getInstance();
		initializeInventory(companyInventory);
		
		InventoryService inventoryService = new InventoryServiceImpl();
		Location location = new Location("Boston", "MA");
		Car.CARTYPE type = Car.CARTYPE.SEDAN;
		Date dropOffDate = new Date("06/15/2020");
		Date pickUpDate = new Date ("06/25/2020");
		CarInventory locationCarInventory = companyInventory.getLocation(location);
		// Are cars available ?
		CarHistory[] availableCars = inventoryService.getInventory(location, type, dropOffDate, pickUpDate);
		int countBeforeAddingBlackoutPeriod = availableCars.length;
		availableCars[0].addBlackoutPeriod(dropOffDate, pickUpDate);
		try {
			locationCarInventory.updateCar(availableCars[0].getId(), availableCars[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error when updating car inventory");
			e.printStackTrace();
		}
		
		// Are cars available ?
		availableCars = inventoryService.getInventory(location, type, dropOffDate, pickUpDate);
		int countAfterAddingBlackoutPeriod = availableCars.length;
		Assert.assertEquals((countBeforeAddingBlackoutPeriod - 1), countAfterAddingBlackoutPeriod);
		
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

