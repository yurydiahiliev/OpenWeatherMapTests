package com.openweathermap.tests;

import api.resttemplate.BaseRestTemplateClient;
import api.resttemplate.WeatherApi;
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

@ContextConfiguration(classes = BaseRestTemplateClient.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    protected static final String SMOKE = "smoke";
    protected static final String REGRESSION = "regression";
    protected User user = new User("yu.dyagilev@gmail.com", "123456qaz");

    @Autowired
    protected WeatherApi weatherApi;

    private AnnotationConfigApplicationContext applicationContext;


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
        applicationContext = new AnnotationConfigApplicationContext(BaseRestTemplateClient.class);
        Configuration.baseUrl = openWeatherConfig.baseUiUrl();
        Configuration.timeout = openWeatherConfig.timeout();
        Configuration.browser = openWeatherConfig.browser();
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
