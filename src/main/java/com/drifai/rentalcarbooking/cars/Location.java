package com.drifai.rentalcarbooking.cars;

import java.util.ArrayList;
import java.util.List;
/***
 * 
 * @author Dorothy Rifai
 * 
 * Stores location info for each rental center. 
 * This information is provided to the CarInventory object. There is one CarInventory reference
 * for each location.
 *
 */
public class Location {
	private Integer id;
	private String city;
	private String state;

	
	
	public Location() {
		super();
	}
	
	public Location(String city, String state) {
		this();
		this.city = city;
		this.state = state;
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
}
