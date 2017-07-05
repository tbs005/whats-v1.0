package com.sunder.xie.whats.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class DynamicDataSource extends AbstractRoutingDataSource {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected Object determineCurrentLookupKey() {
        logger.debug("####****---->>>> DynamicDataSource.determineCurrentLookupKey: {}", DataSourceHolder.getDataSourceKey());
        return DataSourceHolder.getDataSourceKey();
    }
}
