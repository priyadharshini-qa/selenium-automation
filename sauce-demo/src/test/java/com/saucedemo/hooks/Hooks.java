package com.saucedemo.hooks;

import com.saucedemo.config.ConfigReader;
import com.saucedemo.utils.DriverManager;
import com.saucedemo.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    // Runs before every scenario → opens browser, navigates to base URL
    @Before
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getBaseUrl());
    }

    // Runs after every scenario → screenshot on failure, then closes browser
    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.captureScreenshot();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverManager.quitDriver();
    }
}