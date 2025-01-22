package com.cliff.pages.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChallengingDOMPage {
    private WebDriver driver;

    private By title = By.xpath("//h3[contains(text(), 'Challenging')]");
    private By deleteAtRow3 = By.xpath("//tbody/tr[3]/td[7]/a[@href='#delete']");

}
