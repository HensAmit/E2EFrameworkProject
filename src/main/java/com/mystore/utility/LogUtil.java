package com.mystore.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LogUtil {
    private static final Logger logger = (Logger) LogManager.getLogger(LogUtil.class);

    private LogUtil() {
    }

    public static Logger getLogger() {
        return logger;
    }
}