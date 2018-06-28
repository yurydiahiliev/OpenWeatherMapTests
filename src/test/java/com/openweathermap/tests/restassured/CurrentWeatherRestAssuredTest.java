package com.openweathermap.tests.restassured;

import api.responseModels.ForecastResponse;
import api.responseModels.WeatherResponse;
import com.openweathermap.tests.LoginRequiredTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.MainPage;
import ui.pages.NavigationFooter;
import ui.pages.WeatherPage;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static ui.core.BasePage.at;
import static ui.core.BasePage.open;

public class CurrentWeatherRestAssuredTest extends LoginRequiredTest {

    private WeatherResponse actualWeatherResponse;

    @BeforeClass
    public void createActualWeatherResponse() {
        open(MainPage.class);
        actualWeatherResponse = at(NavigationFooter.class)
                .openTab(WeatherPage.class, "Weather")
                .searchCity("London")
                .clickFirstLink()
                .getActualWeatherResponse();
    }

    private void checkAvailableParameters(WeatherResponse expectedWeatherResponse) {
        assertThat(expectedWeatherResponse.getMain().getHumidity()).isEqualTo(actualWeatherResponse.getMain().getHumidity());
        assertThat(expectedWeatherResponse.getWeather().get(0).getDescription()).isEqualTo(actualWeatherResponse.getWeather().get(0).getDescription());
        assertThat(expectedWeatherResponse.getCoord().getLat()).isEqualTo(actualWeatherResponse.getCoord().getLat());
        assertThat(expectedWeatherResponse.getCoord().getLon()).isEqualTo(actualWeatherResponse.getCoord().getLon());
        assertThat(expectedWeatherResponse.getBase()).isEqualTo(actualWeatherResponse.getBase());
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityName() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByCityName("London")
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();

        checkAvailableParameters(weatherResponse);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityId() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByCityId(2643743)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();

        checkAvailableParameters(weatherResponse);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCoordinates() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByCoordinates(51.51, -0.13)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();

        checkAvailableParameters(weatherResponse);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByZipCode() {
        WeatherResponse weatherResponse = restAssuredClient.getWeatherByZipCode("WC2N,gb")
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/weatherResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();

        checkAvailableParameters(weatherResponse);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetForecastByCityName() {
        ForecastResponse weatherResponse = restAssuredClient.getForecastByCityName("London")
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/forecastResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(ForecastResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetForecastByCityId() {
        ForecastResponse weatherResponse = restAssuredClient.getForecastByCityId(2643743)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/forecastResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(ForecastResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetForecastByCoordinates() {
        ForecastResponse weatherResponse = restAssuredClient.getForecastByCoordinates(51.51, -0.13)
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/forecastResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(ForecastResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetForecastByZipCode() {
        ForecastResponse weatherResponse = restAssuredClient.getForecastByZipCode("WC2N,gb")
                .assertThat()
                .body(matchesJsonSchemaInClasspath("jsonSchema/forecastResponseJsonSchema.json"))
                .statusCode(200)
                .extract()
                .as(ForecastResponse.class);
        assertThat(weatherResponse).isNotNull();
    }
}
