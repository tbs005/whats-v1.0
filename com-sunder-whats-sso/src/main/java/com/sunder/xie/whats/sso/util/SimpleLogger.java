package com.sunder.xie.whats.sso.util;

import com.sunder.xie.whats.sso.log.WhatsLogger;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;
import org.slf4j.spi.LocationAwareLogger;
import org.slf4j.ext.LoggerWrapper;
import java.util.Map;
/**
 * Created by xieshengrong on 2017/5/24.
 */
public class SimpleLogger extends LoggerWrapper implements Logger, WhatsLogger {
    private static final String FQCN = SimpleLogger.class.getName();
    static Marker FLOW_MARKER = MarkerFactory.getMarker("FLOW");
    static Marker ENTRY_MARKER = MarkerFactory.getMarker("ENTRY");
    static Marker EXIT_MARKER = MarkerFactory.getMarker("EXIT");
    static Marker EXCEPTION_MARKER = MarkerFactory.getMarker("EXCEPTION");
    static Marker THROWING_MARKER = MarkerFactory.getMarker("THROWING");
    static Marker CATCHING_MARKER = MarkerFactory.getMarker("CATCHING");
    static String EXIT_MESSAGE_0 = "exit";
    static String EXIT_MESSAGE_1 = "exit with ({})";
    static String ENTRY_MESSAGE_0 = "entry";
    static String ENTRY_MESSAGE_1 = "entry with ({})";
    static String ENTRY_MESSAGE_2 = "entry with ({}, {})";
    static String ENTRY_MESSAGE_3 = "entry with ({}, {}, {})";
    static String ENTRY_MESSAGE_4 = "entry with ({}, {}, {}, {})";
    static int ENTRY_MESSAGE_ARRAY_LEN = 5;
    static String[] ENTRY_MESSAGE_ARRAY;

    public SimpleLogger(Logger logger) {
        super(logger, LoggerWrapper.class.getName());
    }

    public void entry(Object... argArray) {
        if (this.instanceofLAL && this.logger.isTraceEnabled(ENTRY_MARKER)) {
            String messagePattern = null;
            if (argArray.length < ENTRY_MESSAGE_ARRAY_LEN) {
                messagePattern = ENTRY_MESSAGE_ARRAY[argArray.length];
            } else {
                messagePattern = buildMessagePattern(argArray.length);
            }

            FormattingTuple tp = MessageFormatter.arrayFormat(messagePattern, argArray);
            ((LocationAwareLogger) this.logger).log(ENTRY_MARKER, FQCN, 0, tp.getMessage(), argArray,
                    tp.getThrowable());
        }

    }

    public void exit() {
        if (this.instanceofLAL && this.logger.isTraceEnabled(ENTRY_MARKER)) {
            ((LocationAwareLogger) this.logger).log(EXIT_MARKER, FQCN, 0, EXIT_MESSAGE_0, (Object[]) null,
                    (Throwable) null);
        }

    }

    public void exit(Object result) {
        if (this.instanceofLAL && this.logger.isTraceEnabled(ENTRY_MARKER)) {
            FormattingTuple tp = MessageFormatter.format(EXIT_MESSAGE_1, result);
            ((LocationAwareLogger) this.logger).log(EXIT_MARKER, FQCN, 0, tp.getMessage(), new Object[] { result },
                    tp.getThrowable());
        }

    }

    public void throwing(Throwable throwable) {
        if (this.instanceofLAL) {
            ((LocationAwareLogger) this.logger).log(THROWING_MARKER, FQCN, 40, "throwing", (Object[]) null, throwable);
        }

    }

    public void throwing(SimpleLogger.Level level, Throwable throwable) {
        if (this.instanceofLAL) {
            ((LocationAwareLogger) this.logger).log(THROWING_MARKER, FQCN, level.level, "throwing", (Object[]) null,
                    throwable);
        }

    }

    public void catching(Throwable throwable) {
        if (this.instanceofLAL) {
            ((LocationAwareLogger) this.logger).log(CATCHING_MARKER, FQCN, 40, "catching", (Object[]) null, throwable);
        }

    }

    public void catching(SimpleLogger.Level level, Throwable throwable) {
        if (this.instanceofLAL) {
            ((LocationAwareLogger) this.logger).log(CATCHING_MARKER, FQCN, level.level, "catching", (Object[]) null,
                    throwable);
        }

    }

    private static String buildMessagePattern(int len) {
        StringBuilder sb = new StringBuilder();
        sb.append(" entry with (");

        for (int i = 0; i < len; ++i) {
            sb.append("{}");
            if (i != len - 1) {
                sb.append(", ");
            }
        }

        sb.append(')');
        return sb.toString();
    }

