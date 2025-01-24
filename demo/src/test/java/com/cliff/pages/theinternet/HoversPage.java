package com.cliff.pages.theinternet;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HoversPage {
    private WebDriver driver;

    private final By title = By.xpath("//h3[contains(text(), 'Hovers')]");
    private final By hoverAble = By.cssSelector("img[alt='User Avatar']");
    private final By user1Name = By.xpath("//h5[contains(text(), 'user1')]");
    private final By user2Name = By.xpath("//h5[contains(text(), 'user2')]");
    private final By user3Name = By.xpath("//h5[contains(text(), 'user3')]");

    public HoversPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHoversPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(title));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isUserVisible(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        try {
            switch (index) {
                case 1:
                    wait.until(ExpectedConditions.visibilityOfElementLocated(user1Name));
                    break;
                case 2:
                    wait.until(ExpectedConditions.visibilityOfElementLocated(user2Name));
                    break;
                case 3:
                    wait.until(ExpectedConditions.visibilityOfElementLocated(user3Name));
                    break;
                default:
                    throw new InvalidArgumentException("index range should be 1 ~ 3, but received " + index);
            }
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void hoverOnUser(int index) {
        List<WebElement> users = driver.findElements(hoverAble);
        if ((index < 1) | (index > 3)) {
            throw new InvalidArgumentException("index range should be 1 ~ 3, but received " + index);
        }
        new Actions(driver)
                .moveToElement(users.get(index-1))
                .perform();
    }
}
