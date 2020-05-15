package com.drifai.rentalcarbooking.cars;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * @author Dorothy Rifai
 * 
 * Singleton pattern
 *
 */

public class OldCompanyInventory {
	private Map<String, CarInventory> companyInventory;
	
	// create object of CompanyInventory
	private static OldCompanyInventory instance = new OldCompanyInventory();
	
	private OldCompanyInventory() {
		companyInventory = new HashMap<>();
	}
	
	// synchronized method to control simultaneous access
	public static OldCompanyInventory getInstance() {
		if (instance == null) {
			synchronized (OldCompanyInventory.class) {
				// if instance is null, initialize
				if (instance == null) {
					instance = new OldCompanyInventory();
				}
			}
		}
		return instance;
	}

	
	//synchronized public boolean addLocation(CarInventory inventory) {
	  public boolean addLocation(CarInventory inventory) {
		boolean returnCode = false;
		if(inventory.isLocationPopulated()) {
			String key = generateKey(inventory.getLocation());
			OldCompanyInventory.getInstance().getCompanyInventory().put(key, inventory);
			returnCode = true;
		}
		return returnCode;
	}
	
	private Map<String, CarInventory> getCompanyInventory() {
		return OldCompanyInventory.getInstance().companyInventory;
	}
	
	public int getNumberOfLocations() {
		return companyInventory.size();
	}
	
	public Set<String> getLocations() {
		return companyInventory.keySet();
	}
	
	public CarInventory getLocation(Location location) {
		String key = generateKey(location);
		CarInventory carInventory = OldCompanyInventory.getInstance().getCompanyInventory().get(key);
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
