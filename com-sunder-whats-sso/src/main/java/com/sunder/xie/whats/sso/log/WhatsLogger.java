package com.sunder.xie.whats.sso.log;

import java.util.Map;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public interface WhatsLogger {

    void warn(String arg0);

    void error(String arg0);

    void warn(String arg0, Throwable arg1);

    void warn(Throwable arg0, int arg1);

    void warn(String arg0, Throwable arg1, int arg2);

    void error(String arg0, Throwable arg1);

    void error(String arg0, Throwable arg1, int arg2);

    void error(Throwable arg0, int arg1);

    void warnMap(Map arg0);

    void warnMap(String arg0, Map arg1);

    void errorMap(Map arg0);

    void errorMap(String arg0, Map arg1);

    void warnWithType(String arg0, String arg1, Throwable arg2);

    void warnWithType(String arg0, String arg1, Throwable arg2, int arg3);

    void warnWithType(String arg0, String arg1);

    void errorWithType(String arg0, String arg1);

    void errorWithType(String arg0, String arg1, Throwable arg2);

    void errorWithType(String arg0, String arg1, Throwable arg2, int arg3);

}
