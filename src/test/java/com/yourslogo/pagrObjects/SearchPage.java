package com.yourslogo.pagrObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage
{
    WebDriver driver;
    public SearchPage(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//input[@id='search_query_top']")
    WebElement searchBox;

    @FindBy(xpath = "//button[@name='submit_search']")
    WebElement searchButton;

    public boolean isElementsPresent( WebElement element)
    {
        if(element.isDisplayed()){
            return true;
        }else {
            return false;
        }
    }
    public void setContent(String uname)
    {
        if(isElementsPresent(searchBox)){
            searchBox.sendKeys(uname);
        }else{
            System.out.println("searchBox not present");
        }
    }
    public  void clickSearchButton()
    {
        if(isElementsPresent(searchButton)){
        searchButton.click();
        }else{
            System.out.println("searchButton not present ");
        }
    }
}

