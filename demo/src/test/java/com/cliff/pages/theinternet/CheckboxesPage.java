package com.cliff.pages.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class CheckboxesPage {
    private WebDriver driver;
    private By title = By.xpath("//h3[contains(text(), 'Checkboxes')]");
    private By checkbox1 = By.xpath("//input[1]");
    private By checkbox2 = By.xpath("//input[2]");
    private By checkbox1Checked = By.xpath("//input[1][@checked]");
    private By checkbox2Checked = By.xpath("//input[2][@checked]");

    public CheckboxesPage(WebDriver driver) { this.driver = driver;}

    public boolean isCheckboxesPage() {
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

    public boolean isCheckbox1Checked() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox1Checked));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isCheckbox2Checked() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox2Checked));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void tickCheckbox1() {
        driver.findElement(checkbox1).click();
    }

    public void tickCheckbox2() {
        driver.findElement(checkbox2).click();
    }
}
