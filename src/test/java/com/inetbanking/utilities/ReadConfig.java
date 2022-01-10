package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig
{
    Properties properties;
    public ReadConfig(){
        File src = new File("./Configuration/config.properties");
        properties= new Properties();
        try {
            properties.load(new FileInputStream(src));
        } catch (IOException e) {
            System.out.println("File is not found in the given path");
        }
    }

    public String getApplicationUrl()
    {
        String url = properties.getProperty("baseUrl");
        return url;
    }
    public String getUsername()
    {
        String username = properties.getProperty("username");
        return username;
    }
    public String getPassword()
    {
        String password = properties.getProperty("password");
        return password;
    }
}
