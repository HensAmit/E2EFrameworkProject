package com.mystore.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
    private ReadPropertyFile () {}
    private static final Logger logger = (Logger) LogManager.getLogger(ReadPropertyFile.class);

    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream fis = ResourceLoader.getResourceAsStream("configs/config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            logger.error("Failed in loadProperties() - ", e);
            throw new RuntimeException("Failed in loadProperties() - ", e);
        }
        return properties;
    }
}
