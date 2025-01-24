package com.cliff.runner;


import com.cliff.managers.ConfigManager;
import io.cucumber.testng.PickleWrapper;

import java.util.List;
import java.util.Map;

public class TestDataLoader {
    private Object[][] cucumberScenarios;

    public void feedTestData(Object[][] cucumberScenarios, String env) {
        List<Map<String, Object>> configs = ConfigManager.getConfigListOfMaps("env", env, "browsers");
        BrowserData[] browserData = new BrowserData[configs.size()];
        int i = 0;
        for (Map<String, Object> config : configs) {
            browserData[i++] = new BrowserData(
                    (String)config.get("browser"),
                    (String)config.get("version"),
                    ConfigManager.getBoolean("env", env, "headless"),
                    ConfigManager.getBoolean("env", env, "browserStack"),
                    ConfigManager.getBoolean("env", env, "grid"),
                    ConfigManager.getConfig("env", env, "hubUrl")
            );
        }
        Object[][] newScenarios = new Object[cucumberScenarios.length*browserData.length][2];
        for (int j = 0; j<cucumberScenarios.length; j++) {
            for (int k=0; k< browserData.length; k++) {
                int index = j+k*cucumberScenarios.length;
                PickerWithData pickerWithData = new PickerWithData((PickleWrapper) cucumberScenarios[j][0], browserData[k]);
                newScenarios[index][0] = pickerWithData;
                newScenarios[index][1] = cucumberScenarios[j][1];
            }
        }

        this.cucumberScenarios = newScenarios;
    }

    public Object[][] getCucumberScenarios() {
        return cucumberScenarios;
    }
}
