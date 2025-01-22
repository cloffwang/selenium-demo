package com.cliff.pages.theinternet;

import com.cliff.common.ElementExists;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRemoveElementsPage {
    private final WebDriver driver;

    private final By addBtn = By.xpath("//div[@class='example']/button");
    private final By deleteBtn = By.className("added-manually");
    private final By title = By.xpath("//*[contains(text(), 'Elements')]"); // xpath locator: //*[contains(text(), 'Elements')]

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAddRemoveElementsPage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(title));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void tapOnAddBtn() {
        driver.findElement(addBtn).click();
    }

    public boolean isDeleteBtnVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(deleteBtn));
            return true;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean isDeleteBtnHidden() {
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(deleteBtn));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void tapOnDeleteBtn() {
        driver.findElement(deleteBtn).click();
    }
}
