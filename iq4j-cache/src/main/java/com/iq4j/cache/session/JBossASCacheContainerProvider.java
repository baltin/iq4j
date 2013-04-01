package com.iq4j.cache.session;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.jboss.solder.logging.Logger;

import com.iq4j.cache.api.CacheContainerProvider;

@ApplicationScoped
public class JBossASCacheContainerProvider implements CacheContainerProvider {

	@Inject Logger logger;
	@Inject JDBCBasedCacheConfiguration cacheCongiguration;
	
	private CacheContainer cacheContainer;
	
	@PostConstruct
	protected void init() {
		cacheContainer = new DefaultCacheManager(cacheCongiguration.getGlobalConfiguration(), cacheCongiguration.getConfiguration(), true);
		logger.info("=== IQ4J JBoss AS Cache Container Created ===");		
	}
	
	@Override
	public CacheContainer getCacheContainer() {
		return cacheContainer;
	}

	@PreDestroy
	public void preDestroy() {
		cacheContainer.stop();
		cacheContainer = null;
		logger.info("=== IQ4J JBoss AS Cache Container Destroyed ===");
	}
	
}
