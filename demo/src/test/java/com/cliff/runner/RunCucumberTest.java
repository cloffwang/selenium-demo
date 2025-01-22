package com.cliff.runner;

import com.cliff.managers.ConfigManager;
import com.cliff.managers.DriverManager;
import com.cliff.utils.ProjLog;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.List;
import java.util.Map;

@CucumberOptions(features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-html-report",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"com.cliff.hooks", "com.cliff.steps"},
        tags = "not @skip and not @InProgress")          // Package with step definitions
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    private String env;

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Override
    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context) {
        this.env = context.getCurrentXmlTest().getParameter("env");
        super.setUpClass(context);
    }

    @Override
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        //this.env = System.getProperty("env");
        List<Map<String, Object>> configs = ConfigManager.getConfigListOfMaps("env", this.env, "browsers");
        for (Map<String, Object> config : configs) {
            DriverManager.createDriver(
                    (String)config.get("browser"),
                    ConfigManager.getBoolean("env", env, "browserStack"),
                    ConfigManager.getBoolean("env", env, "headless"),
                    ConfigManager.getBoolean("env", env, "grid"),
                    ConfigManager.getConfig("env", env, "hubUrl")
            );
            ProjLog.logger.debug("Setup WebDriver for browser {}", (String)config.get("browser"));
            super.runScenario(pickleWrapper, featureWrapper);
        }
    }

    /*@Step("Setup up driver")
    public void beforeMethod(BrowserData browserData) {
        System.out.println("This is from TestNG BeforeMethod");
        DriverManager.createDriver(
                browserData.browser,
                browserData.isBrowserStack,
                browserData.isHeadless,
                browserData.isGrid,
                browserData.hubUrl);
    }

    @Parameters("env")
    public BrowserData[] browserData(String env){
        List<Map<String, Object>> configs = ConfigManager.getConfigListOfMaps("env", env, "browsers");
        BrowserData[] browserData = new BrowserData[configs.size()];
        int i = 0;
        for (Map<String, Object> config : configs) {
            browserData[i++] = new BrowserData(
                    (String)config.get("browser"),
                    (String)config.get("version"),
                    ConfigManager.getBoolean("env", env, "headless"),
                    ConfigManager.getBoolean("env", env, "browserStack"),
                    ConfigManager.getBoolean("env", env, "grid"),
                    ConfigManager.getConfig("env", env, "hubUrl")
            );
        }
        return browserData;
    }

    public class BrowserData {
        public String browser;
        public String version;
        public boolean isHeadless;
        public boolean isBrowserStack;
        public boolean isGrid;
        public String hubUrl;

        public BrowserData(
                String browser, String version, Boolean isHeadless,
                Boolean isBrowserStack, Boolean isGrid, String hubUrl) {
            this.browser = browser;
            this.version = version;
            this.isBrowserStack = isBrowserStack;
            this.isGrid = isGrid;
            this.isHeadless = isHeadless;
            this.hubUrl = hubUrl;
        }
    }*/
}
