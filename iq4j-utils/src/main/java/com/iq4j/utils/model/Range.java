package com.iq4j.utils.model;

import java.io.Serializable;

public interface Range<T> extends Serializable{

	public T getMin();

	public T getMax();
	
	public boolean isSetted();

	public boolean isEmpty();

	public boolean isMinEqualsMax();

	public boolean isValid();

	public int compareValues();

	public int compare(T value1, T value2);

	public boolean contains(T value);
	
	public Range<T> copy();
	

}
