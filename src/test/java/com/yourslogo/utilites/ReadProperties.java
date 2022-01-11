package com.yourslogo.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties
{
    Properties properties;
    public ReadProperties()
    {
        File src = new File("./Configuration/yourslogo.properties");
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
    public String searchContent() {
        String search = properties.getProperty("searchContent");
        return search;
    }
    public String ListOfProducts()
    {
        String proList = properties.getProperty("productsListXpath");
        return proList;
    }
    public String ListOfPrices()
    {
        String proPrices = properties.getProperty("pricesList");
        return proPrices;
    }
}
