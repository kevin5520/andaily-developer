package com.andaily.infrastructure.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Wrapper <i>common-log</i>  action
 *
 * @author Shengzhao Li
 */
public class LogHelper {

    public static final String LOG_DELIMITER = "|";

    private final Log log;

    public LogHelper(Class clazz) {
        this.log = LogFactory.getLog(clazz);
    }

    public LogHelper(String name) {
        this.log = LogFactory.getLog(name);
    }

    /**
     * Convenient create <code>LogHelper</code>.
     *
     * @param clazz class
     * @return LogHelper
     */
    public static LogHelper create(Class clazz) {
        return new LogHelper(clazz);
    }

    /**
     * Convenient create <code>LogHelper</code>.
     *
     * @param name Logger name
     * @return LogHelper
     */
    public static LogHelper create(String name) {
        return new LogHelper(name);
    }

    public void info(Object obj) {
        if (log.isInfoEnabled()) {
            log.info(obj);
        }
    }

    public void info(Object obj, Throwable throwable) {
        if (log.isInfoEnabled()) {
            log.info(obj, throwable);
        }
    }

    public void debug(Object obj) {
        if (log.isDebugEnabled()) {
            log.debug(obj);
        }
    }

    public void debug(Object obj, Throwable throwable) {
        if (log.isDebugEnabled()) {
            log.debug(obj, throwable);
        }
    }

    public Log getLog() {
        return log;
    }

    public void error(Object obj, Throwable throwable) {
        if (log.isErrorEnabled()) {
            log.error(obj, throwable);
        }
    }

    public void warn(Object obj) {
        if (log.isWarnEnabled()) {
            log.warn(obj);
        }
    }

    public void warn(Object obj, Throwable throwable) {
        if (log.isWarnEnabled()) {
            log.warn(obj, throwable);
        }
    }
}
