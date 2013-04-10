package com.iq4j.utils.model;

import java.util.Date;

public class SimplePeriod extends EditablePeriodImpl implements EditablePeriod {

	private static final long serialVersionUID = -1677447200129276606L;

	private Date min;
	private Date max;
	
	public SimplePeriod() {
		super(null, null);
	}

	public SimplePeriod(Date min, Date max) {
		super(min, max);
	}
	
	public Date getMin() {
		return min;
	}
	
	public void setMin(Date min) { 
		this.min = min;
	}
	
	public Date getMax() {
		return max;
	}
	
	public void setMax(Date max) {
		this.max = max;
	}

	@Override
	public Period copy() {
		return new SimplePeriod(min, max);
	}
	
}
