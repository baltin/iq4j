package com.iq4j.faces.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class DataSourceFilteredListManager {

	@SuppressWarnings("rawtypes")
	private Map<Class<? extends DataSource>, List> filteredLists = new HashMap<Class<? extends DataSource>, List>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> get(Class<? extends DataSource> dataSourceClass) {
		return filteredLists.get(dataSourceClass);
	}
	
	@SuppressWarnings("rawtypes")
	public <T> void put(Class<? extends DataSource> dataSourceClass, List<T> filteredList) {
		filteredLists.put(dataSourceClass, filteredList);
	}
	
}
