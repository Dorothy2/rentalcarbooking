package com.drifai.rentalcarbooking.cars;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.drifai.rentalcarbooking.bookings.CarHistory;

/*
 * @author Dorothy Rifai
 * 
 * Singleton pattern
 *
 */

public class CompanyInventory {
	private Map<String, CarInventory> companyInventory;
	
	// create object of CompanyInventory
	private static CompanyInventory instance = new CompanyInventory();
	
	private CompanyInventory() {
		companyInventory = new HashMap<>();
	}
	
	//synchronized method to control simultaneous access 
	  synchronized public static CompanyInventory getInstance()  
	  { 
	    if (instance == null)  
	    { 
	      // if instance is null, initialize 
	      instance = new CompanyInventory(); 
	    } 
	    return instance; 
	  } 

	
	synchronized public boolean addLocation(CarInventory inventory) {
		boolean returnCode = false;
		if(inventory.isLocationPopulated()) {
			String key = generateKey(inventory.getLocation());
			CompanyInventory.getInstance().getCompanyInventory().put(key, inventory);
			returnCode = true;
		}
		return returnCode;
	}
	
	public boolean updateLocation(CarHistory car) {
		for(String locationKey : companyInventory.keySet()) {
			String[] s = locationKey.split(" ");
			Location location = new Location(s[0], s[1]);
			CarInventory localInventory = getLocation(location);
			if(localInventory.getMap().containsKey(car.getId())) {
				try {
					localInventory.updateCar(car.getId(), car);
				} catch (Exception e) {
					System.out.println("Error in CompanyInventory.updateLocation()");
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				return true;
			}            
		}
		return false;
	}
	
	private Map<String, CarInventory> getCompanyInventory() {
		return CompanyInventory.getInstance().companyInventory;
	}
	
	public int getNumberOfLocations() {
		return companyInventory.size();
	}
	
	public Set<String> getLocations() {
		return companyInventory.keySet();
	}
	
	public CarInventory getLocation(Location location) {
		String key = generateKey(location);
		CarInventory carInventory = CompanyInventory.getInstance().getCompanyInventory().get(key);
		return carInventory;
	}
	
	public int getNumberOfCars() {
		int counter = 0;
		for(String key : companyInventory.keySet()) {
			CarInventory inventory = companyInventory.get(key);
			counter += inventory.getNumberOfCars();
		}
		return counter;
	}
	
	private String generateKey(Location location) {
		String key = String.format("%s %s", location.getCity(), location.getState().toUpperCase());
		return key;
	}
	
	

}
