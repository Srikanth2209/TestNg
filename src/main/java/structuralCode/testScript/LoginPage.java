package structuralCode.testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import structuralCode.utility.ActionReUsable;
import structuralCode.utility.Assertions;
public class LoginPage
{
    Assertions assertions = new Assertions();
    ActionReUsable actionReUsable = new ActionReUsable();
    @BeforeTest
    public void setup(){
        actionReUsable.getPropertyFile("Login.properties");
        actionReUsable.launch_Browser(actionReUsable.properties.getProperty("Page_Url"));
    }
    @Test
    /*Testcase description: login to the page and verify that landed on expected page or not*/
    public void login() {
        actionReUsable.getPropertyFile("Login.properties");
        WebElement email = actionReUsable.getElement(By.id(actionReUsable.properties.getProperty("email")));
        WebElement password = actionReUsable.getElement(By.id(actionReUsable.properties.getProperty("password")));
        WebElement login = actionReUsable.getElement(By.xpath(actionReUsable.properties.getProperty("Login_Button")));
        email.clear();
        actionReUsable.enterText(email,actionReUsable.properties.getProperty("Login_UserName"));
        password.clear();
        actionReUsable.enterText(password,actionReUsable.properties.getProperty("Login_Password"));
        actionReUsable.click(login);
        assertions.verifyPageTitle("Dashboard / nopCommerce administration");
        actionReUsable.getLinksCount();
    }
    @AfterTest
    public void tearDown(){
        actionReUsable.driver.quit();
    }

}
