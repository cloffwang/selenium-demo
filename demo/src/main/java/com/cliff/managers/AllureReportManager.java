package com.cliff.managers;

import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureReportManager implements ITestListener {
    private static ThreadLocal<Scenario> scenarioThreadLocal =
            new ThreadLocal<>();

    public static void setScenarioThreadLocal(Scenario scenario) {
        scenarioThreadLocal.set(scenario);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        //attachScreenshot(result.getTestName());
        //todo attach env
    }

    @Override
    public void onTestStart(ITestResult result) {
        //skipped for cucumber test
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //skipped for cucumber test
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //todo not implemented
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //todo not implemented
    }

    @Override
    public void onStart(ITestContext context) {
        //todo not implemented
    }

    @Override
    public void onFinish(ITestContext context) {
        //todo not implemented
    }

    @Attachment(value = "Screenshot - {testName} - {testStep}", type = "image/png")
    public static byte[] attachScreenshot(String testName, String testStep) {
        WebDriver driver = DriverManager.getDriver();
        if (driver instanceof TakesScreenshot) {
            return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        }
        return null;
    }

    @Attachment(value = "Screenshot - {testName}", type = "image/png")
    public static byte[] attachScreenshot(String testName) {
        WebDriver driver = DriverManager.getDriver();
        if (driver instanceof TakesScreenshot) {
            try {
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } catch (UnhandledAlertException e) {
                attachText("An alert blocking screenshots");
            }
        }
        return null;
    }

    @Attachment(value = "message", type = "text/plain", fileExtension = ".txt")
    public static String attachText(String message) {
        return message;
    }

}
