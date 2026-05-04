package org.example.demotestautomation.smoketests;

import org.example.demotestautomation.utilities.CsvReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import javax.imageio.IIOException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;


public class GFormName {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // improved for parallel
    private String webURL= "https://workspace.google.com/intl/en-US/gmail/";
    private String firstName;
    private String lastName;
    //private CsvReader reader = new CsvReader("/Users/cjaid/Documents/SDET/Day 1/demo-test-automation/src/test/java/org/example/demotestautomation/utilities/enteryourname.csv");
    /*
    public static void main(String[] args){
        CsvReader reader = new CsvReader("/Users/cjaid/Documents/SDET/Day 1/demo-test-automation/src/test/java/org/example/demotestautomation/utilities/enteryourname.csv");


        try{
            reader.printAll();
        } catch(IOException e){
            System.out.println(e);
        }finally {
            reader = null;
        }

    }*/

    @ParameterizedTest
    @MethodSource("getData")
    public void setNameAndLastName(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        // 1. Open the browser and go to Gmail
        getDriver().get(webURL);

        // 2. Clic on create account button
        WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/summary/span[3]")));
        createAccountButton.click();

        // 3. Clic on for my personal use button
        WebElement forMyPersonalUseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/nav/a[1]")));
        forMyPersonalUseButton.click();

        // 4. wait search for firstname and send the string to the textbox
        WebElement firstNameBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
        firstNameBox.click();
        firstNameBox.sendKeys(this.firstName);

        // 5. wait search for last name and sends the string to the textbox
        WebElement lastNameBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName")));
        lastNameBox.click();
        lastNameBox.sendKeys(this.lastName);


        // 6. wait search for the next button
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/c-wiz/main/div[3]/div/div/div/div/button")));

        // 7. clic on next button
        nextButton.click();

        // 8. assert
        By monthDropdown = By.id("month");

        WebElement month = wait.until(
                ExpectedConditions.visibilityOfElementLocated(monthDropdown)
        );

        Assertions.assertTrue(month.isDisplayed());

    }


    public static Stream<org.junit.jupiter.params.provider.Arguments> getData() throws Exception {
        CsvReader reader = new CsvReader("src/test/java/org/example/demotestautomation/utilities/enteryourname.csv");

        List<String[]> data = reader.readAll();

        return data.stream().skip(1).map(row -> Arguments.of(row[0], row[1]));
    }

    @BeforeEach
    public void setUp(){
        driver.set(new FirefoxDriver());
    }

    /*
    @Test
    public void setFullName(){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        // 1. Open the browser and go to Gmail
        getDriver().get(webURL);


        //WebElement createAccountButton = getDriver().findElement(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/summary/span[3]"));
        //WebElement forMyPersonalUseButton = getDriver().findElement(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/nav/a[1]"));

        // 2. Clic on create account button
        WebElement createAccountButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/summary/span[3]")));
        createAccountButton.click();

        // 3. Clic on for my personal use button
        WebElement forMyPersonalUseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/gws-header/header/div/div[3]/dropdown-button-wrapper/div/details/nav/a[1]")));
        forMyPersonalUseButton.click();

        // 4. wait search for firstname and send the string to the textbox
        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));
        firstName.click();
        firstName.sendKeys(this.firstName);

        // 5. wait search for last name and sends the string to the textbox
        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.id("lastName")));
        lastName.click();
        lastName.sendKeys(this.lastName);


        // 6. wait search for the next button
        WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/c-wiz/main/div[3]/div/div/div/div/button")));

        // 7. clic on next button
        nextButton.click();

        // 8. assert
        By monthDropdown = By.id("month");

        WebElement month = wait.until(
                ExpectedConditions.visibilityOfElementLocated(monthDropdown)
        );

        Assertions.assertTrue(month.isDisplayed());
    }
     */

    @AfterEach
    public void tearDown(){
        getDriver().quit();
        driver.remove();
    }

    // driver getter
    private WebDriver getDriver(){
        return driver.get();
    }
}
