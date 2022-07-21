package com.cybertek.tests.day3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TotalOrderPriceCalculation1 {
    /*Test Case 1:
Total Order Price Calculation 1
- Go to:  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
- Login with username: Tester, password: test
- Click  Order button
- Verify under Product Information, selected option is “MyMoney”
-Then select FamilyAlbum, make quantity 2, and click Calculate,
- Then verify Total is equal to Quantity*PricePerUnit
     */
    String url = "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx";
    public static WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void priceCalculation1() throws InterruptedException {
        String expectedTitle = "Web Orders Login";
        Assert.assertEquals(driver.getTitle(),expectedTitle, "FAIL: Wrong page");
        login("Tester","test");
        sideTab("Order").click();
        Select productSelection = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
        String expectedSelection = "MyMoney";
        Assert.assertEquals(productSelection.getFirstSelectedOption().getText(),expectedSelection, "FAIL: wrong selection");
        productSelection.selectByVisibleText("FamilyAlbum");
       String quantityEnter = "2";
       productInfo("Quantity").sendKeys(Keys.DELETE + quantityEnter);
       WebElement calculate = driver.findElement(By.xpath("//input[@value='Calculate']"));
       calculate.click();
       int total = Integer.parseInt(productInfo("Total").getAttribute("value"));
       int unitValue = Integer.parseInt(productInfo("Unit").getAttribute("value"));
       Assert.assertTrue(total == Integer.parseInt(quantityEnter)*unitValue,"FAIL: wrong total");

    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public static void login(String username, String password){
      WebElement usernameInput = driver.findElement(By.id("ctl00_MainContent_username"));
       usernameInput.sendKeys(username);
       WebElement passwordInput = driver.findElement(By.id("ctl00_MainContent_password"));
       passwordInput.sendKeys(password + Keys.ENTER);
    }
    public static WebElement sideTab(String text){
        return driver.findElement(By.xpath("//a[.='"+text+"']"));
    }

    public static WebElement productInfo(String text){
        return driver.findElement(By.xpath("//input[contains(@id,'"+text+"')]"));

    }
}
