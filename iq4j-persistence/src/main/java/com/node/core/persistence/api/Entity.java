package com.node.core.persistence.api;

import java.io.Serializable;

import com.iq4j.utils.model.Identifiable;


public interface Entity<ID extends Serializable> extends Identifiable<ID>, Serializable {
	
	public ID getId();
	public void setId(ID id);
	public boolean isManaged();
	public boolean isActive();
	
	public void activate();
	public void passivate();
	
}
