package com.cliff.pages;

import com.cliff.utils.ProjLog;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;

public class InventoryPage extends BasePage {
    public final String optionAZ = "Name (A to Z)";
    public final String optionZA = "Name (Z to A)";
    public final String optionLoHi = "Price (low to high)";
    public final String optionHiLo = "Price (high to low)";

    private final By listItem = By.className("inventory_item");
    private final By headerLabel = By.className("header_label");
    private final By sortList = By.className("product_sort_container");
    private final By sonPrice = By.className("inventory_item_price");

    private Select select;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInventoryPage() {
        try {
            waitForVisible(headerLabel);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void sortAZ() {
        String valueAZ = "az";
        findSelect();
        select.selectByValue(valueAZ);
    }

    public void sortZA() {
        String valueZA = "za";
        findSelect();
        select.selectByValue(valueZA);
    }

    public void sortLoHi() {
        String valueLoHi = "lohi";
        findSelect();
        select.selectByValue(valueLoHi);
    }

    public void sortHiLo() {
        String valueHiLo = "hilo";
        findSelect();
        select.selectByValue(valueHiLo);
    }

    public float findPrice(WebElement parent) {
        return Float.parseFloat(parent.findElement(sonPrice).getText().substring(1));
    }

    public boolean isLowest() {
        List<WebElement> listElements = getInventoryItems();
        if(!listElements.isEmpty()) {
            float[] prices = new float[listElements.size()];
            for ( int i=0; i<listElements.size(); i++) {
                prices[i] = findPrice(listElements.get(i));
            }
            float first = prices[0];
            Arrays.sort(prices);
            return first == prices[0];
        }
        ProjLog.logger.debug("No inventory");
        return false;
    }

    public boolean isHighest() {
        List<WebElement> listElements = getInventoryItems();
        if(!listElements.isEmpty()) {
            float[] prices = new float[listElements.size()];
            for ( int i=0; i<listElements.size(); i++) {
                prices[i] = findPrice(listElements.get(i));
            }
            float first = prices[0];
            Arrays.sort(prices);
            return first == prices[listElements.size()-1];
        }
        ProjLog.logger.debug("No inventory");
        return false;
    }

    private List<WebElement> getInventoryItems() {
        try {
            return waitForPresenceOfAll(listItem);
        } catch (TimeoutException e) {
            return List.of();
        }
    }

    private void findSelect() {
        WebElement selectElement = waitForVisible(sortList);
        this.select = new Select(selectElement);
    }
}
