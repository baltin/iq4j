package com.iq4j.persistence.impl;

import java.util.List;

import javax.inject.Inject;

import com.iq4j.persistence.api.Entity;
import com.iq4j.persistence.service.PersistenceUtil;
import com.iq4j.utils.ClassUtils;

@SuppressWarnings("rawtypes")
public class FetchAll<T extends Entity> extends EntityListImpl<T> {

	private static final long serialVersionUID = -8332935909964191710L;
	
	@Inject 
	protected PersistenceUtil persistenceUtil;
	
	@Override
	protected  List<T> load() {
		return persistenceUtil.findAll(getEntityClass());
	}
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {	
		return (Class<T>) ClassUtils.findFirstGenericParameterType(getClass());
	}
	
	

}
