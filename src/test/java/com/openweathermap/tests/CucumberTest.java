package com.openweathermap.tests;

import api.restassured.BaseRestAssuredClient;
import api.stepDefinitions.CucumberSteps;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.springframework.test.context.ContextConfiguration;

@CucumberOptions(features = "src/test/resources/features/current_weather_by_name.feature",
        plugin = {"pretty", "html:target/cucumber"},
        glue = "api.stepDefinitions",
        monochrome = true,
        strict = true)
@ContextConfiguration(classes = {
        BaseRestAssuredClient.class, CucumberSteps.class})
public class CucumberTest extends AbstractTestNGCucumberTests {
}
