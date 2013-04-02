package com.iq4j.utils.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

public interface Period extends Range<Date>,  Serializable {
	
	public static final Comparator<Period> COMPARATOR = new Comparator<Period>() {		
		@Override
		public int compare(Period o1, Period o2) {
			return o1.getMin().compareTo(o2.getMin());
		}
	};
	
	public Period copy();
	
}
