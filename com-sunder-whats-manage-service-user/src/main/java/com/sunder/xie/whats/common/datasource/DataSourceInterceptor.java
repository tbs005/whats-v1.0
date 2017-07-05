package com.sunder.xie.whats.common.datasource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class DataSourceInterceptor implements MethodInterceptor {
    private String dataSourceKey;
    private static final String QUERY_DATA_SOURCE_KEY = "query";
    private static final String ARCHIVE_DATA_SOURCE_KEY = "archive";

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object o = null;
        if (QUERY_DATA_SOURCE_KEY.equals(dataSourceKey)) {
            DataSourceHolder.setQueryDataSource();
        } else if (ARCHIVE_DATA_SOURCE_KEY.equals(dataSourceKey)) {
            DataSourceHolder.setArchiveDataSource();
        } else {
            DataSourceHolder.setProductionDataSource();
        }
        try{
            o = methodInvocation.proceed();
        } catch(Exception e) {
            throw e;
        } finally {
            DataSourceHolder.clearDataSourceKey();
        }

        return o;
    }

    public void setDataSourceKey(String dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }
}
