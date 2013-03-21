package com.iq4j.utils.impl;

import java.io.Serializable;

import com.iq4j.utils.api.Instance;

public class InstanceImpl<T> implements Instance<T>, Serializable {
	
	private static final long serialVersionUID = -5024738292254965097L;

	protected T instance;
	
	@Override
	public T getInstance() {
		return instance;
	}

	@Override
	public void setInstance(T instance) {
		this.instance = instance;
		onInstanceChange();
	}

	@Override
	public boolean isEmpty() {
		return getInstance() == null;
	}

	@Override
	public void clean() {
		setInstance(null);
	}
	
	/**
	 *  Listener method for instance change
	 */
	protected void onInstanceChange() {}
	
}
