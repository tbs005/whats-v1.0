package com.sunder.xie.whats.sso.config;

import com.sunder.xie.whats.sso.web.support.ErrorCode;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public class Config implements InitializingBean {
    public static final String SYSTEMID;
    public static final String DEPLOYID;
    public static final String SESSION_COOKIE_NAME;
    private static boolean isHoutai = false;
    private static boolean isPreEnv = false;
    private static boolean isTestEnv = false;
    private static final Properties props = new Properties();
    private Map<String, String> configMap = new HashMap();
    private Map<String, Object> pageMap = new HashMap();
    private static Map pageTools;
    private boolean initedConfig = false;
    private boolean initedPage = false;

    public static boolean isTestEnv() {
        return isTestEnv;
    }

    public static boolean isPreEnv() {
        return isPreEnv;
    }

    public static boolean isHoutai() {
        return isHoutai;
    }

    public static Map getPageTools() {
        return pageTools;
    }

    public String getGlobalProp(String key) {
        return props.getProperty(key);
    }

    public String getString(String key) {
        String result = (String) this.configMap.get(key);
        if (StringUtils.isBlank(result)) {
            result = pageTools.get(key) == null ? null : "" + pageTools.get(key);
        }

        return result;
    }

    public Long getLong(String key) {
        String result = this.getString(key);
        return StringUtils.isBlank(result) ? null : Long.valueOf(Long.parseLong(result));
    }

    public void setConfigMap(Map<String, String> configMap) {
        if (!this.initedConfig) {
            this.configMap = configMap;
            this.initedConfig = true;
        } else {
            throw new IllegalStateException("不能再次调用");
        }
    }

    public void setPageMap(Map<String, Object> pageMap) {
        if (!this.initedPage) {
            this.pageMap = pageMap;
            this.initedPage = true;
        } else {
            throw new IllegalStateException("不能再次调用");
        }
    }

    public Map<String, Object> getPageMap() {
        return new HashMap(this.pageMap);
    }

    public static String getServerIp() {
        return GewaIpConfig.getServerip();
    }

    public static String getHostname() {
        return GewaIpConfig.getHostname();
    }

    public void afterPropertiesSet() throws Exception {
        this.configMap = UnmodifiableMap.decorate(this.configMap);
        this.initPageTools();
    }

    public void initPageTools() {
        HashMap tmp = new HashMap();
        tmp.put("math", new MathTool());
        tmp.put("DateUtil", new DateUtil());
        tmp.putAll(this.pageMap);
        pageTools = UnmodifiableMap.decorate(tmp);
    }

    public ErrorCode replacePageTool(String property, Object value) {
        Object old = pageTools.get(property);
        if (value != null && old != null) {
            if (!value.getClass().equals(old.getClass())) {
                return ErrorCode.getFailure("参数类型不兼容");
            } else {
                HashMap tmp = new HashMap(pageTools);
                tmp.put(property, value);
                pageTools = UnmodifiableMap.decorate(tmp);
                if (this.pageMap.containsKey(property)) {
                    this.pageMap.put(property, value);
                }

                return ErrorCode.SUCCESS;
            }
        } else {
            return ErrorCode.getFailure("参数错误:old 或 new 为空");
        }
    }

    public String getBasePath() {
        return (String) this.pageMap.get("basePath");
    }

    public String getAbsPath() {
        return (String) this.pageMap.get("absPath");
    }

    public String getCacheVersionKey() {
        return this.getString("cacheVersionKey");
    }

    public static boolean isGewaServerIp(String ip) {
        return GewaIpConfig.isGewaServerIp(ip);
    }

    static {
        try {
            props.load(Config.class.getClassLoader().getResourceAsStream("gewa-global.properties"));
        } catch (IOException arg0) {
            throw new TraceErrorException("", arg0);
        }

        SYSTEMID = props.getProperty("systemid");
        DEPLOYID = SYSTEMID + "-" + SystemUtils.getShortHostname(GewaIpConfig.getHostname());
        if (StringUtils.isNotBlank(props.getProperty("sessionCookieName"))) {
            SESSION_COOKIE_NAME = props.getProperty("sessionCookieName");
        } else {
            SESSION_COOKIE_NAME = SYSTEMID.toLowerCase() + "_uskey_";
        }

        List idList;
        if (StringUtils.isNotBlank(System.getenv("HOUTAI_SYSTEMID"))) {
            idList = Arrays.asList(StringUtils.split(System.getenv("HOUTAI_SYSTEMID"), ","));
            isHoutai = idList.contains(SYSTEMID) || idList.contains("ALL");
        }

        if (StringUtils.isNotBlank(System.getenv("PRE_ENV_SYSTEMID"))) {
            idList = Arrays.asList(StringUtils.split(System.getenv("PRE_ENV_SYSTEMID"), ","));
            isPreEnv = idList.contains(SYSTEMID) || idList.contains("ALL");
        }

        if (StringUtils.isNotBlank(System.getenv("TEST_ENV_SYSTEMID"))) {
            idList = Arrays.asList(StringUtils.split(System.getenv("TEST_ENV_SYSTEMID"), ","));
            isTestEnv = idList.contains(SYSTEMID) || idList.contains("ALL");
        }

    }
}