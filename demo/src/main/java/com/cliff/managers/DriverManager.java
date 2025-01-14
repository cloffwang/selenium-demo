package com.cliff.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Locale;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver createDriver(String browser) {
        browser = browser.toLowerCase(Locale.ROOT);

        if (driver == null) {
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new IllegalArgumentException(
                                "Invalid Browser Type: " + browser
                        );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void CloseDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
