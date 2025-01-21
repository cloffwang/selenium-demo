package com.cliff.tests;

import com.cliff.managers.ConfigManager;
import com.cliff.utils.ProjLog;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ConfigManagerTest {
    @Test
    public void testGetBrowser(){
        List<Map<String, Object>> browserList = ConfigManager.getConfigListOfMaps("env", "local", "browsers");
        browserList.forEach(browser -> ProjLog.logger.debug(
                "Find browser in env -> local: {}", browser.get("browser")));
        Assert.assertEquals(browserList.size(), 2);
    }
}
