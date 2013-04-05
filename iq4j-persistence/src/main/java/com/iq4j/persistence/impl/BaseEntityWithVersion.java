package com.iq4j.persistence.impl;

import javax.persistence.Version;

public class BaseEntityWithVersion extends BaseEntity {

	private static final long serialVersionUID = 2428814681628640754L;

	@Version
	private Long version;
	
	public Long getVersion() {
		return version;
	}
	
	@Override
	public boolean isManaged() {
		return getVersion() != null;
	}
}
