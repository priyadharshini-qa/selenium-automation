Feature: Login functionality

  Background:
    Given the user is on the login page

  Scenario: Successful login with valid credentials
    When the user enters username "standard_user" and password "secret_sauce"
    And the user clicks the login button
    Then the user should be redirected to the inventory page

  Scenario: Login with invalid credentials
    When the user enters username "invalid_user" and password "wrong_pass"
    And the user clicks the login button
    Then an error message should be displayed

  Scenario: Login with empty username
    When the user enters username "" and password "secret_sauce"
    And the user clicks the login button
    Then an error message should be displayed

  Scenario: Login with empty password
    When the user enters username "standard_user" and password ""
    And the user clicks the login button
    Then an error message should be displayed

  Scenario: Login with locked out user
    When the user enters username "locked_out_user" and password "secret_sauce"
    And the user clicks the login button
    Then an error message should be displayed