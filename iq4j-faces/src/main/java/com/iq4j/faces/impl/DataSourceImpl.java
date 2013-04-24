package com.iq4j.faces.impl;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.iq4j.faces.api.DataSource;
import com.iq4j.faces.api.DataSourceFilteredListManager;
import com.iq4j.utils.impl.ObjectListImpl;

public abstract class DataSourceImpl<T> extends ObjectListImpl<T> implements DataSource<T>, Serializable {

	private static final long serialVersionUID = 3917145886233868937L;
	
	// --- fields
	@Inject DataSourceFilteredListManager dataSourceFilteredListManager;
	
	private boolean selectable;
	private T selection;
	
	// --- constructors
	public DataSourceImpl() {
		this(true);
	}
	
	public DataSourceImpl(boolean selectable) {
		this.selectable = selectable;
	}
	
	// --- methods
	protected abstract List<T> load();
	
	@Override
	public void refresh() {
		dataSourceFilteredListManager.put(getClass(), null);
		super.refresh();
	}
	
	// --- properties
	@Override
	public boolean isSelected() {
		return getSelection() != null;
	}
	
	@Override
	public List<T> getFilteredList() {
		return dataSourceFilteredListManager.get(getClass());
	}

	@Override
	public void setFilteredList(List<T> filteredList) {
		dataSourceFilteredListManager.put(getClass(), filteredList);
	}

	@Override
	public boolean isSelectable() {
		return selectable;
	}

	@Override
	public T getSelection() {
		return selection;
	}

	@Override
	public void setSelection(T instance) {
		this.selection = instance;
	}

}
