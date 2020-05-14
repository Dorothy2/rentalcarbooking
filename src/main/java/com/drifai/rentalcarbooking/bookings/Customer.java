package com.drifai.rentalcarbooking.bookings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Customer class containing information about customers for car reservation bookings.
 * 
 * @author Dorothy Rifai
 */
 public class Customer {
	private String firstName;
	private String lastName;
	private String address1;
	private String city;
	private String state;
	private String zipCode;
	private String phone;
	private String email;
	private String driversLicenseId;
	private Date dateOfBirth;
	//private final List<Booking> bookings = new ArrayList<Booking>();
	//final SimpleDateFormat DD_MM_YYYY = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Default constructor for the Customer class.
	 */
	public Customer() {
	}

	/**
	 * Constructor for the Customer class which takes customer data via params
	 * 
	 * @param firstName
	 *            Customer First Name.
	 * @param lastName
	 *            Customer Last Name.
	 * @param address1
	 *            Customer Address line 1.
	 * @param city
	 *            Customer City.
	 * @param state
	 *            Customer State.
	 * @param zipCode
	 *            Customer ZipCode.
	 * @param phone
	 *            Customer Phone.
	 * @param email
	 *            Customer Email.
	 */
	public Customer(final String firstName, final String lastName,
			final String address1, final String city, final String state, 
			final String zipCode, final String phone, final String email,
			final String driversLicenseId, final String strDateOfBirth) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setAddress1(address1);
		this.setCity(city);
		this.setState(state);
		this.setZipCode(zipCode);
		this.setPhone(phone);
		this.setEmail(email);
		this.setDriversLicenseId(driversLicenseId);
		// TODO: validation for string DOB passed in (later release :) )
		try {
			Date dob = DRifaiConstants.DD_MM_YYYY.parse(strDateOfBirth);
			// TODO: Need check somewhere to make sure the customer is > 25
			this.setDateOfBirth(dob);
		} catch (ParseException e) {
			// TODO If time permits, handle this more gracefully.
			e.printStackTrace();
		}
		
		
	}
	
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
		
	}
	
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
		
	}
	
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
		
	}
	
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
		
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDriversLicenseId() {
		return driversLicenseId;
	}

	public void setDriversLicenseId(String driversLicenseId) {
		this.driversLicenseId = driversLicenseId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


}

