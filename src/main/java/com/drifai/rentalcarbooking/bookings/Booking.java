package com.drifai.rentalcarbooking.bookings;

import java.util.Calendar;
import java.util.Date;

import com.drifai.rentalcarbooking.cars.Car;

public class Booking {
	private Integer id;
	private Date dropOffDate;
	private Date pickUpDate;
	private CarHistory car;
	private Customer customer;
	private Booking.BookingStatus status;
	
	public static enum BookingStatus {
	    PENDING, // reserved, prior to pick up date
	    ACTIVE, // between pickup date and drop off date; customer has car
	    INACTIVE, // user cancelled booking prior to pick up date
	    PAST, // after reservation dates, presumably car was returned - determining whether car was returned is outside scope
	    ERROR, // internal error, Customer Service would have to fix
	    CANCELLED; // company cancelled reservation because customer was < 25
	}
	
    public Booking() {
    	super();
    }
	
	public Booking(Date pickUpDate, int days, CarHistory car, Customer customer) {
		this(pickUpDate, null, car, customer);
		setDropOffDate(convertDaysToDropOffDate(pickUpDate, days));
	}
	

	public Booking(Date pickUpDate, Date dropOffDate, CarHistory car, Customer customer) {
		super();
		this.car = car;
		this.customer = customer;
		this.pickUpDate = pickUpDate;
		if(dropOffDate != null) {
			this.dropOffDate = dropOffDate;
		}
		if(! customer.isMinimumAgeVerified()) {
			this.status = BookingStatus.CANCELLED;
		} else { 
			Date now = new Date();
			determineBookingStatus(now);
		}
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
	private Date convertDaysToDropOffDate(Date dropOffDate, int days) {
	    // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(dropOffDate);

        // manipulate date
        c.add(Calendar.DATE, days); 
 
        // convert calendar to date
        return(c.getTime());
	}
	
	public void determineBookingStatus(Date now) {
		this.status = Booking.BookingStatus.ERROR;
		//Date now = new Date();
		//System.out.println("Date: " + DRifaiConstants.DD_MM_YYYY_HH_MM_SS.format(now));
		if(dropOffDate == null  || pickUpDate == null) {
			// have to have both pickUpDate and dropOffDate to set accurate booking status
			return;
		}
		if(now.compareTo(pickUpDate) < 0)  {
			this.status = Booking.BookingStatus.PENDING;
		}
		else if((now.compareTo(pickUpDate) > 0) && (now.compareTo(dropOffDate) <= 0)) {
			this.status = Booking.BookingStatus.ACTIVE;
		}
		else if((now.compareTo(pickUpDate) > 0)  && (now.compareTo(dropOffDate) > 0)) {
			this.status = Booking.BookingStatus.PAST;
		}
		return;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public CarHistory getCar() {
		return car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setStatus(Booking.BookingStatus status) {
		this.status = status;
		
	}

}
