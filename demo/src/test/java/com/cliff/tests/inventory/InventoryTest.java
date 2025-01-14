package com.cliff.tests.inventory;

import com.cliff.pages.InventoryPage;
import com.cliff.common.CommonActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class InventoryTest {
    private static String targetUrl;
    private static String screenshotPath;
    private WebDriver driver;
    private InventoryPage inventoryPage;

    //@BeforeAll
    public static void setUpClass() {
    //    TestConfigs configs = new TestConfigs();
    //    targetUrl = configs.getTargetUrl();
    //    screenshotPath = configs.getScreenshotPath();
    }

    //@BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.inventoryPage = new InventoryPage(driver);
        driver.get(targetUrl);
        CommonActions.login(driver, "standard_user", "secret_sauce");
    }

    //@Tag("regression")
    //@Test
    public void testSortLoHi() throws IOException {
        //Assertions.assertTrue(inventoryPage.isInventoryPage(),
        //       "Not on inventory page");
        inventoryPage.sortLoHi();
        //Assertions.assertTrue(inventoryPage.isLowest(),
        //        "The lowest price is incorrect");
        CommonActions.screenshotWithName(this.driver, screenshotPath+"inventory.png");
    }

    //@AfterEach
    public void tearDown() {
        this.driver.quit();
    }


}
