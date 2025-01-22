package com.cliff.steps;

import com.cliff.managers.DriverManager;
import com.cliff.pages.sauselab.InventoryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InventorySteps {
    private InventoryPage inventoryPage;

    @Given("the user is on the inventory page")
    public void the_user_is_on_the_inventory_page(){
        WebDriver driver = DriverManager.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.isInventoryPage());
    }

    @When("the user choose to sort inventory from low to high")
    public void the_user_choose_to_sort_inventory_from_low_to_high(){
        inventoryPage.sortLoHi();
        //todo: screenshot
    }

    @Then("the item of inventory should start from low to high")
    public void the_item_of_inventory_should_start_from_low_to_high(){
        Assert.assertTrue(inventoryPage.isLowest(),
                        "The lowest price is incorrect");
    }
}
