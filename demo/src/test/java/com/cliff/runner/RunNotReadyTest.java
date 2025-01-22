package com.cliff.runner;

import com.cliff.managers.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-html-report",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"com.cliff.hooks", "com.cliff.steps"},
        tags = "@InProgress and not @skip")
public class RunNotReadyTest extends AbstractTestNGCucumberTests {
    @Override
    @Test(groups = "cucumber", description = "Run Test is Still In Progress", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        DriverManager.createDriver(
                "chrome", false, false, false, "test");
        super.runScenario(pickleWrapper, featureWrapper);
    }
}
