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
    Then the error message should match "<errorKey>"

    Examples:
      | scenario              | username        | password     | errorKey              |
      | Locked out user       | locked_out_user | secret_sauce | LOCKED_OUT_USER       |
      | Invalid credentials   | invalid_user    | wrong_pass   | INVALID_CREDENTIALS   |
      | Empty username        |                 | secret_sauce | EMPTY_USERNAME        |
      | Empty password        | standard_user   |              | EMPTY_PASSWORD        |
      | Empty both fields     |                 |              | EMPTY_BOTH            |

   