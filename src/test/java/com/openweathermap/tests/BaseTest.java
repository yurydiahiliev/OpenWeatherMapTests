package com.openweathermap.tests;

import api.responseModels.WeatherResponse;
import api.resttemplate.BaseRestTemplateClient;
import api.resttemplate.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@ContextConfiguration(classes = BaseRestTemplateClient.class)
public class BaseTest extends AbstractTestNGSpringContextTests {

    private AnnotationConfigApplicationContext applicationContext;

    @Autowired
    private WeatherApi weatherApi;


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BaseRestTemplateClient.class);
    }

    @Test
    public void testUserCanGetCurrentWeatherByCityName() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByCityName("London");

    }
}
