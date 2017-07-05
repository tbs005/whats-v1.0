package com.sunder.xie.whats.sso.web.log;

import com.sunder.xie.whats.sso.log.WhatsLogger;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public class WebLogger {

    public static WhatsLogger getLogger(Class clazz) {
        return LoggerUtils.getLogger(clazz, Config.getServerIp(), Config.SYSTEMID);
    }
}
