Feature: Login functionality
  As a registered user of SauceDemo
  I want to log in to the application
  So that I can access the product inventory

  Background:
    Given the user is on the login page

  @smoke @regression
  Scenario Outline: Successful login for valid user types
    When the user logs in with username "<username>" and password "<password>"
    Then the user should be redirected to the inventory page

    Examples:
      | scenario                | username                  | password     |
      | Standard user           | standard_user             | secret_sauce |
      | Problem user            | problem_user              | secret_sauce |
      | Performance glitch user | performance_glitch_user   | secret_sauce |
      | Error user              | error_user                | secret_sauce |
      | Visual user             | visual_user               | secret_sauce |

  @regression
  Scenario Outline: Unsuccessful login attempts
    When the user logs in with username "<username>" and password "<password>"
    Then an error message should be displayed

    Examples:
      | scenario              | username        | password     |
      | Locked out user       | locked_out_user | secret_sauce |
      | Invalid credentials   | invalid_user    | wrong_pass   |
      | Empty username        |                 | secret_sauce |
      | Empty password        | standard_user   |              |
      | Empty both fields     |                 |              |