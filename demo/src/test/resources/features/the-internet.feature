@allure.label.owner=ChentingWang
Feature: the-internet selenium demo
  This website contains some common web elements for selenium demo

  @critical @Regression
  Scenario: add/remove elements: add
    Given I am on the-internet site
    When I click the Add Remove Elements link
    And I tap on Add Element button
    Then I should be able to see a Delete button

  @critical @Regression
  Scenario: add/remove elements: delete
    Given I am on the-internet site
    When I click the Add Remove Elements link
    And I tap on Add Element button
    Then I should be able to see a Delete button
    And I tap on Delete button
    Then I should be able to see Delete button gone

  @critical @Regression
  Scenario: Check if all image loaded
    Given I am on the-internet site
    When I click the Broken Image link
    Then I should be able to see all 3 images are loaded

  @critical @Regression
  Scenario: Check checkbox function
    Given I am on the-internet site
    When I click the Checkboxes link
    Then I should be able to check checkbox1
    And I should be able to check checkbox2

  @critical @Regression
  Scenario: Check Context Menu
    Given I am on the-internet site
    When I click the Context menu link
    Then I should see an alert says "You selected a context menu" after right click hotspot
    But I can dismiss it
    And I can go back to Context menu page

  @critical @Regression @InProgress @skip
  Scenario: Login by Digest Auth
    Given I am on the-internet site
    When I click the Digest Auth link
    And I can login as user "admin" and password "admin"
    Then I can see Digest Auth page