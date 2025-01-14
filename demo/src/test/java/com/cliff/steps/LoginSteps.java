package com.cliff.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefs {

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        // Code to navigate to the login page (e.g., using Selenium)
    }

    @When("I enter valid username and password")
    public void i_enter_valid_username_and_password() {
        // Code to enter username and password
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        // Code to click the login button
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        // Code to verify successful login
    }
}