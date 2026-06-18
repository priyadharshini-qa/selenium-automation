package com.saucedemo.pages;

import com.saucedemo.base.BasePage;
import org.openqa.selenium.By;

public class CheckoutDetailsPage extends BasePage {

    // --- Locators ---
    private By checkoutContainer  = By.id("checkout_info_container");
    private By pageTitle          = By.className("title");
    private By firstNameField     = By.cssSelector("[data-test='firstName']");
    private By lastNameField      = By.cssSelector("[data-test='lastName']");
    private By postalCodeField    = By.cssSelector("[data-test='postalCode']");
    private By continueBtn        = By.cssSelector("[data-test='continue']");
    private By cancelBtn          = By.cssSelector("[data-test='cancel']");
    private By errorMessage       = By.cssSelector("[data-test='error']");
    private By errorCloseBtn      = By.className("error-button");

    // --- Constructor ---
    public CheckoutDetailsPage() {
        super();
    }

    // --- Actions ---

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterPostalCode(String postalCode) {
        type(postalCodeField, postalCode);
    }

    // Composite — fill all fields in one call
    public void fillCheckoutDetails(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        click(continueBtn);
    }

    public void clickCancel() {
        click(cancelBtn);
    }

    public void closeErrorMessage() {
        click(errorCloseBtn);
    }

    // Composite — fill and submit in one call
    public void submitCheckoutDetails(String firstName, String lastName, String postalCode) {
        fillCheckoutDetails(firstName, lastName, postalCode);
        clickContinue();
    }

    // --- State Checks ---

    public boolean isCheckoutPageDisplayed() {
        return isDisplayed(checkoutContainer);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMessage);
    }

    public String getErrorMessage() {
        try {
            return getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }

    public String getFirstNameValue() {
        return getAttribute(firstNameField, "value");
    }

    public String getLastNameValue() {
        return getAttribute(lastNameField, "value");
    }

    public String getPostalCodeValue() {
        return getAttribute(postalCodeField, "value");
    }

    public boolean isContinueButtonVisible() {
        return isDisplayed(continueBtn);
    }

    public boolean isCancelButtonVisible() {
        return isDisplayed(cancelBtn);
    }
}