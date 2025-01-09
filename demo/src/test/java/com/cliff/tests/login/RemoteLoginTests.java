package com.cliff.tests.login;

import com.cliff.pages.LoginPage;
import com.cliff.utils.CommonActions;
import com.cliff.utils.GuiceExtension;
import com.cliff.utils.TestConfigs;
import com.google.inject.Inject;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(GuiceExtension.class)
public class RemoteLoginTests {
    @Inject
    private WebDriver driver;
    private LoginPage loginPage;
    private static String targetUrl;

    @BeforeAll
    public static void setUpClass(){
        TestConfigs configs = new TestConfigs();
        targetUrl = configs.getTargetUrl();
    }

    @BeforeEach
    public void setUP(){
        this.loginPage = new LoginPage(driver);
    }

    @Tag("smoke")
    @Test
    public void testLogin(){
        driver.get(targetUrl);
        Assertions.assertTrue(loginPage.isLoginPage(), "It's not login page");
        CommonActions.login(
                driver, "standard_user", "secret_sauce");
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}