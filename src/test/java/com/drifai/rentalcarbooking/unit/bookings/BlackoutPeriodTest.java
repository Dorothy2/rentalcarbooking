package com.drifai.rentalcarbooking.unit.bookings;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.BlackoutPeriod;
import com.drifai.rentalcarbooking.bookings.Booking;
import com.drifai.rentalcarbooking.bookings.Booking.BookingStatus;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;
import com.drifai.rentalcarbooking.bookings.Customer;
import com.drifai.rentalcarbooking.cars.Car;
import com.drifai.rentalcarbooking.cars.CarInventory;
import com.drifai.rentalcarbooking.cars.Location;

/**
 * @author Dorothy Rifai
 * 
 */
public class BlackoutPeriodTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.bookings.BlackoutPeriod#BlackoutPeriod()} .
	 */
	@Test
	public void testBlackoutPeriod() {
		final BlackoutPeriod blackoutPeriod = new BlackoutPeriod();
		Assert.assertEquals("UNINITIALIZED", blackoutPeriod.getKey());
		Assert.assertNull(blackoutPeriod.getStartDate());
		Assert.assertNull(blackoutPeriod.getEndDate());
	}	

	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.BlackoutPeriod#BlackoutPeriod(java.util.Date, java.util.Date)}
	 * 
	 */
	@Test
	public void testBlackoutPeriodAllFields() {
		final BlackoutPeriod blackoutPeriod = new BlackoutPeriod(new Date("05/15/2020"), new Date("05/25/2020"));
		Assert.assertEquals("05/15/2020", DRifaiConstants.DD_MM_YYYY.format(blackoutPeriod.getStartDate()));
		Assert.assertEquals("05/25/2020", DRifaiConstants.DD_MM_YYYY.format(blackoutPeriod.getEndDate()));
		Assert.assertEquals("05/15/2020:05/25/2020", blackoutPeriod.getKey());
	}

}

