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

        SearchPage sp = new SearchPage(driver);
        sp.setContent(readProperties.searchContent());
        sp.clickSearchButton();

        List<WebElement> productsList = driver.findElements(By.xpath(readProperties.ListOfProducts()));
        List<WebElement> pricesList = driver.findElements(By.xpath(readProperties.ListOfPrices()));

        checkDisplayedProducts(productsList,readProperties.searchContent());

        //Use of HashMap to store Products and Their prices(after conversion to Integer)
        HashMap<Integer, String> map_final_products = new HashMap<Integer, String>();
        for (int i = 0; i < productsList.size(); i++) {
            String product_name = productsList.get(i).getText();
            String product_price = pricesList.get(i).getText();
            String updated_price = product_price.replaceAll("[^0-9]", "");
            int product_price_integer = Integer.parseInt(updated_price);
            map_final_products.put(product_price_integer, product_name);
        }

        Set<Integer> keys = map_final_products.keySet();
        ArrayList<Integer> sorted_Prices = new ArrayList<Integer>(keys);

        Collections.sort(sorted_Prices);

        int low_price = sorted_Prices.get(0);
        System.out.println("Low Product Price is: " + low_price + " Product name is: " + map_final_products.get(low_price));
    }
}

