package com.drifai.rentalcarbooking.cars;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drifai.rentalcarbooking.bookings.CarHistory;

/**
 * 
 * @author Dorothy Rifai
 * 
 * Wrapper around a map of Integer ids and CarHistory objects. There is one 
 * CarInventory object per Location.
 *
 */
public class CarInventory {
	private Map<Integer, CarHistory> carMap;
	private Location location;
	private boolean locationPopulated;
	
	
	public CarInventory() {
		super();
		carMap = new HashMap<>();
		locationPopulated = false;
	}
	
	/**
	 * 
	 * @param location Location
	 */
	public CarInventory(Location location) {
		this();
		if(location != null) {
			this.location = location;
			locationPopulated = true;
		}
		
	}
	
	/**
	 * 
	 * @param id Integer
	 * @return CarHistory
	 */
	public CarHistory getCar(Integer id) {
		return carMap.get(id);
	}
	
	/**
	 * 
	 * @param id Integer
	 * @param car CarHistory
	 * @throws Exception when id or car parameter is null
	 */
	public void addCar(Integer id, CarHistory car ) throws Exception {
		if(id == null) {
			throw new Exception("addCar: Car identifier value is null.");
		} else if(car == null) {
			throw new Exception("addCar: Car object is null.");
		}
			
		carMap.put(id, car);
	}
	
	/**
	 * 
	 * @param id Integer
	 * @param car CarHistory
	 * @throws Exception when id or car parameter is null
	 */
	public void removeCar(Integer id, CarHistory car ) throws Exception {
		if(id == null) {
			throw new Exception("removeCar: Car identifier value is null.");
		}  else if(! carMap.containsKey(id)) {
			throw new Exception("removeCar: Car object in map is null.");
		}
		carMap.remove(id);
	}
	/**
	 * 
	 * @param id int
	 * @param car CarHistory
	 * @throws Exception when id or car parameter is null
	 */
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
	
	/**
	 * 
	 * @param type Car.CARTYPE
	 * @return list of cars that match the type
	 */
	public List<CarHistory> getCars(Car.CARTYPE type) {
		List<CarHistory> filteredList = new ArrayList<>();
		for(CarHistory c : carMap.values()) {
			if(type.equals(c.getType())) {
				filteredList.add(c);
			}
		}
		return(filteredList);
	}
	
	/**
	 * 
	 * @param size Car.CARSIZE
	 * @return CarHistory[] of cars that match the size
	 */
	public CarHistory[] getCars(Car.CARSIZE size) {
		List<CarHistory> temp = new ArrayList<>();
		for(CarHistory c : carMap.values()) {
			if(size.equals(c.getSize())) {
				temp.add(c);
			}
		}
		return temp.toArray(new CarHistory[temp.size()]);
	}
	
    /**
     * 
     * @param size Car.CARSIZE
     * @return int
     */
	public int getNumberOfCars(Car.CARSIZE size) {
		int counter = 0;
		for(Car c : carMap.values()) {
			if(size.equals(c.getSize())) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * 
	 * @param type Car.CARTYPE
	 * @return int
	 */
	public int getNumberOfCars(Car.CARTYPE type) {
		int counter = 0;
		for(Car c : carMap.values()) {
			if(type.equals(c.getType())) {
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * 
	 * @param type Car.CARTYPE
	 * @param size Car.CARSIZE
	 * @return int
	 */
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
