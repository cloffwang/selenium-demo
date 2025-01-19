package com.cliff.hooks;

import com.cliff.managers.AllureReportManager;
import com.cliff.managers.DriverManager;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestNGHooks {

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("This is from TestNG BeforeSuite");
        // Perform suite-level setup (e.g., starting a test server)
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("This is from TestNG BeforeClass");
        // Perform class-level setup (e.g., initializing a database connection)
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("This is from TestNG BeforeMethod");
        // Perform method-level setup (e.g., starting a browser)
    }

    @Before // Cucumber Before hook
    public void beforeScenario(Scenario scenario) {
        System.out.println("This is from Cucumber Before");
        DriverManager.createDriver("chrome", false, true, false, "test");
        WebDriver driver = DriverManager.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        AllureReportManager.attachScreenshot(scenario.getName());
    }

    @After // Cucumber After hook
    public void afterScenario(Scenario scenario) {
        System.out.println("This is from Cucumber After");
        // Perform scenario-level teardown (e.g., taking a screenshot on failure)
        if (scenario.isFailed()) {
            AllureReportManager.attachScreenshot(scenario.getName());
        }
        DriverManager.CloseDriver();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("This is from TestNG AfterMethod");
        // Perform method-level teardown (e.g., closing the browser)
    }

    @AfterClass
    public void afterClass() {
        System.out.println("This is from TestNG AfterClass");
        // Perform class-level teardown (e.g., closing the database connection)
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("This is from TestNG AfterSuite");
        // Perform suite-level teardown (e.g., stopping the test server)
    }
}
