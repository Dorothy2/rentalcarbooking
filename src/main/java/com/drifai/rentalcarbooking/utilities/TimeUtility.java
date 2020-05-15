package com.drifai.rentalcarbooking.utilities;

import java.util.Date;

/**
 * TimeUtility has utility methods for doing dateTime calculations for detected dates when the car has already 
 * been reserved.
 * 
 * @author Dorothy Rifai
 *
 */
public class TimeUtility {

	public TimeUtility() {
		
	}
	
	public boolean overlap(Date start1, Date end1, Date start2, Date end2){
	    return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime(); 
	}
}