    public void warn(String msg, Throwable t) {
        LoggerUtils.incrementCount(t, (String) null);
        if (this.logger.isWarnEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 30, msg, (Object[]) null, t);
            } else {
                this.logger.warn(msg);
            }

        }
    }

    public void warn(String msg) {
        if (this.logger.isWarnEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 30, msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.warn(msg);
            }

        }
    }

    public void error(String msg) {
        if (this.logger.isErrorEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 40, msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.error(msg);
            }

        }
    }

    public void error(String msg, Throwable t) {
        LoggerUtils.incrementCount(t, (String) null);
        if (this.logger.isErrorEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 40, msg, (Object[]) null, t);
            } else {
                this.logger.error(msg);
            }

        }
    }

    public void warnMap(String type, Map msgMap) {
        String msg = "" + msgMap;
        if (this.logger.isWarnEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 30, msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.warn(msg);
            }

        }
    }

    public void warnMap(Map msgMap) {
        String msg = "" + msgMap;
        if (this.logger.isWarnEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 30, msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.warn(msg);
            }

        }
    }

    public void errorMap(String type, Map msgMap) {
        String msg = "" + msgMap;
        if (this.logger.isErrorEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 40, msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.error(msg);
            }

        }
    }

    public void errorMap(Map msgMap) {
        String msg = "" + msgMap;
        if (this.logger.isErrorEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 40, msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.error(msg);
            }

        }
    }

    public void warnWithType(String type, String msg, Throwable t) {
        LoggerUtils.incrementCount(t, (String) null);
        if (this.logger.isWarnEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 30, type + msg, (Object[]) null, t);
            } else {
                this.logger.warn(msg);
            }

        }
    }

    public void warnWithType(String type, String msg) {
        if (this.logger.isWarnEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 30, type + msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.warn(msg);
            }

        }
    }

    public void errorWithType(String type, String msg) {
        if (this.logger.isErrorEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 40, type + msg, (Object[]) null,
                        (Throwable) null);
            } else {
                this.logger.error(msg);
            }

        }
    }

    public void errorWithType(String type, String msg, Throwable t) {
        LoggerUtils.incrementCount(t, (String) null);
        if (this.logger.isErrorEnabled()) {
            if (this.instanceofLAL) {
                ((LocationAwareLogger) this.logger).log((Marker) null, FQCN, 40, type + msg, (Object[]) null, t);
            } else {
                this.logger.error(msg);
            }

        }
    }

    public void warn(String msg, Throwable e, int rows) {
        msg = msg + "\n" + LoggerUtils.getExceptionTrace(e, rows);
        this.warn(msg);
    }

    public void error(String msg, Throwable e, int rows) {
        msg = msg + "\n" + LoggerUtils.getExceptionTrace(e, rows);
        this.error(msg);
    }

    public void warnWithType(String type, String msg, Throwable e, int rows) {
        msg = msg + "\n" + LoggerUtils.getExceptionTrace(e, rows);
        this.warnWithType(type, msg);
    }

    public void errorWithType(String type, String msg, Throwable e, int rows) {
        msg = msg + "\n" + LoggerUtils.getExceptionTrace(e, rows);
        this.errorWithType(type, msg);
    }

    public void warn(Throwable e, int rows) {
        String msg = LoggerUtils.getExceptionTrace(e, rows);
        this.warn(msg);
    }

    public void error(Throwable e, int rows) {
        String msg = LoggerUtils.getExceptionTrace(e, rows);
        this.error(msg);
    }

    static {
        ENTRY_MESSAGE_ARRAY = new String[ENTRY_MESSAGE_ARRAY_LEN];
        ENTRY_MARKER.add(FLOW_MARKER);
        EXIT_MARKER.add(FLOW_MARKER);
        THROWING_MARKER.add(EXCEPTION_MARKER);
        CATCHING_MARKER.add(EXCEPTION_MARKER);
        ENTRY_MESSAGE_ARRAY[0] = ENTRY_MESSAGE_0;
        ENTRY_MESSAGE_ARRAY[1] = ENTRY_MESSAGE_1;
        ENTRY_MESSAGE_ARRAY[2] = ENTRY_MESSAGE_2;
        ENTRY_MESSAGE_ARRAY[3] = ENTRY_MESSAGE_3;
        ENTRY_MESSAGE_ARRAY[4] = ENTRY_MESSAGE_4;
    }

    public static enum Level {
        TRACE("TRACE", 0), DEBUG("DEBUG", 10), INFO("INFO", 20), WARN("WARN", 30), ERROR("ERROR", 40);

        private final String name;
        private final int level;

        public String toString() {
            return this.name;
        }

        public int intValue() {
            return this.level;
        }

        private Level(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }
