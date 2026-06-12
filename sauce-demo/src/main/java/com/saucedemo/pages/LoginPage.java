package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.saucedemo.base.BasePage;



public class LoginPage extends BasePage {

    
    
    private WebDriverWait wait;

    // --- Locators ---
    private By usernameField   = By.id("user-name");
    private By passwordField   = By.id("password");
    private By loginButton     = By.id("login-button");
    private By errorMessage    = By.cssSelector("[data-test='error']");
    private By inventoryHeader = By.id("inventory_container");

    // --- Constructor ---
    public LoginPage(WebDriver driver) {
        super();
        
    }

    // --- Actions ---
    public void enterUsername(String username) {
        WebElement field = wait.until(
            ExpectedConditions.visibilityOfElementLocated(usernameField)
        );
        field.clear();
        field.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(
            ExpectedConditions.visibilityOfElementLocated(passwordField)
        );
        field.clear();
        field.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(
            ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    // --- Composite Action (full login in one call) ---
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // --- State Checks ---
    public boolean isLoginSuccessful() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(inventoryHeader)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
            ).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isErrorDisplayed() {
    try {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}
}