package com.cybertek.tests.day1;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class PolymerTest2 {

    //write a basic browser automation framework to validate a Polymer website. The focus should be on the interaction with the browser. The web application under test http://todomvc.com/
    //        //The first step should be to load the website, click within the Javascript tab, and select the Polymer link. The second step should be: Add one Todo item then verify that the item is added.

    String url = "http://todomvc.com/";
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
    public void polymerTest(){
        getTab("JavaScript").click();
        getLink("Polymer").click();
        WebElement input = driver.findElement(By.id("new-todo"));
        String word = "Helloo";
        input.sendKeys(word + Keys.ENTER);
        WebElement enteredWord = driver.findElement(By.xpath("//label[.='"+word+"']"));
        Assert.assertEquals(enteredWord.getText(),word,"The word is displayed incorrectly");


    }

    public static WebElement getTab(String tab){
        return driver.findElement(By.xpath("//div[.='"+tab+"']"));
    }

    public static WebElement getLink(String link){
        return driver.findElement(By.linkText(link));
    }
}
