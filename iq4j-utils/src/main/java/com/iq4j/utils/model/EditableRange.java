package com.iq4j.utils.model;

public interface EditableRange<T> extends Range<T> {

	public void setMin(T value);
	public void setMax(T value);
	
}
