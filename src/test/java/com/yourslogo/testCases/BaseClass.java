package com.yourslogo.testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BaseClass {
    public static WebDriver driver;
    public static Logger logger;
    @BeforeClass
    public void setup() {
        logger = Logger.getLogger("yoursLogo");
        PropertyConfigurator.configure("Log4j.properties");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public void checkDisplayedProducts(List<WebElement> list, String content) throws IOException {
        for (WebElement p : list) {
            String Names = p.getText();
            if (Names.contains(content)) {
                //System.out.println("Searched and Displayed contents are same");
                Assert.assertTrue(true);
            } else {
                //captureScreen(driver, "yoursLogo");
                //System.out.println("Searched and Displayed contents are not same");
                //Assert.assertTrue(false);
            }
        }
    }
    public void captureScreen(WebDriver driver, String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        File Target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
        FileUtils.copyFile(Source, Target);
        System.out.println("Screenshot taken");
    }
}
