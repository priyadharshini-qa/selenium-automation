package com.saucedemo.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    // Where your .feature files live
    features = "src/test/resources/features",

    // Where Cucumber should look for step definitions AND hooks
    glue = {
        "com.saucedemo.stepdefinitions",
        "com.saucedemo.hooks"
    },

    // Reports to generate
    plugin = {
        "pretty",                                          // readable console output
        "html:target/cucumber-reports/report.html",        // HTML report
        "json:target/cucumber.json",                        // for maven-cucumber-reporting
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm" // Allure report
    },

    // Show only steps that are defined (no undefined noise)
    dryRun = false,

    // Show full stack trace on failure
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // Empty — AbstractTestNGCucumberTests does all the work
}