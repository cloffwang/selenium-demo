package com.cliff.utils;

import com.cliff.pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

public class CommonActions {
    public static void login(
            WebDriver driver,
            String username,
            String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username,password);
    }

    public static WebElement waitForAppear(
            WebDriver driver,
            int waitSecs,
            int frequency,
            By by) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(waitSecs))
                .pollingEvery(Duration.ofSeconds(frequency))
                .ignoring(NoSuchElementException.class);

        return wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver1) {
                return driver1.findElement(by);
            }
        });
    }

    public static WebElement waitForClickable(
            WebDriver driver,
            int waitSecs,
            By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitSecs));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void dismissAlert(WebDriver driver, boolean isAccepted) {
        Alert alert = driver.switchTo().alert();
        String alertMsg = alert.getText();
        TestLogger.logger.debug("Alert pops up: {}", alertMsg);

        if (isAccepted) {
            alert.accept();
        } else {
            alert.dismiss();
        }
    }

    public static void screenshotWithName(WebDriver driver, String filePath) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenshot  = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(filePath));
    }
}
