package com.cliff.pages.theinternet;

import com.cliff.utils.ProjLog;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.pagefactory.ByChained;
import java.time.Duration;

public class HorizontalSliderPage {
    private WebDriver driver;

    private By title = By.xpath("//h3[contains(text(), 'Horizontal')]");
    private By sliderLocator = new ByChained(By.cssSelector(".sliderContainer"), By.tagName("input"));
    private By sliderValue = new ByChained(By.cssSelector(".sliderContainer"), By.tagName("span"));

    public HorizontalSliderPage(WebDriver driver) { this.driver = driver; }

    public boolean isHorizontalSliderPage() {
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

    public void adjustSlider() {
        WebElement slider = driver.findElement(sliderLocator);
        new Actions(driver)
                .clickAndHold(slider)
                .moveByOffset(100,0)
                .release()
                .perform();
        ProjLog.logger.debug("Slider value after offset is {}", driver.findElement(sliderValue).getText());
    }

    public int getSliderValue() {
        return (int)Float.parseFloat(driver.findElement(sliderValue).getText());
    }
}
