package com.openweathermap.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/current_weather_by_name.feature",
        plugin = {"pretty", "html:target/cucumber"},
        glue = "api.stepDefinitions",
        monochrome = true,
        strict = true,
        tags = "@smokeTest")
public class CucumberTest extends AbstractTestNGCucumberTests {
}
