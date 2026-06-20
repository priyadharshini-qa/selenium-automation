Feature: Add to Cart functionality
  As a logged-in SauceDemo user
  I want to add products to my cart
  So that I can purchase the items I want

  Background:
    Given the user is on the login page
    And the user logs in as the standard user

  @smoke @regression
  Scenario: Add a single product to the cart after login
    When the user adds the first product from test data to the cart
    Then the cart badge should show "1" item

  @regression
  Scenario: Add every product listed in test data to the cart
    When the user adds each product from the test data file to the cart
    Then the cart badge should show the same count as the number of products added

  @regression
  Scenario: Remove a product after adding it
    When the user adds the first product from test data to the cart
    And the user removes that same product from the cart
    Then the cart badge should not be visible

  @regression
  Scenario: Add to Cart button changes to Remove after adding
    When the user adds the first product from test data to the cart
    Then the "Remove" button should be visible for that product