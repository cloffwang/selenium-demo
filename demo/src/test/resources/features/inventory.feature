Feature:

  Background:
    Given the user is logged in as "standard_user" and password is "secret_sauce"

  @Regression
  Scenario: Test if inventory is list as low to high
    Given the user is on the inventory page
    When the user choose to sort inventory from low to high
    Then the item of inventory should start from low to high