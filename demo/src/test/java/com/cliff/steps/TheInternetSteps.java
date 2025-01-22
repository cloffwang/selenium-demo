package com.cliff.steps;

import com.cliff.managers.ConfigManager;
import com.cliff.managers.DriverManager;
import com.cliff.pages.theinternet.*;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TheInternetSteps {
    private WebDriver driver;
    private TheInternetHomePage theInternetHomePage;
    private AddRemoveElementsPage addRemoveElementsPage;
    private BrokenImagePage brokenImagePage;
    private CheckboxesPage checkboxesPage;
    private ContextMenuPage contextMenuPage;
    private DigestAuthPage digestAuthPage;

    @Given("I am on the-internet site")
    public void i_am_on_the_internet_site() {
        String targetUrl = ConfigManager.getConfig("the-internet", "target");
        driver = DriverManager.getDriver();
        driver.get(targetUrl);
        theInternetHomePage = new TheInternetHomePage(driver);
        Assert.assertTrue(theInternetHomePage.isTheInternetHomePage(),
                "This is not https://the-internet.herokuapp.com/, can't locate title");
    }

    @When("I click the Add Remove Elements link")
    public void i_click_the_add_remove_elements_link() {
        theInternetHomePage.gotoAddRemoveElements();
        addRemoveElementsPage = new AddRemoveElementsPage(driver);
        Assert.assertTrue(addRemoveElementsPage.isAddRemoveElementsPage(),
                "This is not add remove elements page");
    }

    @When("I tap on Add Element button")
    public void i_tap_on_add_element_button() {
        addRemoveElementsPage.tapOnAddBtn();
    }

    @When("I tap on Delete button")
    public void i_tap_on_delete_button() {
        addRemoveElementsPage.tapOnDeleteBtn();
    }

    @When("I click the Broken Image link")
    public void i_click_the_broken_image_link() {
        theInternetHomePage.gotoBrokenImage();
        brokenImagePage = new BrokenImagePage(driver);
        Assert.assertTrue(
                brokenImagePage.isBrokenImagePage(),
                "Not in Broken Image Page"
        );
    }

    @When("I click the Checkboxes link")
    public void i_click_the_checkboxes_link() {
        checkboxesPage = new CheckboxesPage(driver);
        theInternetHomePage.gotoCheckboxes();
        Assert.assertTrue(
                checkboxesPage.isCheckboxesPage(),
                "This is not checkboxes page"
        );
    }

    @When("I click the Context menu link")
    public void i_click_the_context_menu_link() {
        theInternetHomePage.gotoContextMenu();
        contextMenuPage = new ContextMenuPage(driver);
        Assert.assertTrue(
                contextMenuPage.isContextMenuPage(),
                "Not in Context Menu page"
        );
    }

    @When("I right click on hotspot")
    public void i_right_click_on_hotspot() {
        contextMenuPage.rightClickHosSpot();
    }

    @When("I click the Digest Auth link")
    public void i_click_the_digest_auth_link() {
        theInternetHomePage.gotoDigestAuth();
    }

    @And("I can login as user {string} and password {string}")
    public void i_can_login_as_user_and_password(String username, String password){
        digestAuthPage = new DigestAuthPage(driver);
        digestAuthPage.login(username, password);
    }

    @Then("I should be able to see a Delete button")
    public void i_should_be_able_to_see_a_delete_button() {
        Assert.assertTrue(addRemoveElementsPage.isDeleteBtnVisible(),
                "Delete button is not visible");
    }

    @Then("I should be able to see Delete button gone")
    public void i_should_be_able_to_see_delete_button_gone() {
        Assert.assertTrue(addRemoveElementsPage.isDeleteBtnHidden(),
                "Delete button is still visible");
    }

    @Then("I should be able to see all 3 images are loaded")
    public void i_should_be_able_to_see_all_3_images_are_loaded() {
        Assert.assertTrue(
                brokenImagePage.isImageALoaded() && brokenImagePage.isImageBLoaded() &&
                        brokenImagePage.isImageCLoaded(),
                "Some images are broken"
        );
    }

    @Then("I should be able to check checkbox1")
    public void i_should_be_able_to_check_checkbox1() {
        if (checkboxesPage.isCheckbox1Checked()) {
            checkboxesPage.tickCheckbox1();
            Assert.assertFalse(
                    checkboxesPage.isCheckbox1Checked(),
                    "checkbox 1 is still checked"
            );
        } else {
            checkboxesPage.tickCheckbox1();
            Assert.assertTrue(
                    checkboxesPage.isCheckbox1Checked(),
                    "Checkbox 1 is still unchecked"
            );
        }
    }

    @Then("I should be able to check checkbox2")
    public void i_should_be_able_to_check_checkbox2() {
        if (checkboxesPage.isCheckbox2Checked()) {
            checkboxesPage.tickCheckbox2();
            Assert.assertFalse(
                    checkboxesPage.isCheckbox2Checked(),
                    "checkbox 2 is still checked"
            );
        } else {
            checkboxesPage.tickCheckbox2();
            Assert.assertTrue(
                    checkboxesPage.isCheckbox2Checked(),
                    "Checkbox 2 is still unchecked"
            );
        }
    }

    @Then("I should see an alert says {string} after right click hotspot")
    public void i_should_see_an_alert_says(String message) {
        String alertText = contextMenuPage.switchToAlert();
        Assert.assertEquals(
                alertText, message
        );
    }

    @But("I can dismiss it")
    public void i_can_dismiss_it() {
        contextMenuPage.dismissAlert();
    }

    @And("I can go back to Context menu page")
    public void i_can_go_back_to_context_menu_page() {
        Assert.assertTrue(
                contextMenuPage.isContextMenuPage(),
                "Not switch back to Context Menu page"
        );
    }

    @Then("I can see Digest Auth page")
    public void i_can_see_digest_auth_page() {
        Assert.assertTrue(
                digestAuthPage.isDigestPage(),
                "Not in Digest Auth page"
        );
    }
}
