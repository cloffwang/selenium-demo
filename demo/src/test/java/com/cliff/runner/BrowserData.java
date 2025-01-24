package com.cliff.runner;

public class BrowserData {
    public String browser;
    public String version;
    public boolean isHeadless;
    public boolean isBrowserStack;
    public boolean isGrid;
    public String hubUrl;

    public BrowserData(
            String browser, String version, Boolean isHeadless,
            Boolean isBrowserStack, Boolean isGrid, String hubUrl) {
        this.browser = browser;
        this.version = version;
        this.isBrowserStack = isBrowserStack;
        this.isGrid = isGrid;
        this.isHeadless = isHeadless;
        this.hubUrl = hubUrl;
    }
}
