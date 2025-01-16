package com.cliff.runner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/smoke-test"},// Path to feature files
        glue = {"com.cliff.hooks", "com.cliff.steps"},
        tags = "@Smoke and not @Disabled")
public class RunSmokeTest extends RunCucumberTest{
}
