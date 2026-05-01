package org.example.demotestautomation.smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ATagsTest {
    //private WebDriver driver; break in parallel execution

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // improved for parallel
    private String webURL= "https://workspace.google.com/intl/en-US/gmail/";

    @BeforeMethod
    public void setUp(){
        // driver = new FirefoxDriver();
        driver.set(new FirefoxDriver()); // improved version for parallel
    }

    // to get the driver with thread version
    private WebDriver getDriver(){
        return driver.get();
    }
    @Test
    public void pageTitle(){

        getDriver().get(webURL);
        List<WebElement> list = getDriver().findElements(By.tagName("a"));

        // for  version
        /*
        for(WebElement element: list){
            System.out.println(element.getText());
        }*/
        // for each version
        /*
        list.forEach(element -> {
            if (!element.getText().equals("")) {
                System.out.println(element.getText());
            }
            });

         */
        boolean found = false;

        /*list.forEach(element -> {
            if (element.getText().equals("carlosjaid")) {
                found = true;
            }
        });*/

        for(WebElement element: list){
            if(element.getText().equals("For worksss")){
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "Not Founded");
    }

    @AfterMethod
    // we use drive quit() to
    public void tearDown(){

        getDriver().quit();
        //driver.close(); // only closes tab
        driver.remove(); // important cleanup for ThreadLocal
    }
}
