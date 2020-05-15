package com.drifai.rentalcarbooking.service;

import java.util.Date;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;

public interface BookingService {

	
	
	Customer createCustomer(final String firstName, final String lastName,
			final String address1, final String city, final String state, 
			final String zipCode, final String phone, final String email,
			final String driversLicenseId, final String strDateOfBirth);
	
	Booking makeReservation(Customer customer, Car car, Date pickUpDate, Date dropOffDate);
	
	boolean cancelReservation(Booking booking);
	
	
}
