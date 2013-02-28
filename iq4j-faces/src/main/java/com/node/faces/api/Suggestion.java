package com.node.faces.api;

import java.util.List;

public interface Suggestion<T> {

	/***
	 * 
	 * Main search function to provide instant search. ResultList  cached nowhere
	 * 
	 * @param key 
	 * @return 
	 * 		search result as list of object(s)
	 */
	public List<T> list(String key);
	
	
	/**
	 * Returns String representation of suggested object instance.
	 * 
	 * 
	 * @param instance
	 * @return String representation of instance
	 */
	public String labelForInstance(T instance);
	
}
