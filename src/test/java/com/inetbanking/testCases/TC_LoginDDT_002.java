package com.inetbanking.testCases;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtility;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.inetbanking.utilities.ReadConfig;

import java.io.IOException;

public class TC_LoginDDT_002 extends BaseClass
{
    ReadConfig readconfig = new ReadConfig();
    @Test(dataProvider = "LoginData")
    public void loginDDT(String username, String pwd)
    {
        driver.get(readconfig.getApplicationUrl());
        logger.info("Url is Opened");

        LoginPage lp = new LoginPage(driver);
        lp.setUserName(username);
        logger.info("username entered");
        lp.setPassword(pwd);
        logger.info("password entered");
        lp.clickSubmit();

        if(isAlertPresent()==true)
        {
            driver.switchTo().alert().accept();// wrong credentials alert
            driver.switchTo().defaultContent();
            Assert.assertTrue(false);
            logger.warn("Login failed");
        }else{
            Assert.assertTrue(true);
            logger.info("Login passed");
            lp.clickLogout();
            driver.switchTo().alert().accept();//logout alert
            driver.switchTo().defaultContent();
        }
    }

    @DataProvider(name = "LoginData")
    Object[][] getData() throws IOException {

        String path = "D:\\FileReaders\\LoginData.xls";
        //String path = System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xls";
        XLUtility xlutil = new XLUtility(path);

        int totalrows = xlutil.getRowCount("sheet1");
        int totalcols = xlutil.getCellCount("sheet1", 1);

        String loginData[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                loginData[i - 1][j] = xlutil.getCellData("sheet1", i, j);
            }
        }
        return loginData;
    }
}
