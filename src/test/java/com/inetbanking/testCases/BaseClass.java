package com.inetbanking.testCases;

import com.beust.jcommander.Parameter;
import com.inetbanking.utilities.ReadConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class BaseClass
{
    public static WebDriver driver;
    public static Logger logger;
    @Parameters("browser")
    @BeforeClass
    public void setup(String browser)
    {
        logger = Logger.getLogger("ebanking");
        PropertyConfigurator.configure("Log4j.properties");

        /*WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();*/

        if(browser.equals("chrome"))
        {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        }
        else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        else if (browser.equals("ie")){
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        }

    }
    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }

    public void captureScreen(WebDriver driver,String tname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File Source = ts.getScreenshotAs(OutputType.FILE);
        File Target = new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
        FileUtils.copyFile(Source,Target);
        System.out.println("Screenshot taken");
    }
    public boolean isAlertPresent()
    {
        try{
            driver.switchTo().alert();
            return true;
        }catch(NoAlertPresentException e){
            return false;
        }
    }
}
