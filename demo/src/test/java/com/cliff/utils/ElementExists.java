package com.cliff.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ElementExists {
    public static boolean isElementExists(WebDriver driver, By selector) {
        try {
            driver.findElement(selector);
        } catch (NoSuchElementException ex) {
            return false;
        }
        return true;
    }
}
