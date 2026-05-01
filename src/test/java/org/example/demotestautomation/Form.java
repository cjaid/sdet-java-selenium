package org.example.demotestautomation;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.SQLOutput;

public class Form {
    public static void main(String[] args){
        // Open the browser and go to gmail to create an account
        WebDriver driver = new FirefoxDriver();
        driver.get("https://workspace.google.com/intl/en-US/gmail/");

        // clic on create an account
        // xpath
        //driver.findElement(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/summary/span[1]")).click();
        // class
        //driver.findElement(By.className("button-label")).click();

        // css selector
        //driver.findElement(By.cssSelector("span[class='button-label']")).click();

        //dropdownlist
        //new Select(driver.findElement(By.cssSelector("nav[class='dropdown-menu']"))).selectByVisibleText("For my personal use");

        driver.findElement(By.cssSelector("span[class='button-arrow']")).click();

        //linkText
        driver.findElement(By.linkText("For my personal use")).click();

        //assert
        String subheaderText = driver.findElement(By.id("headingSubtext")).getText();
        if(subheaderText.equals("Enter your name")){
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }

        // Close the browser
        //driver.quit();

    }
}
