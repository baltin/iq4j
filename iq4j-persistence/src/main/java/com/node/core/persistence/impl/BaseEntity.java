package com.node.core.persistence.impl;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity extends EntityImpl<Long> {

	private static final long serialVersionUID = -4875488469275310715L;

	@Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable=false)
    @Access(AccessType.PROPERTY)
	private Long id;
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		if(this.id == null && id != null) {			
			this.id = id;
		}
	}
	
}
