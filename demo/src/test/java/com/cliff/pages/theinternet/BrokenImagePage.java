package com.cliff.pages.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class BrokenImagePage {
    private WebDriver driver;

    private final By imagesA = By.cssSelector("img[src='asdf.jpg']");
    private final By imagesB = By.cssSelector("img[src='hjkl.jpg']");
    private final By imagesC = By.cssSelector("img[src='img/avatar-blank.jpg']");
    private final By title = By.xpath("//h3[contains(text(), 'Broken')]");

    public BrokenImagePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isBrokenImagePage() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(title));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isImageALoaded() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.attributeToBe(
                                    imagesA, "naturalWidth", "0")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isImageBLoaded() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.attributeToBe(
                                    imagesB, "naturalWidth", "0")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isImageCLoaded() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(
                    ExpectedConditions.not(
                            ExpectedConditions.attributeToBe(
                                    imagesC, "naturalWidth", "0")));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
