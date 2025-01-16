Feature: Login Functionality

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