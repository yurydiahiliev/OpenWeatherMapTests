package com.openweathermap.tests;

import api.responseModels.WeatherResponse;
import api.resttemplate.BaseRestTemplateClient;
import api.resttemplate.WeatherApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCityName("London");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testUserCanGetCurrentWeatherById() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherById(2172797);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
