package com.sunder.xie.whats.sso.util;

import com.sunder.xie.whats.sso.config.WhatsIpConfig;
import com.sunder.xie.whats.sso.log.WhatsLogger;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public class LoggerUtils {

    private static boolean notUseJson = false;
    private static final String tracePackage = "com.whats";
    private static int singleMax;
    private static AtomicInteger criticalCount;
    private static String singleMaxName;
    private static Map<String, AtomicInteger> exceptionCount;
    private static Long exceptionTimefrom;
    private static Set<String> criticalException;

    public static String getSingleMaxName() {
        return singleMaxName;
    }

    public static int getSingleMax() {
        return singleMax;
    }

    public static Long getExceptionTimefrom() {
        return exceptionTimefrom;
    }

    public static int getCriticalExceptionCount() {
        int ret = criticalCount.get();
        return ret;
    }

    public static void incrementCount(Throwable e, String traceMethod) {
        if (e != null) {
            String name = e.getClass().getName();
            if (criticalException.contains(name)) {
                criticalCount.incrementAndGet();
            }

            if (StringUtils.isNotBlank(traceMethod)) {
                name = name + "@" + traceMethod;
            }

            AtomicInteger counter = (AtomicInteger) exceptionCount.get(name);
            if (counter == null) {
                counter = new AtomicInteger(1);
                exceptionCount.put(name, counter);
            } else {
                int max = counter.incrementAndGet();
                if (max > singleMax) {
                    singleMaxName = name;
                    singleMax = max;
                }
            }
        }

    }

    public static Map<String, Integer> getExceptionCountMap() {
        return getCountMap(exceptionCount);
    }

    public static Map<String, Integer> resetExceptionCount() {
        Map cur = exceptionCount;
        exceptionCount = new ConcurrentHashMap();
        criticalCount.set(0);
        exceptionTimefrom = Long.valueOf(System.currentTimeMillis());
        singleMax = 0;
        singleMaxName = "";
        return getCountMap(cur);
    }

    private static Map<String, Integer> getCountMap(Map<String, AtomicInteger> countMap) {
        HashMap result = new HashMap();
        Iterator arg1 = countMap.entrySet().iterator();

        while (arg1.hasNext()) {
            Map.Entry entry = (Map.Entry) arg1.next();
            result.put(entry.getKey(), Integer.valueOf(((AtomicInteger) entry.getValue()).get()));
        }

        return result;
    }

    public static WhatsLogger getLogger(Class clazz) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return (WhatsLogger) (notUseJson ? new SimpleLogger(logger)
                : new JsonLogger(logger, WhatsIpConfig.getServerip(), (String) null));
    }

    public static WhatsLogger getLogger(Class clazz, String serverIp, String systemId) {
        Logger logger = LoggerFactory.getLogger(clazz);
        return (WhatsLogger) (notUseJson ? new SimpleLogger(logger) : new JsonLogger(logger, serverIp, systemId));
    }

    public static WhatsLogger getLogger(String name, String serverIp, String systemId) {
        Logger logger = LoggerFactory.getLogger(name);
        return (WhatsLogger) (notUseJson ? new SimpleLogger(logger) : new JsonLogger(logger, serverIp, systemId));
    }

    public static String getExceptionTrace(Throwable e) {
        return getExceptionTrace(e, 100);
    }

    public static String getExceptionTrace(Throwable e, int rows) {
        StringBuffer result = new StringBuffer(e.getClass().getCanonicalName() + ": " + e.getMessage());
        --rows;
        String tracedMethod = getExceptionTrace(result, e, rows);
        incrementCount(e, tracedMethod);
        return result.toString();
    }

    private static String getExceptionTrace(StringBuffer result, Throwable e, int rows) {
        result.append(e);
        StackTraceElement[] traces = e.getStackTrace();
        String tmp = "";
        String tracedMethod = null;

        for (int ourCause = 0; ourCause < traces.length && rows >= 0; ++ourCause) {
            tmp = traces[ourCause].toString();
            if (tracedMethod == null && StringUtils.contains(tmp, "com.gewara")) {
                tracedMethod = tmp;
            }

            result.append("\n\tat " + tmp);
            --rows;
        }

        if (rows > 0) {
            Throwable arg7 = e.getCause();
            if (arg7 != null) {
                result.append(arg7);
                result.append("\nCaused by");
                --rows;
                if (rows > 0) {
                    String trace = getExceptionTrace(result, arg7, rows);
                    if (tracedMethod == null) {
                        tracedMethod = trace;
                    }
                }
            }
        }

        return tracedMethod;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("/notUseJson.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("notUseJson File create:" + file.exists());
        }

    }

    static {
        notUseJson = (new File("/notUseJson.txt")).exists();
        singleMax = 0;
        criticalCount = new AtomicInteger();
        singleMaxName = "";
        exceptionCount = new ConcurrentHashMap();
        exceptionTimefrom = Long.valueOf(System.currentTimeMillis());
        criticalException = new TreeSet(Arrays.asList(new String[] { "com.alibaba.dubbo.rpc.ProviderException",
                "com.mongodb.MongoException", "java.lang.ArrayIndexOutOfBoundsException",
                "java.net.UnknownHostException", "org.springframework.dao.DataAccessResourceFailureException",
                "org.springframework.dao.RecoverableDataAccessException",
                "org.springframework.jdbc.BadSqlGrammarException", "org.springframework.jms.UncategorizedJmsException",
                "java.lang.StackOverflowError", "java.lang.NoClassDefFoundError" }));
    }
}
}
