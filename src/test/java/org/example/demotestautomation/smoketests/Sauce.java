package org.example.demotestautomation.smoketests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class Sauce {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String BASE_URL = "https://www.saucedemo.com/";
    private WebDriverWait wait;

    public WebDriver getDriver(){
        return driver.get();
    }

    @BeforeMethod
    public void setUp(){
        driver.set(new ChromeDriver());
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        getDriver().get(BASE_URL);
    }

    // Test for login
    @Test
    public void loginTest(){
        // 1. Login
        loginAs("standard_user", "secret_sauce");

        // 2. My assert: Products span
        By products = By.cssSelector("[data-test='title']");
        WebElement productsSpan = wait.until(ExpectedConditions.visibilityOfElementLocated(products));
        Assert.assertTrue(productsSpan.getText().equalsIgnoreCase("Products"));

        // 3. Pro move assert from Chat: URL validation
        Assert.assertTrue(getDriver().getCurrentUrl().contains("inventory"));
    }

    // Test for invalid login
    @Test
    public void loginInvalidTest(){
        // 1. Login
        loginAs("fafa", "dafskl");

        // 2. Assert: message error
        By error = By.cssSelector("[data-test='error']");
        WebElement messageError = wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        Assert.assertTrue(messageError.getText().contains("Username and password do not match"),
                "Expected error message is not displayed");

        // 3. Assert: actual page is not the login page
        Assert.assertFalse(getDriver().getCurrentUrl().contains("inventory"),
                "There was a successful login when it did not have to happen.");
    }

    // Test add to cart
    @Test
    public void addToCarTest(){
        // 1. Login
        loginAs("standard_user", "secret_sauce");

        // 2. Select a product
        By product = By.id("add-to-cart-sauce-labs-bike-light");
        WebElement productAddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(product));
        productAddToCartButton.click();

        // 3. Assert the product selection
        By productRemove = By.id("remove-sauce-labs-bike-light");
        WebElement productRemoveFromCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(productRemove));
        Assert.assertTrue(productRemoveFromCartButton.isDisplayed());

        // 4. Go to the cart
        By cart = By.cssSelector("[data-test='shopping-cart-link']");
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(cart));
        cartButton.click();

        // 5. Assert the product on the cart
        By productOnCart = By.className("inventory_item_name");
        WebElement productOnCartLink = wait.until(ExpectedConditions.visibilityOfElementLocated(productOnCart));
        Assert.assertTrue(productOnCartLink.getText().contains("Bike Light"));
    }

    private void loginAs(String username, String password){
        // 1. Username input
        By user =  By.id("user-name");
        WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(user));
        userInput.sendKeys(username);

        // 2. Password input
        By pass = By.id("password");
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(pass));
        passwordInput.sendKeys(password);

        // 3. Click on login
        By login = By.id("login-button");
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(login));
        loginButton.click();
    }

    @AfterMethod
    public void tearDown(){
        getDriver().quit();
        driver.remove();
    }
}
