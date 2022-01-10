package structuralCode.testScript;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import structuralCode.utility.ActionReUsable;
import structuralCode.utility.Assertions;

public class HomePage {
    Assertions assertions = new Assertions();
    ActionReUsable actionReUsable = new ActionReUsable();

    @BeforeTest
    public void setup() {
        actionReUsable.getPropertyFile("HomePage.properties");
        actionReUsable.launch_Browser(actionReUsable.properties.getProperty("Home_Page_Url"));
    }
    @Test
    public void homepage() throws InterruptedException {
        actionReUsable.getPropertyFile("HomePage.properties");
        WebElement login = actionReUsable.getElement(By.xpath(actionReUsable.properties.getProperty("Login_Button")));
        actionReUsable.click(login);
        if (assertions.verifyPageTitle("Dashboard / nopCommerce administration")) {
            WebElement searchBox = actionReUsable.getElement(By.xpath(actionReUsable.properties.getProperty("SearchBox")));
            WebElement salesButton = actionReUsable.getElement(By.xpath(actionReUsable.properties.getProperty("Sales")));
            actionReUsable.enterText(searchBox, actionReUsable.properties.getProperty("SearchText"));
            Thread.sleep(2000);
            actionReUsable.click(salesButton);
        } else {
            System.out.println("its not the actual page");
        }
    }
    @AfterTest
    public void tearDown(){
        actionReUsable.driver.quit();
    }
}
