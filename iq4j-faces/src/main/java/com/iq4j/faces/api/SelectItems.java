package com.iq4j.faces.api;

import java.util.List;

public interface SelectItems<T> {
	
	public List<T> list();
	public String labelForInstance(T instance);

}
