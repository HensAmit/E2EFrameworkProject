package com.mystore.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {
    private ReadPropertyFile () {};

    public static Properties loadProperties () {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\Configs\\config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
