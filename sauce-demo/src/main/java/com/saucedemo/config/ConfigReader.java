package com.saucedemo.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* This file is about reading the data from config.properties */
public class ConfigReader {
   private static Properties properties;

static {
    try {
        // Use ClassLoader to load from classpath (proper for JAR/Maven)
        var resourceStream = ConfigReader.class.getClassLoader()
            .getResourceAsStream("config.properties");
        
        if (resourceStream == null) {
            throw new IOException("config.properties not found in classpath");
        }
        
        properties = new Properties();
        properties.load(resourceStream);
        resourceStream.close(); 
        
    } catch (IOException e) {
        throw new RuntimeException("Failed to load config.properties: " + e.getMessage(), e);
    }
}

public static String get(String key) {
    // System property (e.g. -Dbrowser=firefox) always wins over config.properties
    String systemValue = System.getProperty(key);
    if (systemValue != null && !systemValue.isBlank()) {
        return systemValue.trim();
    }

    String value = properties.getProperty(key);
    if (value == null) {
        throw new IllegalArgumentException("Property '" + key + "' not found in config.properties");
    }
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