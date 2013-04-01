package com.iq4j.cache.impl;

import javax.inject.Inject;

import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.stats.Stats;

import com.iq4j.cache.api.CacheContainerProvider;
import com.iq4j.cache.api.StatisticsProvider;

public abstract class LocalStatisticsProviderImpl implements StatisticsProvider {

    private Stats stats;
	    
    public abstract String getCacheName();
    
    @Inject
    protected void initialize(CacheContainerProvider cacheContainerProvider){	 
    	 stats = ((DefaultCacheManager) cacheContainerProvider.getCacheContainer()).getCache(getCacheName()).getAdvancedCache().getStats();
    } 
	

	public String getRetrievals() {
        return String.valueOf(stats.getRetrievals());
    }

    public String getStores() {
        return String.valueOf(stats.getStores());
    }

    public String getCurrentEntries() {
        return String.valueOf(stats.getCurrentNumberOfEntries());
    }
    
    public String getTotalNumberOfEntries() {
    	return String.valueOf(stats.getTotalNumberOfEntries());
    }

    public String getHits() {
        return String.valueOf(stats.getHits());
    }

    public String getMisses() {
        return String.valueOf(stats.getMisses());
    }

    public String getRemoveHits() {
        return String.valueOf(stats.getRemoveMisses());
    }

}
