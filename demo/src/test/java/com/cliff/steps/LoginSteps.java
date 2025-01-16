package com.cliff.steps;

import com.cliff.managers.ConfigManager;
import com.cliff.managers.DriverManager;
import com.cliff.pages.InventoryPage;
import com.cliff.pages.LoginPage;
import com.cliff.utils.ProjLog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps  {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String targetUrl = ConfigManager.getConfig("web", "target");
        this.driver = DriverManager.getDriver();
        this.loginPage = new LoginPage(driver);
        driver.get(targetUrl);
        Assert.assertTrue(loginPage.isLoginPage(),
                "It's not login page");
    }

    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(
            String username, String password) {
        loginPage.fillUserInfo(username, password);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        loginPage.tapOnLogin();
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPage());
    }

    @Given("the user is logged in as {string} and password is {string}")
    public void common_user_login(String username, String password) {
        this.i_am_on_the_login_page();
        this.i_enter_valid_username_and_password(username, password);
        this.i_click_the_login_button();
        this.i_should_be_logged_in_successfully();
    }
}