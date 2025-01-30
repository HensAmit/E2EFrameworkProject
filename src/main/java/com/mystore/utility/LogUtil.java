package com.mystore.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;

public class LogUtil {
    private static final Logger logger = (Logger) LogManager.getLogger(LogUtil.class);
    private static final String LOGS_DIRECTORY = System.getProperty("user.dir") + "/logs/";

    private LogUtil() {
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void cleanOldLogs() {
        try {
            // Close Log4j to release file locks
            LoggerContext context = (LoggerContext) LogManager.getContext(false);
            context.close();
            File logDir = new File(LOGS_DIRECTORY);
            if (logDir.exists()) {
                for (File file : Objects.requireNonNull(logDir.listFiles())) {
                    Files.deleteIfExists(file.toPath());
                }
            }
            context.reconfigure();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to delete old log files.");
        }
    }
}