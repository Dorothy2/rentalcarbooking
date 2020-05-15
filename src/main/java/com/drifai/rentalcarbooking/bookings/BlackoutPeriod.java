package com.drifai.rentalcarbooking.bookings;

import java.util.Date;

import com.drifai.rentalcarbooking.utilities.DRifaiConstants;

public class BlackoutPeriod {
	private Date startDate;
	private Date endDate;
	private String key;
	
	public BlackoutPeriod() {
		super();
	}
	
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
