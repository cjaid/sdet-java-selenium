package org.example.demotestautomation.smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PageTitleTestNG {

    private WebDriver driver;
    private String webURL= "https://workspace.google.com/intl/en-US/gmail/";

    @BeforeMethod
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Test
    public void pageTitle(){

        driver.get(webURL);
        WebElement forWorkButton = driver.findElement(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/span[1]/a"));
        WebElement signInButton = driver.findElement(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/span[3]/a"));

        boolean forWork = forWorkButton.isDisplayed();
        boolean signIn = signInButton.isDisplayed();

        Assert.assertTrue(forWork, "button for work exists");
        Assert.assertTrue(signIn, "button sign in exists");

    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
