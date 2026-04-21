package org.example.demotestautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.ui.WebDriverWait;
//import org.openqa.selenium.ui.ExpectedConditions;

public class GoogleTest {
    // version 1
    /*
    public static void main(String[] args) {

        // 1. Open browser
        WebDriver gDriver = new ChromeDriver();

        // 2. Open Google Chrome
        gDriver.get("https://www.google.com.mx");

        // 3. Find Search box
        WebElement searchBox = gDriver.findElement(By.id("APjFqb"));

        // 4. Type text
        searchBox.sendKeys("SDET jobs in Mexico City");

        // 5. Submit the search
        searchBox.submit();

        // 6. Wait a bit, we will improve this later
        try{
            Thread.sleep(30000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        // 7. Read data from page
        System.out.println("Data: " + gDriver.getTitle());
        
        // 8. Quit the browser
        gDriver.quit();
    }
    */
    // version 2
    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com.mx");

        // Wait setup
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find search box
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));

        searchBox.sendKeys("Amazon SDET jobs");
        searchBox.submit();

        // Wait for results page title to contain keyword
        wait.until(ExpectedConditions.titleContains("SDET"));

        // Read data: first assertion mindset
        if(driver.getTitle().contains("Amazon")){
            System.out.println("Test passed");
        }{
            System.out.println("Test failed");
        }



        driver.quit();
    }
}