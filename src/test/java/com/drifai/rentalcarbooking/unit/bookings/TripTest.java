package com.drifai.rentalcarbooking.unit.bookings;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.BlackoutPeriod;
import com.drifai.rentalcarbooking.bookings.Trip;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;

/**
 * @author Dorothy Rifai
 * 
 */
public class TripTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.bookings.Trip#Trip()} .
	 */
	@Test
	public void testTrip() {
		final Trip trip = new Trip();
		Assert.assertNull(trip.getStartDate());
		Assert.assertNull(trip.getEndDate());
	}	

	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.Trip#Trip(java.util.Date, java.util.Date)}
	 * 
	 */
	@Test
	public void testBlackoutPeriodAllFields() {
		final Trip trip = new Trip(new Date("07/15/2020"), new Date("07/25/2020"));
		Assert.assertEquals("07/15/2020", DRifaiConstants.DD_MM_YYYY.format(trip.getStartDate()));
		Assert.assertEquals("07/25/2020", DRifaiConstants.DD_MM_YYYY.format(trip.getEndDate()));
	}

}

