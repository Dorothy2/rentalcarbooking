package com.drifai.rentalcarbooking.bookings;

import java.util.Calendar;
import java.util.Date;

public class Booking {
	private Integer id;
	private Date dropOffDate;
	private Date pickUpDate;
	private boolean ageVerified;
	//private enum BookingStatus status;
	
	
    public Booking() {
    	super();
    }
	
	public Booking(Integer id, Date pickUpDate, int days, boolean ageVerified) {
		this(id, pickUpDate, null, ageVerified);
		//Date pickUpDate = convertDaysToPickupDate(dropOffDate, days);
		setDropOffDate(convertDaysToDropOffDate(pickUpDate, days));
	}
	

	public Booking(Integer id, Date pickUpDate, Date dropOffDate, boolean ageVerified) {
		super();
		this.id = id;
		this.pickUpDate = pickUpDate;
		if(dropOffDate != null) {
			this.dropOffDate = dropOffDate;
		}
		this.ageVerified = ageVerified;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDropOffDate() {
		return dropOffDate;
	}
	public void setDropOffDate(Date dropOffDate) {
		this.dropOffDate = dropOffDate;
	}
	public Date getPickUpDate() {
		return pickUpDate;
	}
	public void setPickUpDate(Date pickUpDate) {
		this.pickUpDate = pickUpDate;
	}
	public boolean isAgeVerified() {
		return ageVerified;
	}
	public void setAgeVerified(boolean ageVerified) {
		this.ageVerified = ageVerified;
	}
	
	private Date convertDaysToDropOffDate(Date dropOffDate, int days) {
		Date currentDate = new Date();
        //System.out.println(dateFormat.format(currentDate));

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(dropOffDate);

        // manipulate date
        c.add(Calendar.DATE, days); 
 
        // convert calendar to date
        return(c.getTime());
	}

}
