package org.example.demotestautomation.smoketests;

import org.example.demotestautomation.utilities.CsvReader;
import org.example.demotestautomation.utilities.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.invokers.Arguments;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class GFormNameTNG {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // improved for parallel
    private String webURL= "https://workspace.google.com/intl/en-US/gmail/";
    private String firstName;
    private String lastName;

    /*
    public static void main(String[] args){
        ExcelReader excelReader = new ExcelReader("src/test/java/org/example/demotestautomation/utilities/enteryourname.xlsx",
                "Sheet1");

        try {
            excelReader.printAll();
        } catch (IOException e) {
            System.out.println(e);
        }


    }*/


    @Test (dataProvider = "excelData")
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

        //Assertions.assertTrue(month.isDisplayed());
        Assert.assertTrue(month.isDisplayed());


    }


    @DataProvider(name= "excelData")
    public static Object[][] getData() throws Exception {
        //CsvReader reader = new CsvReader("src/test/java/org/example/demotestautomation/utilities/enteryourname.csv");
        ExcelReader excelReader = new ExcelReader("src/test/java/org/example/demotestautomation/utilities/enteryourname.xlsx", "Sheet1");
        List<String[]> data = excelReader.readAll();

        Object[][] result = new Object[data.size()-1][2]; // skip header

        for(int i = 1; i < data.size(); i++){
            result[i - 1][0] = data.get(i)[0]; // firstName
            result[i - 1][1] = data.get(i)[1]; // lastName

        }

        return result;
    }

    @BeforeMethod
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


    @AfterMethod
    public void tearDown(){
        getDriver().quit();
        driver.remove();
    }

    // driver getter
    private WebDriver getDriver(){
        return driver.get();
    }


}
