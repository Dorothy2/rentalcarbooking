package com.drifai.rentalcarbooking.bookings;

import java.util.Date;

/**
 * 
 * @author Dorothy Rifai
 * 
 * Object with a start date and end date. Used to check whether a single car
 * can be used for multiple transactions.
 *
 */
public class Trip {
	private Date startDate;
	private Date endDate;
	
	public Trip() {
		
	}
	/**
	 * 
	 * @param startDate Date
	 * @param endDate Date
	 */
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
