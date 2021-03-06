package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;

public class LoginPage
{
    WebDriver ldriver;

    public LoginPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(xpath="//input[@name='uid']")
    WebElement txtUserName;

    @FindBy(name="password")
    WebElement txtPassword;

    @FindBy(name="btnLogin")
    WebElement btnLogin;

    @FindBy(xpath = "//a[.='Log out']")
    WebElement btnLogout;

    public void setUserName(String uname)
    {
        txtUserName.sendKeys(uname);
    }

    public void setPassword(String pwd)
    {
        txtPassword.sendKeys(pwd);
    }

    public void clickSubmit()
    {
        btnLogin.click();
    }
    public void clickLogout()
    {
        btnLogout.click();
    }

}
