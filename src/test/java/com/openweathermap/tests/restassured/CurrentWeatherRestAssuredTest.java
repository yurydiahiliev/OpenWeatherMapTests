package com.openweathermap.tests.restassured;

import api.responseModels.WeatherResponse;
import com.openweathermap.tests.BaseTest;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

public class CurrentWeatherRestAssuredTest extends BaseTest {

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityName() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByCityName("London")
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityId() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByCityId(2172797)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCoordinates() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByCoordinates(35, 139)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByZipCode() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByZipCode("94040")
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }
}
