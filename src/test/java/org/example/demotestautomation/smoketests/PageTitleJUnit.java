package org.example.demotestautomation.smoketests;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageTitleJUnit {

    private WebDriver driver;
    private String webURL= "https://workspace.google.com/intl/en-US/gmail/";

    @BeforeEach
    public void setUp(){
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().window().fullscreen();
        }
        System.out.println("Broser opened");
    }
    @Test
    public void pageTitle(){

        System.out.println("Running the test");


        // Open gmail US
        driver.get(webURL);

        String expectedNamePageTitle = "Gmail: Secure, AI-Powered Email for Everyone | Google Workspace";
        String namePageTitle = driver.getTitle();

        // With assertions we don't use if else conditions to say if the test pass or fail
        Assertions.assertEquals(expectedNamePageTitle, namePageTitle);
        // Fails the test
        //Assertions.fail("By default");


    }

    @AfterEach
    public void tearDown(){

        if(driver != null)
            driver.quit();

        System.out.println("Browser closed");


    }
}
