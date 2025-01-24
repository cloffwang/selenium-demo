package com.cliff.pages.theinternet;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropDownPage {
    private WebDriver driver;
    private By title = By.xpath("//h3[contains(text(), 'Dropdown')]");
    private By selector = By.id("dropdown");

    public DropDownPage(WebDriver driver) { this.driver = driver; }

    public boolean isDropDownPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(title));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getSelected(int option) {
        Select select = new Select(driver.findElement(selector));
        select.selectByIndex(option);
        WebElement selected = select.getFirstSelectedOption();
        return selected.getText();
    }
}
