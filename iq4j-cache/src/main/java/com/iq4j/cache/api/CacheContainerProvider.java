package com.iq4j.cache.api;

import org.infinispan.manager.CacheContainer;

public interface CacheContainerProvider {

	public CacheContainer getCacheContainer();
	
}
