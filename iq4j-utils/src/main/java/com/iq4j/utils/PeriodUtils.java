package com.iq4j.utils;

import com.iq4j.utils.model.Period;

/**
 * Utility Class to be used with {@link Period} implementations
 * 
 * @author Anatolian
 *
 */
public class PeriodUtils {

	public static boolean equals(Period period1, Period period2) {
		return DateUtils.dateEquals(period1.getMin(), period2.getMin()) 
			&& DateUtils.dateEquals(period1.getMax(), period2.getMax())	;
	}
	
	
}
