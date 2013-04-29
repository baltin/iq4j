package com.iq4j.faces.impl;

import com.iq4j.faces.api.SelectItems;
import com.iq4j.utils.model.Definition;

public abstract class SelectItemsImpl<T> implements SelectItems<T> {

	@Override
	public Object valueForInstance(T instance) {
		return instance;
	}

	@Override
	public String labelForInstance(T instance) {
		
		return (instance instanceof Definition) 
					? ((Definition)instance).getName() 
					: instance.toString();
					
	}

}
