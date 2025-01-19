package com.cliff.pages;

import com.cliff.common.ElementExists;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By userNameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errMsgBox = By.cssSelector("h3[data-test=error]");
    private final By errMsgBoxCloseBtn = By.cssSelector("button[data-test=error-button]");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isLoginPage() {
        return ElementExists.isElementExists(driver, loginButton);
    }

    public void fillUserInfo(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        //driver.findElement(loginButton).click();
    }

    public String getError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement err = wait.until(ExpectedConditions.visibilityOfElementLocated(errMsgBox));
        return err.getText();
    }

    public void closeErrBox() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(errMsgBoxCloseBtn));
        btn.click();
    }

    public boolean isErrorPresented() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errMsgBox));
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void tapOnLogin() {
        driver.findElement(loginButton).click();
    }

    public void inputUsername(String username) {
        driver.findElement(userNameField).sendKeys(username);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
}
