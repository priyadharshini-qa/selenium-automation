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
    public LoginPage() {
        super();
        
    }

    // --- Actions ---
    public void enterUsername(String username) {
        type(usernameField, username);  // Use BasePage's type() method
    }

    public void enterPassword(String password) {
        type(passwordField, password);  // Use BasePage's type() method
    }
    

    public void clickLoginButton() {
        click(loginButton);  // Use BasePage's click() method
    }

    // --- Composite Action (full login in one call) ---
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    // --- State Checks ---
    public boolean isLoginSuccessful() {
        return isDisplayed(inventoryHeader);  // Use BasePage method
    }

    public String getErrorMessage() {
        return getText(errorMessage);  //  Use BasePage method
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
}
}