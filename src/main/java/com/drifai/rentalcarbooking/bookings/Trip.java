package com.drifai.rentalcarbooking.bookings;

import java.util.Date;

public class Trip {
	Date startDate;
	Date endDate;
	
	public Trip() {
		
	}
	
	public Trip(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}

}
