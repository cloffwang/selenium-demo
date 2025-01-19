@allure.label.owner=ChentingWang
Feature: Login Functionality

  @blocker #Severity: Allowed values are: “trivial”, “minor”, “normal”, “critical”, and “blocker”.
  @Smoke #@Disabled
  Scenario Outline: Successful Login
    Given I am on the login page
    When I enter valid username "<username>" and password "<password>"
    And I click the login button
    Then I should be logged in successfully

    Examples:
      | username | password |
      | standard_user | secret_sauce |
      | problem_user | secret_sauce |
      | error_user | secret_sauce |
    # others like:
      # standard_user
      # locked_out_user
      # problem_user
      # performance_glitch_user
      # error_user
      # visual_user

  @minor
  @Smoke
  Scenario: username is empty
    Given I am on the login page
    When I left username field empty
    And I click the login button
    Then I should see error contains "Username is required"

  @minor
  @Smoke
  Scenario: password is empty
    Given I am on the login page
    When I enter username "standard_user"
    And I click the login button
    Then I should see error contains "Password is required"

  @critical
  @Smoke
  Scenario Outline: wrong username and/or password
    Given I am on the login page
    When I enter username "<username>"
    And I enter password "<password>"
    And I click the login button
    Then I should see error contains "Username and password do not match any user in this service"

    Examples:
      | username | password |
      | standard_user | werew |
      | werwe | secret_sauce |
      | !@# | secret_sauce |
      | !@# | #@$@#$ |