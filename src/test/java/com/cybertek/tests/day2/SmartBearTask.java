package com.cybertek.tests.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SmartBearTask {
    /*
    Test Case 1:
    Basic login authentication
    -Go to: http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
    verify title equals: String expectedTitle = "Web Orders Login"
    Enter username: Tester
    Enter password test
    Click "Sign In" button
    Verify title equals: String expectedHomePageTitle = "Web Orders"
    Test Case 2:
    After login
    click on check All button
    verify that all check buttons are selected
    click on uncheck all button
    select one of the check box and delete thah row
    verify that row of information is deleted.
     */

   public static WebDriver driver;
    String url = "http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx";

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

    @Test
    public void TC01(){
        //get page title before login
        String expectedTitle = "Web Orders Login";
        Assert.assertEquals(driver.getTitle(),expectedTitle);
        WebElement username = driver.findElement(By.id("ctl00_MainContent_username"));
        username.sendKeys("Tester");
        WebElement password = driver.findElement(By.id("ctl00_MainContent_password"));
        password.sendKeys("test" + Keys.ENTER);
        String expTitleAfterLogin = "Web Orders";
        Assert.assertEquals(driver.getTitle(),expTitleAfterLogin);

    }

    @Test
    public void TC02(){
        getLink("Check All").click();
        List<WebElement> links = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement each: links){
            Assert.assertTrue(each.isSelected());
        }
        getLink("Uncheck All").click();

        links = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for (WebElement each: links){
            Assert.assertTrue(!each.isSelected(),each+"is selected");
        }
        int expectedSize = 8;
        Assert.assertTrue(links.size()==expectedSize);
        //links.get(0).click(); but not reusable.
        selectItem("Paul Brown").click();
        WebElement deleteButton = driver.findElement(By.xpath("//input[@value='Delete Selected']"));
        deleteButton.click();
        int expectedSizeAfterDeletion = 7;

        links = driver.findElements(By.xpath("//input[@type='checkbox']"));

        Assert.assertTrue(links.size() == expectedSizeAfterDeletion,"Size is wrong");



    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public static WebElement getLink(String link){
        return driver.findElement(By.linkText(link));
    }

    public static WebElement selectItem(String itemName){
        return driver.findElement(By.xpath("//td[.='"+itemName+"']/../td[1]/input"));
    }
}
