package com.drifai.rentalcarbooking.utilities;

import java.util.Date;

public class TimeUtility {

	boolean overlap(Date start1, Date end1, Date start2, Date end2){
	    return start1.getTime() <= end2.getTime() && start2.getTime() <= end1.getTime(); 
	}
}
