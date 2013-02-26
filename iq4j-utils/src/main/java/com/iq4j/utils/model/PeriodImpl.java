package com.iq4j.utils.model;

import java.util.Date;

public abstract class PeriodImpl extends RangeImpl<Date> implements Period {

	private static final long serialVersionUID = -2762995193435630849L;

	@Override
	public int compareTo(Period o) {
		return getMin().compareTo(o.getMin());
	}

}
