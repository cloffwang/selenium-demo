package com.cliff.common;

import com.cliff.managers.AllureReportManager;
import com.cliff.pages.sauselab.LoginPage;
import com.cliff.utils.ProjLog;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CommonActions {
    public static void login(
            WebDriver driver,
            String username,
            String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillUserInfo(username,password);
        loginPage.tapOnLogin();
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
        ProjLog.logger.debug("Alert pops up: {}", alertMsg);

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

    public static void checkLinks(WebDriver driver) {
        List<WebElement> elementList = driver.findElements(By.cssSelector("a[href]"));
        elementList.forEach(webElement -> {
            String link = webElement.getDomAttribute("href");
            ProjLog.logger.debug("link is: [{}]", link);
        });
    }

    public static boolean checkLinks(WebDriver driver, boolean isHttpLinkOnly) {
        if (isHttpLinkOnly) {
            List<WebElement> elementList = driver.findElements(By.cssSelector("a[href^='http']"));
            List<String> validList = new ArrayList<>();
            elementList.forEach(webElement -> {
                String link = webElement.getDomAttribute("href");
                ProjLog.logger.debug("link is: [{}]", link);
                try {
                    URL url = new URL(link);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.connect();
                    int responseCode = connection.getResponseCode();
                    if (responseCode >= 200 && responseCode < 400) {
                        validList.add(link);
                    } else {
                        ProjLog.logger.debug("I can't open this link: [{}], response code is [{}]",
                                link, responseCode);
                        AllureReportManager.attachText(String.format(
                                "I can't open this link: [%s], response code is [%d]", link, responseCode));
                    }
                } catch (IOException e) {
                    ProjLog.logger.debug("This link is invalid: [{}]", link);
                }

            });
            return validList.size() == elementList.size();
        } else {
            checkLinks(driver);
            return false;
        }
    }
}
