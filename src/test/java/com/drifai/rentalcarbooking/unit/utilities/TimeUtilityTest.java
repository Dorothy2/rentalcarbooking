package com.drifai.rentalcarbooking.unit.utilities;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.BlackoutPeriod;
import com.drifai.rentalcarbooking.utilities.TimeUtility;

public class TimeUtilityTest {
	
	@Test
	public void testDateOverlap() {
		// Create Customer
		BlackoutPeriod bp = new BlackoutPeriod(new Date("06/15/2020"), new Date("06/17/2020"));
		Date pickUpDate = new Date("06/15/2020");
		Date dropOffDate = new Date("06/25/2020");
		TimeUtility t = new TimeUtility();
		Assert.assertTrue(t.overlap(bp.getStartDate(), bp.getEndDate(), pickUpDate, dropOffDate));
	}

}
