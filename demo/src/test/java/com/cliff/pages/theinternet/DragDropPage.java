package com.cliff.pages.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;

public class DragDropPage {
    private WebDriver driver;

    private By title = By.xpath("//h3[contains(text(), 'Drag')]");
    private By boxA = By.id("column-a");
    private By boxB = By.id("column-b");
    private By labelA = new ByChained(boxA, By.tagName("header"));
    private By labelB = new ByChained(boxB, By.tagName("header"));

    public DragDropPage(WebDriver driver) { this.driver = driver;}

    public boolean isDragAndDropPage() {
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

    public String getLabelAText() { return driver.findElement(labelA).getText(); }

    public String getLabelBText() { return driver.findElement(labelB).getText(); }

    public void dragAndDropA2B() {
        new Actions(driver)
                .dragAndDrop(driver.findElement(boxA), driver.findElement(boxB))
                .perform();
    }
}
