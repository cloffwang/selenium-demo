package com.cliff.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", // Path to feature files
        glue = {"com.cliff.hooks", "com.cliff.steps"})          // Package with step definitions
public class CucumberRunner extends AbstractTestNGCucumberTests {
}

/*
Page Object Model (POM): Organize your UI interactions using POM. This improves code reusability and maintainability by separating page elements and actions from your test logic.
Step Definitions: Keep your step definitions concise and focused. Use meaningful names and avoid complex logic within steps.
Hooks: Use hooks for common setup and teardown tasks (e.g., browser initialization, test data setup, screenshot capture on failure, reporting).
Driver Management: Implement a DriverFactory class to manage your WebDriver instances (e.g., ChromeDriver, FirefoxDriver) and handle browser lifecycle.
Configuration: Use configuration files (e.g., .properties or .yaml) to store environment-specific settings (URLs, browser types, credentials).
Reporting: Integrate a reporting library (e.g., ExtentReports, Allure) to generate detailed test execution reports.
Dependency Injection: Consider using dependency injection (e.g., with Cucumber's @ParameterType or Spring) to manage object dependencies and improve testability.
 */
