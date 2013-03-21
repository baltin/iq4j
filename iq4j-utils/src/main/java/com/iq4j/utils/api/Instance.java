package com.iq4j.utils.api;

public interface Instance<T> {

	
	public T getInstance();
	public void setInstance(T instance);
	
	/**
	 * Checks managed instance is null
	 * <br/>
	 * @return true if instance is not null
	 */
	public boolean isEmpty();
	
	/**
	 * Cleans managed instance
	 */
	public void clean();
	
}
