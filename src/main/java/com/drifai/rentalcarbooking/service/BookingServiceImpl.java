package com.drifai.rentalcarbooking.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.bookings.CarHistory;
import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.bookings.Trip;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;

/***
 * This is the implementation of the BookingService interface. The BookingService
 * facilitates the placing and cancelling of car rental reservations. Per requirement "2." there is a
 * method for making reservations for multiple trips involving one customer and one car.
 * 
 * @author Dorothy Rifai
 *
 */
public class BookingServiceImpl implements BookingService {

	private static final int CUSTOMER_AGE_MINIMUM = 25;

	@Override
	public Customer createCustomer(String firstName, String lastName, String address1, String city, String state,
			String zipCode, String phone, String email, String driversLicenseId, String strDateOfBirth) {
		Customer customer = null;
		int customerAge = -1;
		boolean isMinimumAge = false;
		// Check whether driver is at least 25 years		
		Date dateOfBirth = null;
		try {
			dateOfBirth = DRifaiConstants.DD_MM_YYYY.parse(strDateOfBirth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in trying to parse date: " + strDateOfBirth); 
			e.printStackTrace();
		}
		isMinimumAge = verifyMinimumAge(dateOfBirth);
		
        customer = new Customer(firstName, lastName, address1, city, state, zipCode, 
			phone, email, driversLicenseId, dateOfBirth, isMinimumAge);

		return customer;
	}

	@Override
	public Booking makeReservation(Customer customer, CarHistory car, Date pickUpDate, Date dropOffDate) {
		final Booking booking = new Booking(pickUpDate, dropOffDate, car, customer);
		if(BookingStatus.PENDING.equals(booking.getStatus())) {
			car.addBlackoutPeriod(pickUpDate, dropOffDate);
		}
		return booking;
	}
	
	@Override
	public Booking[] makeReservations(Customer customer, CarHistory car, Trip[] trips) {
		List<Booking> bookingList = new ArrayList<>();
		for(Trip trip : trips) {
			Date pickUpDate = trip.getStartDate();
			Date dropOffDate = trip.getEndDate();
			final Booking booking = new Booking(pickUpDate, dropOffDate, car, customer);
			if(BookingStatus.PENDING.equals(booking.getStatus())) {
				car.addBlackoutPeriod(pickUpDate, dropOffDate);
			}
			bookingList.add(booking);
		}
		return bookingList.toArray(new Booking[bookingList.size()]);
	}
	
	private boolean verifyMinimumAge(Date dateOfBirth) {
		boolean returnCode = false;
		if (dateOfBirth == null) {
			return false;
		}

		Date now = new Date();
		// get customer's age
		int customerAge = now.getYear() - dateOfBirth.getYear();
		if (customerAge >= CUSTOMER_AGE_MINIMUM) {
			returnCode = true;
		}
		return returnCode;
	}

	/**
	 * Reservation cancelled by user
	 */
	@Override
	public boolean cancelReservation(Booking booking) {
		boolean returnCode = false;
		booking.setStatus(Booking.BookingStatus.INACTIVE);
		returnCode = true;
		return returnCode;
	}

}
