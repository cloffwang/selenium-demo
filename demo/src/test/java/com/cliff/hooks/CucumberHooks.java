package com.cliff.hooks;

import com.cliff.managers.AllureReportManager;
import com.cliff.managers.ConfigManager;
import com.cliff.managers.DriverManager;
import com.cliff.runner.RunCucumberTest;
import io.cucumber.java.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CucumberHooks {

    @Before // Cucumber Before hook
    public void beforeScenario(Scenario scenario) {

    }

    @Step("End step screenshots")
    @AfterStep
    public void afterStep(Scenario scenario) {
        AllureReportManager.attachScreenshot(scenario.getName());
    }

    @Step("Closing webdriver")
    @After // Cucumber After hook
    public void afterScenario(Scenario scenario) {
        // Perform scenario-level teardown (e.g., taking a screenshot on failure)
        if (scenario.isFailed()) {
            AllureReportManager.attachScreenshot(scenario.getName());
            WebDriver driver = DriverManager.getDriver();
            AllureReportManager.attachText(driver.getPageSource());
        }
        DriverManager.CloseDriver();
    }


}
