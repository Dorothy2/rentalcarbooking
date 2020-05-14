package com.drifai.rentalcarbooking.unit.bookings;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.drifai.rentalcarbooking.bookings.Customer;

/**
 * @author Dorothy Rifai
 * 
 */
public class CustomerTest {

	/**
	 * Test method for
	 * {@link com.drifai.rentalcarbooking.bookings.Customer#Customer()} .
	 */
	@Test
	public void testCustomer() {
		final Customer customer = new Customer();
		Assert.assertNull(customer.getFirstName());
		Assert.assertNull(customer.getLastName());
		Assert.assertNull(customer.getAddress1());
		Assert.assertNull(customer.getCity());
		Assert.assertNull(customer.getState());
		Assert.assertNull(customer.getZipCode());
		Assert.assertNull(customer.getPhone());
		Assert.assertNull(customer.getEmail());
		Assert.assertNull(customer.getDriversLicenseId());
		Assert.assertNull(customer.getDateOfBirth());
		//Assert.assertNotNull(customer.getBookings());
		//Assert.assertEquals(0, customer.getBookings().size());
	}

	/**
	 * Test method for
	 * {@link drifai.rentalcarbooking.bookings.Customer#Customer(java.lang.String, java.lang.String,
	 *  java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 *  java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCustomerAllFields() {
		final Customer customer = new Customer("Bob", "White", "35 Bird Lane", "Waltham",
				"MA", "02451", "781-555-1212", "bwhite@gmail.com", "S-12345", "11/14/1980");
		Assert.assertEquals("Bob", customer.getFirstName());
		Assert.assertEquals("White", customer.getLastName());
		Assert.assertEquals("35 Bird Lane", customer.getAddress1());
		Assert.assertEquals("Waltham", customer.getCity());
		Assert.assertEquals("MA", customer.getState());
		Assert.assertEquals("02451", customer.getZipCode());
		Assert.assertEquals("781-555-1212", customer.getPhone());
		Assert.assertEquals("bwhite@gmail.com", customer.getEmail());
		Assert.assertEquals("S-12345", customer.getDriversLicenseId());
		Assert.assertEquals(new Date("11/14/1980"), customer.getDateOfBirth());
		
		//Assert.assertNotNull(customer.getBookings());
		//Assert.assertEquals(0, customer.getBookings().size());
	}


}

