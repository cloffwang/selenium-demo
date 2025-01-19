package com.cliff.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-html-report",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"com.cliff.hooks", "com.cliff.steps"})          // Package with step definitions
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
