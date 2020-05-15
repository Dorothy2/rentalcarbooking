package com.drifai.rentalcarbooking.cars;

public class Car {
	private Integer id;
	private CARSIZE size;
	private CARTYPE type;
	private int numberOfPassengers;
	
	public enum CARSIZE {
	    ECONOMY, 
	    COMPACT, 
	    MIDSIZE, 
	    FULLSIZE;
	}
	
	public enum CARTYPE {
	    SEDAN, 
	    SUV, 
	    VAN, 
	    PICKUP_TRUCK;
	}
	
	public Car() {
		super();
	}
	
	public Car(int id, CARSIZE size, CARTYPE type, int numberOfPassengers) {
		this();
		this.id = id;
		this.size = size;
		this.type = type;
		this.numberOfPassengers = numberOfPassengers;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = new Integer(id);
	}

	public CARSIZE getSize() {
		return size;
	}

	public void setSize(CARSIZE size) {
		this.size = size;
	}

	public CARTYPE getType() {
		return type;
	}

	public void setType(CARTYPE type) {
		this.type = type;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", size=" + size + ", type=" + type + ", numberOfPassengers=" + numberOfPassengers
				+ "]";
	}
	
	
}
