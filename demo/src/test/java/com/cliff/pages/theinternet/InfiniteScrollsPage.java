package com.cliff.pages.theinternet;

import com.cliff.utils.ProjLog;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InfiniteScrollsPage {
    private WebDriver driver;

    private final By title = By.xpath("//h3[contains(text(), 'Infinite')]");
    private final By scrollContainer = By.cssSelector(".jscroll-inner");
    private String containerString = "//div[@class='jscroll-added']";

    public InfiniteScrollsPage(WebDriver driver) { this.driver = driver; }

    public boolean scrollToFind(int index) {
        WebElement scroller = driver.findElement(scrollContainer);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean founded = false;
        if(index < 1) {
            throw new InvalidArgumentException("Index number should >= 1");
        } else {
            containerString = String.format("%s[%d]", containerString, index);
        }

        while (!founded) {
            try {
                long lastHeight = (long)js.executeScript("return arguments[0].scrollHeight", scroller);
                js.executeScript("window.scrollTo(0, arguments[0].scrollHeight)", scroller);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
                wait.until((ExpectedCondition<Boolean>) driver -> {
                    long newHeight = (long)js.executeScript(
                            "return arguments[0].scrollHeight", scroller);
                    return newHeight > lastHeight;
                });
            } catch (NullPointerException e) {
                throw new RuntimeException("Can't find element to measure height");
            }

            try {
                driver.findElement(By.xpath(containerString));
                founded = true;
            } catch (NoSuchElementException e) {
                ProjLog.logger.debug("Keep searching No.{} element for infinite scrolling", index);
            }
        }
        return founded;
    }
}
