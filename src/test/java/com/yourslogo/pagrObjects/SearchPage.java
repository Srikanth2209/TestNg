package com.yourslogo.pagrObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage
{
    WebDriver ldriver;

    public SearchPage(WebDriver rdriver)
    {
        ldriver=rdriver;
        PageFactory.initElements(rdriver,this);
    }

    @FindBy(xpath="//input[@id='search_query_top']")
    WebElement searchContent;

    @FindBy(xpath = "//button[@name='submit_search']")
    WebElement searchButton;

    public void setContent(String uname)
    {
        searchContent.sendKeys(uname);
    }
    public  void clickSearchButton()
    {
        searchButton.click();
    }
}

