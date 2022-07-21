package com.cybertek.tests.day3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TotalOrderPriceCalculation2 {
    /*Test Case 2:
Total Order Price Calculation 2
 - Go to https://www.demoblaze.com/index.html#
 - From Categories select Laptops, and from products select Sony Vaio i7
 - click Add to Cart then handle pop up
 - Navigate to Home
  - From Categories select Phones, and from products select Iphone 6 32gb
 - click Add to Cart then handle pop up
 - Navigate to Cart
- Then Verify that total cart price is equal to expected (total of added 2 items) price
     */
    String url = "https://www.demoblaze.com/index.html#";
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
    public void TC02() throws InterruptedException {
        getLink("Laptops").click();
        getLink("Sony vaio i7").click();
        getLink("Add to cart").click();
        Thread.sleep(2000);
        Alert popup = driver.switchTo().alert();
        popup.accept();
        WebElement home = driver.findElement(By.xpath("//li[@class='nav-item active']"));
        home.click();
        getLink("Phones").click();
        getLink("Iphone 6 32gb").click();
        getLink("Add to cart").click();
        Thread.sleep(2000);
        Alert popup2 = driver.switchTo().alert();
        popup2.accept();
        getLink("Cart").click();
        Thread.sleep(2222);
        String actualTotal = driver.findElement(By.tagName("h3")).getText();
        int total = 0;

        List<WebElement> price = driver.findElements(By.xpath("//table//td[3]"));

        for (WebElement each : price) {
            total+= Integer.parseInt(each.getText());
        }
        Assert.assertTrue(Integer.parseInt(actualTotal)==total,"FAIL:Price Missmatch");

    }

    @AfterClass
    public void tearDown(){
        //driver.quit();
    }

    public static WebElement getLink(String word){
        return driver.findElement(By.linkText(word));
    }
    //could have also done it like this
    /*
    public static void getLink(String linkText){
    driver.findElement(By.linkText(linkText)).click();
    }
     */

    public static int addProduct(String category, String product){
        return 3;
    }

}
