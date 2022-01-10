package structuralCode.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ActionReUsable
{
    public static WebDriver driver;
    public static Properties properties;
    /**
     * This method is to check whether the element is present or not
     *        Returns: true,if found
     *        false, if not found
     * @param element
     * @return
     */
    public boolean isElementsPresent( WebElement element)
    {
        if(element.isDisplayed()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * This method is to check the element is present or
     *  not and enter values in TextBox
     * @param element
     * @param textValue
     */
    public void enterText(WebElement element, String textValue){

        if(isElementsPresent(element)){
            element.sendKeys(textValue);
        }else {
            System.out.println("WebElement is not found ");
        }
    }
    /**
     * This method is to check the element is present or
     *     not and perform click operation on element
     * @param element
     */
    public void click(WebElement element){
        if(isElementsPresent(element)){
            element.click();
        }else {
            System.out.println("WebElement is not found");
        }
    }
    /**
     * This method is to check whether the checkbox is checked or not
     *       Returns: true,if it is checked
     *       false, if it is unchecked
     * @param element
     * @return
     */
    public boolean isChecked(WebElement element) {
        if (isElementsPresent(element)) {
            element.isSelected();
            return true;
        } else {
            return false;
        }
    }
    public WebElement getElement(By locator) {
        return driver.findElement(locator);
    }
    /**
     * This method is to launch the browse and open the url
     * @param url
     */

    public void launch_Browser(String url) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(url);
    }
    public void navigate_To(String url){
        driver.navigate().to(url);
    }

    /**
     * This method is to get title of the page
     * @return
     */
    public String title() {
        return driver.getTitle();
    }
    /**
     * This method is to read the data in property file
     *       will provide the path of the file
     * @param path
     */
    public void getPropertyFile(String path){
        //String FilePath = System.getProperty("user.dir");
        properties= new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            System.out.println("File is not found in the given path");
        }
    }
    /**
     * This method is to get total number of links present in webPage
     */
    public void getLinksCount() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("The count of links is: " + links.size());
    }
    /**
     * This method is to get linkTexts of links present in webPage
     */
    public void getLinkText() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String linkText = link.getText();
            System.out.println(linkText);
        }
    }
    /**
     * This method is to get all links on particular webpage
     */
    public void getAllLinks() {
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String LinksNames = link.getAttribute("href");
            System.out.println(LinksNames);
        }
    }
    /**
     * This method is to check the element is present or not
     *        if element present select the element with visible text
     * @param element
     * @param visibleText
     */
    public void doSelectByVisibleText(WebElement element,String visibleText)
    {
        if(isElementsPresent(element)){
            new Select(element).selectByVisibleText(visibleText);
        }else{
            System.out.println("WebElement is not found ");
        }
    }
    /**
     * This method is to conform dialogBox if it is present
     */
    public void conformDialogBox()
    {
        Alert ConfAlert = driver.switchTo().alert();
        ConfAlert.accept();
    }
    /**
     * This method is to cancel dialogBox if it is present
     */
    public void cancelDialogBox() {
        Alert CancelAlert = driver.switchTo().alert();
        CancelAlert.dismiss();
    }
}
