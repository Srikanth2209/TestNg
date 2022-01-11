package com.yourslogo.testCases;

import com.yourslogo.utilites.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.List;

public class BaseClass {
    public static WebDriver driver;
    ReadProperties readProperties = new ReadProperties();

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void checkDisplayedProducts(List<WebElement>list) {
        for (WebElement p : list) {
            String Names = p.getText();
            if (Names.contains("Printed Summer Dress")) {
                System.out.println("Searched and Displayed contents are same");
            } else {
                System.out.println("Searched and Displayed contents are not same");
            }
        }
    }
    /*public HashMap<List,List>items (String items) {
        for (int i = 0; i < namesList.size(); i++) {
            String product_name = namesList.get(i).getText();
            String product_price = priceList.get(i).getText();
            String updated_price = product_price.replaceAll("[^0-9]", "");
            int product_price_integer = Integer.parseInt(updated_price);
            //map_final_products.put(product_price_integer, product_name);
            return product_price_integer,product_name;
        }
    }*/
}
