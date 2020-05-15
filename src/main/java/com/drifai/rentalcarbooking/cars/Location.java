package com.drifai.rentalcarbooking.cars;

import java.util.ArrayList;
import java.util.List;

public class Location {
	private Integer id;
	private String city;
	private String state;
	private List<String> applicableZipCodes;
	//private CarInventory inventory;
	
	
	public Location() {
		super();
		applicableZipCodes = new ArrayList<String>();
	}
	
	public Location(String city, String state) {
		this();
		this.city = city;
		this.state = state;
	}
	
	public void addZipCode(String zip) {
		applicableZipCodes.add(zip);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getApplicableZipCodes() {
		return applicableZipCodes;
	}

	public void setApplicableZipCodes(List<String> applicableZipCodes) {
		this.applicableZipCodes = applicableZipCodes;
	}

//	public CarInventory getInventory() {
//		return inventory;
//	}
//
//	public void setInventory(CarInventory inventory) {
//		this.inventory = inventory;
//	}

}
