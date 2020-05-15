package com.drifai.rentalcarbooking.cars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drifai.rentalcarbooking.bookings.CarHistory;

public class CarInventory {
	private Map<Integer, CarHistory> carMap;
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
	
	public CarHistory getCar(Integer id) {
		return carMap.get(id);
	}
	
	public void addCar(Integer id, CarHistory car ) throws Exception {
		if(id == null) {
			throw new Exception("addCar: Car identifier value is null.");
		} else if(car == null) {
			throw new Exception("addCar: Car object is null.");
		}
			
		carMap.put(id, car);
	}
	
	public void removeCar(Integer id, CarHistory car ) throws Exception {
		if(id == null) {
			throw new Exception("removeCar: Car identifier value is null.");
		}  else if(! carMap.containsKey(id)) {
			throw new Exception("removeCar: Car object in map is null.");
		}
		carMap.remove(id);
	}
	
	public void updateCar(Integer id, CarHistory car ) throws Exception {
		if(id == null) {
			throw new Exception("addCar: Car identifier value is null.");
		} else if(car == null) {
			throw new Exception("addCar: Car object is null.");
		}
			
		carMap.put(id, car);
	}
	
	public int getNumberOfCars() {
		return carMap.size();
	}
	
	public List<CarHistory> getCars(Car.CARTYPE type) {
		List<CarHistory> filteredList = new ArrayList<>();
		for(CarHistory c : carMap.values()) {
			if(type.equals(c.getType())) {
				filteredList.add(c);
			}
		}
		return(filteredList);
		//return temp.toArray(new CarHistory[temp.size()]);
	}
	
	public CarHistory[] getCars(Car.CARSIZE size) {
		List<CarHistory> temp = new ArrayList<>();
		for(CarHistory c : carMap.values()) {
			if(size.equals(c.getSize())) {
				temp.add(c);
			}
		}
		return temp.toArray(new CarHistory[temp.size()]);
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
	
	public Map<Integer, CarHistory> getMap() {
		return carMap;
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
