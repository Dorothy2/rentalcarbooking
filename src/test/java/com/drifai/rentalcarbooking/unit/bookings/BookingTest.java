package com.drifai.rentalcarbooking.unit.bookings;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.bookings.DRifaiConstants;

/**
 * @author Dorothy Rifai
 * 
 */
public class BookingTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.bookings.Booking#Booking()} .
	 */
	@Test
	public void testBooking() {
		final Booking booking = new Booking();
		Assert.assertNull(booking.getId());
		Assert.assertNull(booking.getPickUpDate());
		Assert.assertNull(booking.getDropOffDate());
		Assert.assertFalse(booking.isAgeVerified());
	}	

	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.Booking#Booking(java.lang.Integer, java.util.Date,
	 *  java.util.Date, java.lang.Boolean)}
	 * 
	 */
	@Test
	public void testBookingAllFields() {
		final Booking booking = new Booking(1, new Date("05/15/2020"), new Date("05/25/2020"), true);
		Assert.assertEquals(1, booking.getId().intValue());
		Assert.assertEquals("05/15/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getPickUpDate()));
		Assert.assertEquals("05/25/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getDropOffDate()));
		Assert.assertEquals(true, booking.isAgeVerified());
	}
	
	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.Booking#Booking(java.lang.Integer, java.util.Date,
	 *  int, java.lang.Boolean)}
	 * 
	 */
	@Test
	public void testBookingPickUpDatePlusDays() {
		final Booking booking = new Booking(1, new Date("05/15/2020"), 10, true);
		Assert.assertEquals(1, booking.getId().intValue());
		Assert.assertEquals("05/15/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getPickUpDate()));
		Assert.assertEquals("05/25/2020", DRifaiConstants.DD_MM_YYYY.format(booking.getDropOffDate()));
		Assert.assertEquals(true, booking.isAgeVerified());		
	}


}

