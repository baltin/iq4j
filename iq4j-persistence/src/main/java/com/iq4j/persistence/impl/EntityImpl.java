package com.iq4j.persistence.impl;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.iq4j.persistence.api.Entity;

@MappedSuperclass
public abstract class EntityImpl<ID extends Serializable> implements Entity<ID> {

	private static final long serialVersionUID = -9012111776973735806L;
	
	@Version
	private Long version;
	
	@Column(name="active")
	private Boolean active = Boolean.TRUE;
	
	@Override
	public boolean isManaged() {		
		return getVersion() != null;
	}

	@Override
	public boolean isActive() {
		return active == null ? true : active;
	}

	@Override
	public void activate() {
		this.active = Boolean.TRUE;
	}

	@Override
	public void passivate() {
		this.active = Boolean.FALSE;
	}
	
	public Long getVersion() {
		return version;
	}
	
}
