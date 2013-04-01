package com.iq4j.cache.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfiguration;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.eviction.EvictionStrategy;
import org.infinispan.loaders.jdbc.stringbased.JdbcStringBasedCacheStore;
import org.infinispan.transaction.LockingMode;
import org.infinispan.transaction.TransactionMode;
import org.infinispan.transaction.lookup.GenericTransactionManagerLookup;
import org.infinispan.util.concurrent.IsolationLevel;

import com.iq4j.cache.api.CacheConfiguration;

@ApplicationScoped
public class JDBCBasedCacheConfiguration implements CacheConfiguration {

	public final static String DEFAULT_JMX_DOMAIN = "com.iq4j.cache";
    public final static String JDBC_STRING_BASED_CACHED_STORE_PROPERTIES_FILE =  "META-INF/JDBCStringBasedCacheStore.properties";
	
	private Properties JDBCStringBasedCacheStoreProperties;

	@PostConstruct
	protected void init() {
		JDBCStringBasedCacheStoreProperties = new Properties();
		try {
			InputStream  properties = JDBCBasedCacheConfiguration.class.getResourceAsStream("JDBCStringBasedCacheStore.properties");
			JDBCStringBasedCacheStoreProperties.load(properties);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	public GlobalConfiguration getGlobalConfiguration() {

		return new GlobalConfigurationBuilder()
							.nonClusteredDefault()
							.globalJmxStatistics()
							.enable()
							.jmxDomain(DEFAULT_JMX_DOMAIN)
							.build();

	}

	public Configuration getConfiguration() {
		
		return new ConfigurationBuilder()
						.jmxStatistics()
							.enable()
					    .clustering()
				 			.cacheMode(CacheMode.LOCAL)		
				 		.transaction()
					 		.transactionManagerLookup(new GenericTransactionManagerLookup())
					 		.transactionMode(TransactionMode.TRANSACTIONAL)
					 		.autoCommit(false)
				 			.lockingMode(LockingMode.OPTIMISTIC)
				 		.locking()
				 			.concurrencyLevel(10000)
				 			.isolationLevel(IsolationLevel.REPEATABLE_READ)
					    .loaders()	       
						    .passivation(false)
						    .preload(true)
					    	.shared(false)
				 			.addCacheLoader()
				 				.cacheLoader(new JdbcStringBasedCacheStore())				 					
				 					.fetchPersistentState(false)
				 					.purgeOnStartup(false)
				 					.addProperty("connectionFactoryClass", "org.infinispan.loaders.jdbc.connectionfactory.ManagedConnectionFactory")
						 			.addProperty("datasourceJndiLocation", JDBCStringBasedCacheStoreProperties.getProperty("datasourceJndiLocation"))
						 			.addProperty("idColumnType", JDBCStringBasedCacheStoreProperties.getProperty("idColumnType"))
						 			.addProperty("idColumnName", JDBCStringBasedCacheStoreProperties.getProperty("idColumnName"))
						 			.addProperty("dataColumnType", JDBCStringBasedCacheStoreProperties.getProperty("dataColumnType"))
						 			.addProperty("dataColumnName", JDBCStringBasedCacheStoreProperties.getProperty("dataColumnName"))
						 			.addProperty("timestampColumnName", JDBCStringBasedCacheStoreProperties.getProperty("timestampColumnName"))
						 			.addProperty("timestampColumnType", JDBCStringBasedCacheStoreProperties.getProperty("timestampColumnType"))
						 			.addProperty("stringsTableNamePrefix", JDBCStringBasedCacheStoreProperties.getProperty("stringsTableNamePrefix"))
						 			.addProperty("createTableOnStart", JDBCStringBasedCacheStoreProperties.getProperty("createTableOnStart"))
						 			.addProperty("dropTableOnExit", JDBCStringBasedCacheStoreProperties.getProperty("dropTableOnExit"))
				           .eviction()
					           .strategy(EvictionStrategy.LRU)
					       	   .maxEntries(30000)
						   .build();
	}

}
