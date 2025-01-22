package com.cliff.pages.sauselab;

import com.cliff.common.ElementExists;
import com.cliff.utils.ProjLog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InventoryPage {
    public final String optionAZ = "Name (A to Z)";
    public final String optionZA = "Name (Z to A)";
    public final String optionLoHi = "Price (low to high)";
    public final String optionHiLo = "Price (high to low)";

    private final WebDriver driver;

    private final By listItem = By.className("inventory_item");
    private final By headerLabel = By.className("header_label");
    private final By sortList = By.className("product_sort_container");
    private final By sonPrice = By.className("inventory_item_price");

    private Select select;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
    }

    public boolean isInventoryPage() {
        return ElementExists.isElementExists(driver, headerLabel);
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
        List<WebElement> listElements = driver.findElements(listItem);
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
        List<WebElement> listElements = driver.findElements(listItem);
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

    private void findSelect() {
        WebElement selectElement = driver.findElement(sortList);
        this.select = new Select(selectElement);
    }
}
