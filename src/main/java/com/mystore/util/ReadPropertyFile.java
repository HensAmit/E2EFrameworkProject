package com.mystore.util;

import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {
    private ReadPropertyFile () {}

    public static Properties loadProperties () {
        Properties properties = new Properties();
        try (InputStream fis = ResourceLoader.getResourceAsStream("configs/config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
