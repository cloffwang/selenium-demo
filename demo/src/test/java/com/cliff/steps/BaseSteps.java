package com.cliff.steps;

import com.cliff.common.CommonActions;
import com.cliff.managers.DriverManager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class BaseSteps {

    public static void executeSteps() {

    }


    @Then("I check all links on this page")
    public void check_all_links() {
        Assert.assertTrue(CommonActions.checkLinks(DriverManager.getDriver(), true));
    }
}
