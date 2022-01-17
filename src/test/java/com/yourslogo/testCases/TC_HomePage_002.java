package com.yourslogo.testCases;

import com.yourslogo.pagrObjects.SearchPage;
import com.yourslogo.utilites.ReadProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

public class TC_HomePage_002 extends BaseClass
{
    ReadProperties readProperties = new ReadProperties();
    @Test
    public void searchPage() throws IOException
    {
        driver.get(readProperties.getApplicationUrl());

        SearchPage sp = new SearchPage(driver);
        sp.setContent(readProperties.searchContent());
        sp.clickSearchButton();

        List<WebElement> productsList = driver.findElements(By.xpath(readProperties.listOfProducts()));
        //List<WebElement> pricesList = driver.findElements(By.xpath(readProperties.listOfPrices()));
        //List<WebElement> stock = driver.findElements(By.xpath(readProperties.stockUpdate()));
        checkDisplayedProducts(productsList,readProperties.searchContent());

        HashMap<Double, String> map_final_products = new HashMap<>();
        for(int i=1;i<=productsList.size();i++)
        {
            String item =driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[2]/h5/a")).getText();
            if(item.contains(readProperties.searchContent()))
            {
                String price = driver.findElement(By.xpath("//ul[@class='product_list grid row']/li["+i+"]/div/div[2]/div[1]/span[1]")).getText();
                price = price.replace("$","");
                double updatePrice = Double.parseDouble(price);
                map_final_products.put(updatePrice,item);
            }
        }
        Set<Double> keys = map_final_products.keySet();
        ArrayList<Double> sorted_Prices = new ArrayList<Double>(keys);

        Collections.sort(sorted_Prices);

        Double low_price = sorted_Prices.get(0);
        System.out.println("Low Product Price is: " + low_price + " Product name is: " + map_final_products.get(low_price));
    }
}
