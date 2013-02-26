package com.iq4j.utils.model;


public interface Identifiable<ID>  {
	
	public ID getId();
	public void setId(ID id);
	public boolean isManaged();
	public boolean isActive();
	
	public void activate();
	public void passivate();
	
}
