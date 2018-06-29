package com.openweathermap.tests;

import api.restassured.BaseRestAssuredClient;
import api.restassured.RestAssuredClient;
import api.resttemplate.BaseRestTemplateClient;
import api.resttemplate.RestTemplateClient;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import ui.models.User;
import ui.utils.AllureSelenide;
import utils.OpenWeatherConfig;
import utils.UserConfig;

@ContextConfiguration(classes = {BaseRestTemplateClient.class, BaseRestAssuredClient.class})
public class BaseTest extends AbstractTestNGSpringContextTests {

    protected static UserConfig userConfig = ConfigFactory.create(UserConfig.class);

    protected static final String SMOKE = "smoke";
    protected static final String REGRESSION = "regression";
    protected static User user = new User(userConfig.email(), userConfig.password());

    @Autowired
    protected RestTemplateClient weatherApi;

    @Autowired
    protected RestAssuredClient restAssuredClient;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
        new AnnotationConfigApplicationContext(BaseRestTemplateClient.class, BaseRestAssuredClient.class);
        Configuration.baseUrl = openWeatherConfig.baseUiUrl();
        Configuration.timeout = openWeatherConfig.timeout();
        Configuration.browser = openWeatherConfig.browser();
        RestAssured.baseURI = openWeatherConfig.baseUrl();
    }

    @BeforeClass(alwaysRun = true)
    public void addSelenideListener(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        RestAssured.reset();
    }
}
