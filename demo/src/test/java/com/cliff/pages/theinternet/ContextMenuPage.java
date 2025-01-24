package com.cliff.pages.theinternet;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class ContextMenuPage {
    private WebDriver driver;

    private By title = By.xpath("//h3[contains(text(), 'Context')]");
    private By hotSpot = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver) {this.driver = driver;}

    public boolean isContextMenuPage() {
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

    public void rightClickHosSpot() {
        WebElement clickable = driver.findElement(hotSpot);
        new Actions(driver).contextClick(clickable).perform();
    }

    public String switchToAlert() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        WebElement clickable = driver.findElement(hotSpot);
        new Actions(driver)
                .contextClick(clickable)
                .perform();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void dismissAlert() {
        new Actions(driver).moveByOffset(0, -100).click().perform();
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        WebElement clickable = driver.findElement(hotSpot);
        new Actions(driver).contextClick(clickable).perform();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
        driver.switchTo().defaultContent();
    }
}
