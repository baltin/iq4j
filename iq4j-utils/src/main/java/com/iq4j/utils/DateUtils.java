package com.iq4j.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

public class DateUtils {
	
	
	/**
	 * Check two dates equality regarding only date parts and omitting time parts
	 * 
	 * null parameters are supposed to be new Date() 
	 * 
	 * @param date1
	 * @param date2
	 * @return true if given date's date parts are equal otherwise false
	 */
	public static boolean dateEquals(Date date1, Date date2) {
		return new DateTime(date1).withMillisOfDay(0).compareTo(new DateTime(date2).withMillisOfDay(0)) == 0;
	}
	
	/***
	 * Compares two dates and returns the highest
	 * 
	 * @param date1 can not be null
	 * @param date2 can not be null
	 * @return bigger date
	 * @throws NullPointerException if date1 or date2 is null
	 */
	public static Date compareAndGetBigger(Date date1, Date date2) throws NullPointerException {
		return date1.after(date2) ? date1 : date2;
	}

	/***
	 * Compares two dates and returns the lowest 
	 * 
	 * @param date1 can not be null
	 * @param date2 can not be null
	 * @return lowest date
	 * @throws NullPointerException if date1 or date2 is null
	 */
	public static Date compareAndGetLower(Date date1, Date date2) throws NullPointerException {
		return date1.before(date2) ? date1 : date2;
	}

	public static Date addDay(Date day, int dayCount) {		
		return new DateTime(day).plusDays(dayCount).toDate();
	}
	
	/**
	 * returns a dates between start and end dates as list
	 * 
	 * @param start 
	 * @param end
	 * @param addLastDay used to decide adding end date to result or not 
	 * @return list of dates
	 * 
	 * @throws IllegalArgumentException if start date is after than end
	 */
	public static List<Date> toList(Date start, Date end, boolean addLastDay) throws RuntimeException {
		
		if(start.after(end)) {
			throw new IllegalArgumentException("start date shoul not be after than end date");
		}
		
		List<Date> result = new ArrayList<Date>();
    	
		while(start.before(end)) {
    		result.add(start);
    		start = DateUtils.addDay(start, 1);
    	}
		
		if(addLastDay) {
			result.add(end);
		}
		
    	return result;
    	
	}
	
	/**
	 * @return Today's date 
	 */
	public static Date today() {
		
		return new DateTime(System.currentTimeMillis()).withTime(0, 0, 0, 0).toDate();
		
	}
	
}
