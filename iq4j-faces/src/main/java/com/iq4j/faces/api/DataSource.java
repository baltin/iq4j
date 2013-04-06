package com.iq4j.faces.api;

import java.util.List;

import com.iq4j.utils.api.ObjectList;

public interface DataSource<T> extends ObjectList<T> {

	public List<T> getFilteredList();
	public void setFilteredList(List<T> filteredList);
	
	public boolean isSelected();
	public boolean isSelectable();
	public T getSelection();
	public void setSelection(T instance);
	
	
}
