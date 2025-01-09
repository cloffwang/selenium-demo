package com.cliff.utils;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverModule  extends AbstractModule {
    private static final String BROWSER = System.getProperty("browser", "firefox");
    private static final String REMOTE_URL = System.getProperty("remoteUrl", "http://localhost:4444");
    private static final String LOCATION = System.getProperty("location", "local");

    @Override
    protected void configure() {
        //other binders
    }

    @Provides
    @Singleton
    public WebDriver provideWebDriver() throws MalformedURLException {
        if (LOCATION.equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (BROWSER.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    EdgeOptions edgeOptions = new EdgeOptions();
                    capabilities.setCapability(EdgeOptions.CAPABILITY, edgeOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid browser parameter: "+ BROWSER);
            }

            try {
                return new RemoteWebDriver(new URL(REMOTE_URL), capabilities);
            } catch (MalformedURLException ex) {
                throw new RuntimeException("Invalid Remote Url: " + REMOTE_URL, ex);
            }
        } else if (LOCATION.equalsIgnoreCase("local")){
            return switch (BROWSER.toLowerCase()) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    yield new ChromeDriver();
                }
                case "firefox" -> {
                    //todo: WARNING: CDP support for Firefox is deprecated and will be removed in future versions. Please switch to WebDriver BiDi.
                    WebDriverManager.firefoxdriver().setup();
                    yield new FirefoxDriver();
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    yield new EdgeDriver();
                }
                default -> throw new IllegalArgumentException("Invalid browser parameter: " + BROWSER);
            };
        } else {
            throw new IllegalArgumentException("Invalid browser parameter: "+ LOCATION);
        }

    }
}
