package com.iq4j.persistence.impl;

import java.util.List;

import com.iq4j.persistence.api.Entity;
import com.iq4j.persistence.api.EntityList;
import com.iq4j.utils.impl.ObjectListImpl;

@SuppressWarnings("rawtypes")
public abstract class EntityListImpl<T extends Entity> extends ObjectListImpl<T> implements EntityList<T> {

	private static final long serialVersionUID = 6513130417080543797L;

	@Override
	protected abstract List<T> load();

}
