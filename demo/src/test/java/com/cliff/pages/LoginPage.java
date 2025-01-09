package com.cliff.pages;

import com.cliff.utils.ElementExists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.swing.*;

public class LoginPage {
    private final WebDriver driver;

    private final By userNameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isLoginPage() {
        return ElementExists.isElementExists(driver, loginButton);
    }

    public void login(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        driver.findElement(loginButton).click();
    }

    private void inputUsername(String username) {
        driver.findElement(userNameField).sendKeys(username);
    }

    private void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
}
