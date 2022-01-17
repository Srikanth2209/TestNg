package com.yourslogo.testCases;

import com.yourslogo.pagrObjects.SearchPage;
import com.yourslogo.utilites.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

public class TC_HomePage_001 extends BaseClass {
    ReadProperties readProperties = new ReadProperties();

    @Test
    public void searchPage() throws IOException {
        driver.get(readProperties.getApplicationUrl());
        logger.info("chrome invoked and navigated to url");
        SearchPage sp = new SearchPage(driver);
        sp.setContent(readProperties.searchContent());
        sp.clickSearchButton();
        logger.info("product search success");

        List<WebElement> productsList = driver.findElements(By.xpath(readProperties.listOfProducts()));
        List<WebElement> pricesList = driver.findElements(By.xpath(readProperties.listOfPrices()));

        checkDisplayedProducts(productsList,readProperties.searchContent());
        logger.info("searched and displayed products are compared");

        HashMap<Double, String> map = new HashMap<Double, String>();
        for (int i = 0; i < productsList.size(); i++) {
            String product_name = productsList.get(i).getText();
            String product_price = pricesList.get(i).getText();
            String updated_price = product_price.replace("$", "");
            Double product_price_integer = Double.parseDouble(updated_price);
            map.put(product_price_integer, product_name);
        }
        logger.info("product_price and product_names are pushed to HashMap");
        Set<Double> keys = map.keySet();
        ArrayList<Double> sorted_Prices = new ArrayList<Double>(keys);
        Collections.sort(sorted_Prices);
        Double low_price = sorted_Prices.get(0);
        System.out.println("Low Product Price is: " + low_price + " Product name is: " + map.get(low_price));
        logger.info("lowest product price and correspondent product details are extracted");
    }
}

