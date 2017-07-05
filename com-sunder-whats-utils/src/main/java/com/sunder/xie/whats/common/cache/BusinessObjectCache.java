package com.sunder.xie.whats.common.cache;


import java.text.MessageFormat;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.statistics.LiveCacheStatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessObjectCache {
	@Autowired
	private Cache businessObjectCache;
	private final Logger logger = LoggerFactory.getLogger(BusinessObjectCache.class);

	
	@SuppressWarnings("unchecked")
	public <T> T get(String id, Class<T> clazz) {
		Element element = businessObjectCache.get(id);
		if (element == null) {
			logger.info("Get object from cache id={}, object=null", id);
			return null;
		} else {
			logger.info("Get object from cache id={}, object={}.", id, element.getObjectValue());
			return (T)element.getObjectValue();
		}
	}
	
	public Object get(String id) {
		return get(id, Object.class);
	}

	public void set(String id, Object value) {
		businessObjectCache.put(new Element(id, value));
		logger.info("Set object to cache id={}, object={}.", id, value);
	}

    @Deprecated
    public void set(Long id, Object value) {
        set(id.toString(), value);
    }

    public void remove(String id) {
		businessObjectCache.remove(id);
		logger.info("Remove object from cache id={}.", id);
	}

    @Deprecated
    public void remove(Long id) {
        businessObjectCache.remove(id.toString());
    }

	public void evictExpiredElements() {
		businessObjectCache.evictExpiredElements();
	}

	public String statistics() {
		LiveCacheStatistics statistics = businessObjectCache.getLiveCacheStatistics();
//		long cacheSize = businessObjectCache.getKeys().size();
//		for (Object key : businessObjectCache.getKeys()) {
//			Element element = businessObjectCache.get(key);
//
//		}
		return MessageFormat.format("{0} Statistics: [cacheHits={1,number}, inMemoryHits={2,number}, onDiskHits={3,number}, cacheHitRatio={4,number,percent}, "
				+ "count={5,number}, putCount={6,number}, removedCount={7,number}, localHeapSize={8,number}, localDiskSize={9, number}, averageGetTime={10,number}, "
				+ "evictedCount={11,number}, expiredCount={12,number}]",
				statistics.getCacheName(), 
				statistics.getCacheHitCount(),
				statistics.getInMemoryHitCount(),
				statistics.getOnDiskHitCount(),
				statistics.getCacheHitRatio()/100,
				statistics.getSize(),
				statistics.getPutCount(),
				statistics.getRemovedCount(),
				statistics.getLocalHeapSize(),
				statistics.getLocalDiskSize(),
				statistics.getAverageGetTimeMillis(),
				statistics.getEvictedCount(),
				statistics.getExpiredCount());	
//		return businessObjectCache.getStatistics().toString();


	}
}
