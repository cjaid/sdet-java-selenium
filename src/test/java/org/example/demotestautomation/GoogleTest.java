package org.example.demotestautomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {
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
}