package com.inetbanking.testCases;
import com.inetbanking.utilities.ReadConfig;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

import java.io.IOException;

public class TC_AddCustomerTest_003 extends BaseClass
{
    ReadConfig readconfig = new ReadConfig();
    @Test
    public void addNewCustomer() throws InterruptedException, IOException, IOException {
        LoginPage lp=new LoginPage(driver);
        driver.get(readconfig.getApplicationUrl());
        logger.info("Url is Opened");
        lp.setUserName( readconfig.getUsername());
        logger.info("User name is provided");
        lp.setPassword(readconfig.getPassword());
        logger.info("Passsword is provided");
        lp.clickSubmit();

        Thread.sleep(3000);

        AddCustomerPage addcust=new AddCustomerPage(driver);

        addcust.clickAddNewCustomer();

        logger.info("providing customer details....");


        addcust.custName("Pavan");
        addcust.custgender("male");
        addcust.custdob("10","15","1985");
        Thread.sleep(5000);
        addcust.custaddress("INDIA");
        addcust.custcity("HYD");
        addcust.custstate("AP");
        addcust.custpinno("5000074");
        addcust.custtelephoneno("987890091");

        String email=randomestring()+"@gmail.com";
        addcust.custemailid(email);
        addcust.custpassword("abcdef");
        addcust.custsubmit();

        Thread.sleep(3000);

        logger.info("validation started....");

        boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

        if(res==true)
        {
            Assert.assertTrue(true);
            logger.info("test case passed....");

        }
        else
        {
            logger.info("test case failed....");
            captureScreen(driver,"addNewCustomer");
            Assert.assertTrue(false);
        }

    }

}
