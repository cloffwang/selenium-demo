package com.cliff.pages.theinternet;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DigestAuthPage {
    private final WebDriver driver;

    private final By title = By.xpath("//h3[contains(text(), 'Digest')]");

    public DigestAuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDigestPage() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(title));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        //alert.sendKeys(username);
        //driver.switchTo().frame(0);
        new Actions(driver)
                .sendKeys(username)
                .sendKeys(Keys.TAB)
                .sendKeys(password)
                .perform();
        //alert.sendKeys(password);
        //alert.accept();
        //driver.switchTo().defaultContent();
    }
}
