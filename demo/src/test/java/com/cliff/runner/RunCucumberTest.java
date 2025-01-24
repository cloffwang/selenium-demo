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
    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context) {
        this.env = context.getCurrentXmlTest().getParameter("env");
        ProjLog.logger.debug("current env is {}", env);
        super.setUpClass(context);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        Object[][] cucumberScenarios = super.scenarios();
        TestDataLoader testDataLoader = new TestDataLoader();
        testDataLoader.feedTestData(cucumberScenarios, env);
        return testDataLoader.getCucumberScenarios();
    }

    @Override
    @Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        PickerWithData pickerWithData = (PickerWithData)pickleWrapper;
        BrowserData browserData = pickerWithData.getBrowserData();
        DriverManager.createDriver(
                browserData.browser,
                browserData.isBrowserStack,
                browserData.isHeadless,
                browserData.isGrid,
                browserData.hubUrl
        );
        super.runScenario(pickerWithData.getPickleWrapper(), featureWrapper);
    }
}
