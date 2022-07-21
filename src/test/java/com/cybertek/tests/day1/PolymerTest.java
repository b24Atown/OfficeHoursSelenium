package com.cybertek.tests.day1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class PolymerTest {

    public static void main(String[] args) {

        //write a basic browser automation framework to validate a Polymer website. The focus should be on the interaction with the browser. The web application under test http://todomvc.com/
        //The first step should be to load the website, click within the Javascript tab, and select the Polymer link. The second step should be: Add one Todo item then verify that the item is added.

        String url = "http://todomvc.com/";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);

        //<div class="tab-content flex-auto center-center horizontal layout style-scope paper-tab">JavaScript</div>
        WebElement javascriptTab = driver.findElement(By.xpath("//div[@class='tab-content flex-auto center-center horizontal layout style-scope paper-tab'][1]"));
        javascriptTab.click();

        WebElement polymerLink = driver.findElement(By.xpath("//a[.='Polymer']"));
        polymerLink.click();

        WebElement addBox = driver.findElement(By.id("new-todo"));
        String todoItemOne = "Office Hours for B24";
        addBox.sendKeys(todoItemOne+ Keys.ENTER);
        String locatorForAddedItem = "//label[.='"+todoItemOne+"']";
        //verify that added item

        System.out.println(driver.findElement(By.xpath(locatorForAddedItem)).isDisplayed());


    }
}
