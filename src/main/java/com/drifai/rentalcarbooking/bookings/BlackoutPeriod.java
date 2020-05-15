package com.drifai.rentalcarbooking.bookings;

import java.util.Date;

import com.drifai.rentalcarbooking.utilities.DRifaiConstants;
/**
 * 
 * @author Dorothy Rifai
 * 
 * BlackoutPeriod is used to track the unavailable dates.
 * Dates are unavailable either because the car has already been reserved for rentals. 
 * 
 * There is also a CarHistory checkAvailability() method which inserts blackout periods for 
 * all desired rental trips, and then resets it to the actual blackout period before leaving the method.
 *
 */
public class BlackoutPeriod {
	private Date startDate;
	private Date endDate;
	private String key;
	
	public BlackoutPeriod() {
		super();
	}
	/***
	 * 
	 * @param startDate BlackoutPeriod starts
	 * @param endDate BlackoutPeriod ends
	 */
	public BlackoutPeriod(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
		generateKey();
	}
	
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getKey() {
		if(key == null) {
			generateKey();
		}
		return key;
	}
	
	private void generateKey() {
		if(startDate == null || endDate == null) {
			key = "UNINITIALIZED";
			return;
		}
		StringBuilder sb = new StringBuilder(DRifaiConstants.DD_MM_YYYY.format(startDate));
		sb.append(":");
		sb.append(DRifaiConstants.DD_MM_YYYY.format(endDate));
		key = sb.toString();
		return;
	}

}
