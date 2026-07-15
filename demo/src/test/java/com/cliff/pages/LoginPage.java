package com.cliff.pages;

import com.cliff.common.ElementExists;
import com.cliff.common.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        WebElement err = Waits.waitForVisible(driver, errMsgBox);
        return err.getText();
    }

    public void closeErrBox() {
        WebElement btn = Waits.waitForClickable(driver, errMsgBoxCloseBtn);
        btn.click();
    }

    public boolean isErrorPresented() {
        try {
            Waits.waitForVisible(driver, errMsgBox);
            return true;
        } catch (TimeoutException e){
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
