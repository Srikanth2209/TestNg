package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.ReadConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_LoginTest_001 extends BaseClass
{
    ReadConfig readconfig = new ReadConfig();
    @Test
    public void loginTest() throws IOException {
        driver.get(readconfig.getApplicationUrl());
        logger.info("Url is Opened");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName( readconfig.getUsername());
        logger.info("UserName is entered");

        lp.setPassword(readconfig.getPassword());
        logger.info("Password is entered");

        lp.clickSubmit();

        if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
        {
            Assert.assertTrue(true);
            logger.info("Login test passed");
        }else {
            captureScreen(driver,"LoginTest");
            Assert.assertTrue(false);
            logger.info("Login test failed");
        }

    }
}
