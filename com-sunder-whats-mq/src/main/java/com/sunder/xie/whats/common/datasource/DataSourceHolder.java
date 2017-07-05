package com.sunder.xie.whats.common.datasource;


import org.apache.commons.lang3.StringUtils;


public class DataSourceHolder {
    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void setQueryDataSource() {
        String dataSourceQueryKey = System.getProperty("DataSource_Query_Key");
        if (StringUtils.isBlank(dataSourceQueryKey)) {
            dataSourceQueryKey = "query";
        }
        setDataSourceKey(dataSourceQueryKey);
    }

    public static void setArchiveDataSource() {
        setDataSourceKey("archive");
    }

    public static void setProductionDataSource() {
        setDataSourceKey("production");
    }

    private static void setDataSourceKey(String key) {
        holder.set(key);
    }

    public static String getDataSourceKey() {
        return holder.get();
    }

    public static void clearDataSourceKey() {
        holder.remove();
    }
}
