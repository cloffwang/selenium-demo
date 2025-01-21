package com.cliff.managers;

import com.cliff.utils.ProjLog;
import io.cucumber.java.it.Ma;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ConfigManager {

    private static final Map<String, Object> config;

    static {
        Yaml yaml = new Yaml();
        InputStream configStream = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config.yaml");
        if (configStream == null) {
            throw new IllegalArgumentException("config.yaml file not found in resources");
        }
        config = yaml.load(configStream);
    }

    @SuppressWarnings("unchecked")
    public static String getConfig(String... keys) {
        Map<String, Object> currentLevel = config;
        String value = null;
        for (String key : keys) {
            if (currentLevel.containsKey(key)) {
                Object nextLevel = currentLevel.get(key);
                if (nextLevel instanceof Map<?, ?>) {
                    currentLevel = (Map<String, Object>) nextLevel;
                } else {
                    value = nextLevel.toString();
                }
            } else {
                throw new RuntimeException("Key not found in config: " + key);
            }
        }
        return value;
    }

    public static boolean getBoolean(String... keys) {
        Map<String, Object> currentLevel = config;
        for (int i = 0; i < keys.length - 1; i++) {
            currentLevel = (Map<String, Object>) currentLevel.get(keys[i]);
        }
        return (boolean) currentLevel.get(keys[keys.length - 1]);
    }

    public static List<String> getConfigList(String... keys) {
        Map<String, Object> currentLevel = config;
        for (int i=0; i< keys.length-1;i++) {
            currentLevel = (Map<String, Object>) currentLevel.get(keys[i]);
        }
        return (List<String>) currentLevel.get(keys[keys.length-1]);
    }

    public static List<Map<String, Object>> getConfigListOfMaps(String... keys) {
        Map<String, Object> currentLevel = config;
        for (int i = 0; i < keys.length - 1; i++) {
            currentLevel = (Map<String, Object>) currentLevel.get(keys[i]);
        }
        return (List<Map<String, Object>>) currentLevel.get(keys[keys.length - 1]);
    }

    /*public String getScreenshotPath() {
        loadConfig();
        return configMap.get("screenshotsPath").toString();
    }*/
}
