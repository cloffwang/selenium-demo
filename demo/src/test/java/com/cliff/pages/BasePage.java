package com.cliff.pages;

import com.cliff.common.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class BasePage {
    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForVisible(By locator) {
        return Waits.waitForVisible(driver, locator);
    }

    protected WebElement waitForClickable(By locator) {
        return Waits.waitForClickable(driver, locator);
    }

    protected List<WebElement> waitForPresenceOfAll(By locator) {
        return Waits.waitForPresenceOfAll(driver, locator);
    }
}
