package com.saucedemo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* This file is about reading the data from config.properties */
public class ConfigReader {
    private static Properties properties;
    private static final String CONFIG_PATH = "resources/config.properties";
    //ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_PATH);
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("Property '" + key + "' not found.");
        return value.trim();
    }

    public static String getBaseUrl() {         
        return get("baseUrl"); 
    }
    public static String getBrowser() { 
        return get("browser"); 
    }
    public static int getImplicitWait(){ 
        return Integer.parseInt(get("implicitWait")); 
    }
    public static int getExplicitWait() { 
        return Integer.parseInt(get("explicitWait")); 
    }
    public static boolean isHeadless() { 
        return Boolean.parseBoolean(get("headless"));
     }
}