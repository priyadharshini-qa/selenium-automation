package com.saucedemo.stepdefinitions;

import com.saucedemo.constants.ErrorMessages;
import com.saucedemo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps {

    private LoginPage loginPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage = new LoginPage();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWithUsernameAndPassword(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }
    @Then("the user should be redirected to the inventory page")
    public void theUserShouldBeRedirectedToTheInventoryPage() {
        assertThat(loginPage.isLoginSuccessful())
            .as("Expected inventory page to be displayed after successful login")
            .isTrue();
    }

    @Then("the error message should match {string}")
    public void theErrorMessageShouldMatch(String errorKey) {
        String expectedMessage = resolveExpectedMessage(errorKey);
        assertThat(loginPage.getErrorMessage())
            .as("Expected error message for key '" + errorKey + "' to match exactly")
            .isEqualTo(expectedMessage);
    }

    
    private String resolveExpectedMessage(String errorKey) {
        return switch (errorKey) {
            case "LOCKED_OUT_USER" -> ErrorMessages.LOCKED_OUT_USER;
            case "INVALID_CREDENTIALS" -> ErrorMessages.INVALID_CREDENTIALS;
            case "EMPTY_USERNAME" -> ErrorMessages.EMPTY_USERNAME;
            case "EMPTY_PASSWORD" -> ErrorMessages.EMPTY_PASSWORD;
            case "EMPTY_BOTH" -> ErrorMessages.EMPTY_BOTH;
            default -> throw new IllegalArgumentException("Unknown errorKey: " + errorKey);
        };
    }
}