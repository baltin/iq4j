package com.iq4j.cache.api;

/**
 * 
 * Provides cache statistics.
 * 
 * @author Martin Gencur
 * @author Anatolian (sertac at anadollu dot com)
 */
public interface StatisticsProvider {

    public String getRetrievals();

    public String getStores();

    public String getCurrentEntries();

    public String getTotalNumberOfEntries();
    
    public String getHits();

    public String getMisses();

    public String getRemoveHits();

}
