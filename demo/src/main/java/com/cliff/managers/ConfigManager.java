package com.cliff.managers;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
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

    /*public String getScreenshotPath() {
        loadConfig();
        return configMap.get("screenshotsPath").toString();
    }*/
}
