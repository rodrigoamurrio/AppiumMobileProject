package org.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    private Properties properties;

    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = new FileInputStream("src/test/resources/setUp/init.properties")) {
                // load a properties file
                properties.load(input);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return properties;
    }

    public String getUrlApplication(){
        return getProperties().getProperty("url");
    }

    public String getDeviceName(){
        return getProperties().getProperty("deviceName");
    }

    public String getDeviceUdUi(){
        return getProperties().getProperty("udui");
    }

    public String getDevicePlatformName(){
        return getProperties().getProperty("platformName");
    }

    public String getDevicePlatformVersion(){
        return getProperties().getProperty("platformVersion");
    }

    public File getChromeDriverPath(){
        return new File(getProperties().getProperty("chromeDriverPath"));
    }
}
