package com.iq4j.utils.api;

import java.io.Serializable;
import java.util.List;

public interface ObjectList<T> extends Serializable {

	public List<T> getList();
	public void refresh();
	
}
