package com.cliff.tests.login;

import com.cliff.pages.LoginPage;
import com.cliff.common.CommonActions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private static String targetUrl;

    //@BeforeAll
    public static void setUpClass(){
    //    TestConfigs configs = new TestConfigs();
    //    targetUrl = configs.getTargetUrl();
    }

    //@BeforeEach
    public void setUP(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.loginPage = new LoginPage(driver);
    }

    //@Tag("smoke")
    //@Test
    public void testLogin(){
        driver.get(targetUrl);
        //Assertions.assertTrue(loginPage.isLoginPage(), "It's not login page");
        CommonActions.login(
                driver, "standard_user", "secret_sauce");
    }

    //@AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}

