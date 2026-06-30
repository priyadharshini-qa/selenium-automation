package com.saucedemo.base;

import com.saucedemo.utils.DriverManager;
import com.saucedemo.utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class BasePage {

    // Core dependencies for all pages: WebDriver and WaitUtils
    protected WebDriver driver;
    protected WaitUtils waitUtils;
   // Constructor initializes WebDriver, WaitUtils, and PageFactory elements
    public BasePage() {
        this.driver   = DriverManager.getDriver();
        this.waitUtils = new WaitUtils(driver);
        PageFactory.initElements(driver, this);
    }

    //  Click actions 
    protected void click(By locator) {
        waitUtils.waitForClickable(locator).click();
    }

    protected void click(WebElement element) {
        waitUtils.waitForClickable(element).click();
    }

    //  Type / Input actions 

    protected void type(By locator, String text) {
        WebElement element = waitUtils.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void type(WebElement element, String text) {
        WebElement elementToType = waitUtils.waitForVisible(element);
        elementToType.clear();
        elementToType.sendKeys(text);
    }

    protected void selectByVisibleText(By locator, String text) {
        new Select(driver.findElement(locator)).selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        new Select(driver.findElement(locator)).selectByValue(value);
    }

    //  Read actions 

    protected String getText(By locator) {
        return waitUtils.waitForVisible(locator).getText().trim();
    }

    protected String getText(WebElement element) {
        return waitUtils.waitForVisible(element).getText().trim();
    }

    protected String getAttribute(By locator, String attribute) {
        return waitUtils.waitForVisible(locator).getAttribute(attribute);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitUtils.waitForVisible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitUtils.waitForVisible(element).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isEnabled(By locator) {
        try {
            return driver.findElement(locator).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //  Navigation helpers 

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    //  Wait wrappers (delegates to WaitUtils) 

    protected WebElement waitForVisible(By locator) {
        return waitUtils.waitForVisible(locator);
    }

    protected WebElement waitForVisible(WebElement element) {
        return waitUtils.waitForVisible(element);
    }

    protected WebElement waitForClickable(By locator) {
        return waitUtils.waitForClickable(locator);
    }

    protected WebElement waitForClickable(WebElement element) {
        return waitUtils.waitForClickable(element);
    }

    protected boolean waitForUrl(String urlFragment) {
        return waitUtils.waitForUrlContains(urlFragment);
    }
}