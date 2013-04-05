package com.iq4j.utils.impl;

import java.util.List;

import com.iq4j.utils.api.ObjectList;

public abstract class ObjectListImpl<T> implements ObjectList<T> {

	private static final long serialVersionUID = 4275715387895864207L;

	private List<T> list;
	
	@Override
	public List<T> getList() {
		if(list == null) {
			list = load();
		}
		return list;
	}

	@Override
	public void refresh() {
		list = null;
	}
	
	protected abstract List<T> load();

}
