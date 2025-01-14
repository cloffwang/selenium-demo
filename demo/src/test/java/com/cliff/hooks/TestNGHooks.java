package com.cliff.hooks;

import com.cliff.utils.ProjLog;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseSteps {
    @BeforeClass
    public void beforeClass() {
        ProjLog.logger.debug("This is running before class");
    }

    @AfterClass
    public void afterClass() {
        ProjLog.logger.debug("This is running after class");
    }

    @Before
    public void beforeScenario() {
        ProjLog.logger.debug("This is running before scenario");
    }

    @After
    public void afterScenario() {
        ProjLog.logger.debug("This is running after scenario");
    }
}
