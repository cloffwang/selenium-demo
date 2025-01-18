package com.cliff.runner;


import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/regression-test",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},// Path to feature files
        glue = {"com.cliff.hooks", "com.cliff.steps"},
        tags = "@Regression")
public class RunRegressionTest extends RunCucumberTest{
}
