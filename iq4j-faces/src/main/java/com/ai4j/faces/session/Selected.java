package com.ai4j.faces.session;

import javax.enterprise.inject.Model;

@Model
public class Selected {

	private Object[] array;
	private Object instance;
	
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public Object[] getArray() {
		return array;
	}
	public void setArray(Object[] array) {
		this.array = array;
	}	
	
	
}
