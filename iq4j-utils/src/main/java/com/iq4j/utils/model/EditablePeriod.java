package com.iq4j.utils.model;

import java.util.Date;

public interface EditablePeriod extends Period {
	
	public void setMin(Date min);
	public void setMax(Date max);
	
}
