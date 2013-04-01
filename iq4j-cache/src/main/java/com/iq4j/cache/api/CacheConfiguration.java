package com.iq4j.cache.api;

import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.global.GlobalConfiguration;


public interface CacheConfiguration {

	public GlobalConfiguration getGlobalConfiguration();
	public Configuration getConfiguration();
	
}
