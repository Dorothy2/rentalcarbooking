package com.drifai.rentalcarbooking.unit.bookings;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.BlackoutPeriod;
import com.drifai.rentalcarbooking.utilities.DRifaiConstants;

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
	 * {@link drifai.rentalcarbooking.bookings.BlackoutPeriod#BlackoutPeriod(java.util.Date, java.util.Date)}.
	 * 
	 */
	@Test
	public void testBlackoutPeriodAllFields() {
		final BlackoutPeriod blackoutPeriod = new BlackoutPeriod(new Date("06/15/2020"), new Date("06/25/2020"));
		Assert.assertEquals("06/15/2020", DRifaiConstants.DD_MM_YYYY.format(blackoutPeriod.getStartDate()));
		Assert.assertEquals("06/25/2020", DRifaiConstants.DD_MM_YYYY.format(blackoutPeriod.getEndDate()));
		Assert.assertEquals("06/15/2020:06/25/2020", blackoutPeriod.getKey());
	}

}

