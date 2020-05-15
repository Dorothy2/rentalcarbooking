package com.drifai.rentalcarbooking.cars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarInventory {
	private Map<Integer, Car> carMap;
	private Location location;
	private boolean locationPopulated;
	
	
	public CarInventory() {
		super();
		carMap = new HashMap<>();
		locationPopulated = false;
	}
	
	public CarInventory(Location location) {
		this();
		if(location != null) {
			this.location = location;
			locationPopulated = true;
		}
		
	}
	
	public void addCar(Integer id, Car car ) throws Exception {
		if(id == null) {
			throw new Exception("addCar: Car identifier value is null.");
		} else if(car == null) {
			throw new Exception("addCar: Car object is null.");
		}
			
		carMap.put(id, car);
	}
	
	public void removeCar(Integer id, Car car ) throws Exception {
		if(id == null) {
			throw new Exception("removeCar: Car identifier value is null.");
		}  else if(! carMap.containsKey(id)) {
			throw new Exception("removeCar: Car object in map is null.");
		}
		carMap.remove(id);
	}
	
	public int getNumberOfCars() {
		return carMap.size();
	}
	
	public Car[] getCars(Car.CARTYPE type) {
		List<Car> temp = new ArrayList<>();
		for(Car c : carMap.values()) {
			if(type.equals(c.getType())) {
				temp.add(c);
			}
		}
		return temp.toArray(new Car[temp.size()]);
	}
	
	public Car[] getCars(Car.CARSIZE size) {
		List<Car> temp = new ArrayList<>();
		for(Car c : carMap.values()) {
			if(size.equals(c.getSize())) {
				temp.add(c);
			}
		}
		return temp.toArray(new Car[temp.size()]);
	}
	
	public int getNumberOfCars(Car.CARSIZE size) {
		int counter = 0;
		for(Car c : carMap.values()) {
			if(size.equals(c.getSize())) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getNumberOfCars(Car.CARTYPE type) {
		int counter = 0;
		for(Car c : carMap.values()) {
			if(type.equals(c.getType())) {
				counter++;
			}
		}
		return counter;
	}
	
	public int getNumberOfCars(Car.CARTYPE type, Car.CARSIZE size) {
		int counter = 0;
		for(Car c : carMap.values()) {
			if(type.equals(c.getType()) && size.equals(c.getSize())) {
				counter++;
			}
		}
		return counter;
	}

	public Location getLocation() {
		if(carMap == null || carMap.size() == 0 || this.location == null) {
			return null;
		}
		return location;
	}
	
	public boolean isLocationPopulated() {
		return location != null;
	}
}
