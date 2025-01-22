package com.cliff.pages.theinternet;

import com.cliff.common.ElementExists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.pagefactory.ByChained;

public class TheInternetHomePage {
    private final WebDriver driver;

    private final By title = By.cssSelector("h1[class=heading]");
    private final By addRemoveElementEntry = By.cssSelector("a[href='/add_remove_elements/']");
    private final By basicAuthEntry = By.xpath("//ul/li[3]/a");
    private final By brokenImagesEntry = RelativeLocator.with(By.tagName("a")).below(By.linkText("Basic Auth"));
    private final By challengingDOMEntry = new ByChained(By.tagName("a"), By.partialLinkText("Challenging"));
    private final By checkBoxEntry = By.cssSelector("[href*='checkbox']");
    private final By contextMenuEntry = By.cssSelector("[href*='context']");
    private final By digestAuthEntry = By.cssSelector("[href*='digest']");
    private final By disappearingEntry = By.partialLinkText("disa");
    private final By dragAndDropEntry = By.partialLinkText("drag");

    public TheInternetHomePage(WebDriver driver) {this.driver = driver;}

    public boolean isTheInternetHomePage() { return ElementExists.isElementExists(driver, title);}

    public void gotoAddRemoveElements() {
        driver.findElement(addRemoveElementEntry).click();
    }

    public void gotoBrokenImage() {
        driver.findElement(brokenImagesEntry).click();
    }

    public void gotoCheckboxes() {
        driver.findElement(checkBoxEntry).click();
    }

    public void gotoContextMenu() {
        driver.findElement(contextMenuEntry).click();
    }

    public void gotoDigestAuth() {
        driver.findElement(digestAuthEntry).click();
    }
}
