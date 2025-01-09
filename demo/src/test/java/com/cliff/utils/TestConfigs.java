package com.cliff.utils;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.Map;


public class TestConfigs {
    private final Yaml yaml;
    private Map<String, Object> configMap;

    public TestConfigs() {
        this.yaml = new Yaml();
    }

    public String getTargetUrl() {
        loadConfig();
        String targetUrl = configMap.get("targetUrl").toString();
        TestLogger.logger.debug("Target url is: [{}]", targetUrl);
        return targetUrl;
    }

    public String getScreenshotPath() {
        loadConfig();
        return configMap.get("screenshotsPath").toString();
    }

    private void loadConfig() {
        InputStream configStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.yaml");

        if (configStream == null) {
            throw new IllegalArgumentException("config.yaml file not found in resources");
        }

        this.configMap = yaml.load(configStream);
    }
}
