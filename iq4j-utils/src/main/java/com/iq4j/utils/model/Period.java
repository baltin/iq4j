package com.iq4j.utils.model;

import java.io.Serializable;
import java.util.Date;

public interface Period extends Range<Date>, Comparable<Period>, Serializable {
	
	public Period copy();
	
}
