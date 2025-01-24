package com.cliff.runner;

import com.cliff.managers.DriverManager;
import com.cliff.steps.BaseSteps;
import com.cliff.utils.ProjLog;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty", "html:target/cucumber-html-report",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        glue = {"com.cliff.hooks", "com.cliff.steps"},
        tags = "@InProgress and not @skip")
public class RunNotReadyTest extends AbstractTestNGCucumberTests {
    private String env;

    @Override
    @BeforeClass(alwaysRun = true)
    public void setUpClass(ITestContext context) {
        //this.env = context.getCurrentXmlTest().getParameter("env");
        this.env = "local";
        ProjLog.logger.debug("current env is {}", env);
        super.setUpClass(context);
    }

    @Override
    @DataProvider
    public Object[][] scenarios() {
        Object[][] cucumberScenarios = super.scenarios();
        TestDataLoader testDataLoader = new TestDataLoader();
        testDataLoader.feedTestData(cucumberScenarios, env);
        return testDataLoader.getCucumberScenarios();
    }

    @Override
    @Test(groups = "cucumber", description = "Runs InProgress Scenarios", dataProvider = "scenarios")
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
