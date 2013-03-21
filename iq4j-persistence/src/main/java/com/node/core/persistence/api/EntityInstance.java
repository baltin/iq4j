package com.node.core.persistence.api;

import java.io.Serializable;

import javax.persistence.EntityManager;

import com.iq4j.utils.api.Instance;

public interface EntityInstance<E extends Entity<ID>, ID extends Serializable> extends Instance<E>, Serializable {

	public EntityManager getEntityManager();
	public Class<E> getEntityClass();
	
	public ID getId();
	public void setId(ID id);
	
	public boolean persist();
	public boolean update();
	public boolean remove();
	public boolean save();
	
	public boolean validate();
	public void refresh();
	
	
	
}
