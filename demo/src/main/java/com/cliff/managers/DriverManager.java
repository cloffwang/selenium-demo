package com.cliff.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static void createDriver(
            String browser,
            Boolean isBrowserStack,
            Boolean isHeadless,
            Boolean isGrid,
            String remoteUrl){
        WebDriver driver = null;
        browser = browser.toLowerCase(Locale.ROOT);
        if (driverThreadLocal.get() == null) {
            try {
                if (isBrowserStack) {
                    driver = createBrowserStackDriver(browser);
                } else {
                    switch (browser) {
                        case "chrome":
                            ChromeOptions chromeOptions = new ChromeOptions();
                            if (isHeadless) {
                                chromeOptions.addArguments("--headless");
                            }
                            if (isGrid) {
                                driver = new RemoteWebDriver(
                                        new URL(remoteUrl), chromeOptions);
                            } else {
                                WebDriverManager.chromedriver().setup();
                                driver = new ChromeDriver(chromeOptions);
                            }
                            break;
                        case "firefox":
                            FirefoxOptions firefoxOptions = new FirefoxOptions();
                            if (isHeadless) {
                                firefoxOptions.addArguments("-headless");
                            }
                            if (isGrid) {
                                driver = new RemoteWebDriver(
                                        new URL(remoteUrl), firefoxOptions);
                            } else {
                                WebDriverManager.firefoxdriver().setup();
                                driver = new FirefoxDriver(firefoxOptions);
                            }
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid browser option: " + browser);
                    }
                }
            } catch (MalformedURLException ex) {
                throw new RuntimeException("Invalid remote URL: " + remoteUrl, ex);
            }
            driverThreadLocal.set(driver);
        }
    }

    public static void createDriver(String browser) {
        browser = browser.toLowerCase(Locale.ROOT);

        if (driverThreadLocal.get() == null) {
            WebDriver driver = null;
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
            driverThreadLocal.set(driver);
        }

    }

    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    public static void CloseDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }

    private static WebDriver createBrowserStackDriver(String browser) throws MalformedURLException {
        //need to set system env for BROWSERSTACK_USER, BROWSERSTACK_KEY, BROWSERSTACK_HUB
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String username = System.getenv("BROWSERSTACK_USER");
        String passkey = System.getenv("BROWSERSTACK_KEY");
        String hubUrl = System.getenv("BROWSERSTACK_HUB");
        capabilities.setCapability("browserstack.user", username);
        capabilities.setCapability("browserstack.key", passkey);
        capabilities.setCapability("project", "test123");
        capabilities.setCapability("build", "v0.1");
        capabilities.setCapability("name", "test1");

        switch (browser) {
            case "chrome":
                capabilities.setCapability("browser", "Chrome");
                capabilities.setCapability("browser_version", "latest");
                capabilities.setCapability("os", "Windows");
                capabilities.setCapability("os_version", "11");
                break;
            case "firefox":
                capabilities.setCapability("browser", "Firefox");
                capabilities.setCapability("browser_version", "latest");
                capabilities.setCapability("os", "Windows");
                capabilities.setCapability("os_version", "11");
                break;
            default:
                throw new IllegalArgumentException("Invalid browser option: " + browser);

        }
        return new RemoteWebDriver(new URL(hubUrl), capabilities);
    }
}
