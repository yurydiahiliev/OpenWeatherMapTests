package com.openweathermap.tests.resttemplate;

import api.responseModels.ForecastResponse;
import api.responseModels.WeatherResponse;
import com.openweathermap.tests.LoginRequiredTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.MainPage;
import ui.pages.NavigationFooter;
import ui.pages.WeatherPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ui.core.BasePage.at;
import static ui.core.BasePage.open;

public class CurrentWeatherRestTemplateTest extends LoginRequiredTest {

    private WeatherResponse actualWeatherResponse;

    @BeforeClass(alwaysRun = true)
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

    @Feature(value = "Current weather")
    @Story(value = "Getting current weather by city name")
    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityName() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCityName("London");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        checkAvailableParameters(expectedWeatherResponse);
    }

    @Feature(value = "Current weather")
    @Story(value = "Getting current weather by city id")
    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherById() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherById(2643743);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        checkAvailableParameters(expectedWeatherResponse);
    }

    @Feature(value = "Current weather")
    @Story(value = "Getting current weather by geographical coordinates")
    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCoordinates() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCoordinates(51.51, -0.13);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        checkAvailableParameters(expectedWeatherResponse);
    }

    @Feature(value = "Current weather")
    @Story(value = "Getting current weather by zip code")
    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByZipCode() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByZipCode("WC2N,gb");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        checkAvailableParameters(expectedWeatherResponse);
    }

    @Feature(value = "Forecast weather")
    @Story(value = "Getting forecast by city name")
    @Test(groups = {SMOKE})
    public void testUserCanGetForeCastByCityName() {
        ResponseEntity<ForecastResponse> weatherResponse = weatherApi.getForecastByCityName("London");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ForecastResponse expectedWeatherResponse = weatherResponse.getBody();
        assertThat(expectedWeatherResponse).isNotNull();

    }

    @Feature(value = "Forecast weather")
    @Story(value = "Getting forecast weather by city id")
    @Test(groups = {SMOKE})
    public void testUserCanGetForecastById() {
        ResponseEntity<ForecastResponse> weatherResponse = weatherApi.getForecastById(2643743);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ForecastResponse expectedWeatherResponse = weatherResponse.getBody();
        assertThat(expectedWeatherResponse).isNotNull();

    }

    @Feature(value = "Forecast weather")
    @Story(value = "Getting forecast weather by geographical coordinates")
    @Test(groups = {SMOKE})
    public void testUserCanGetForecastByCoordinates() {
        ResponseEntity<ForecastResponse> weatherResponse = weatherApi.getForecastByCoordinates(51.51, -0.13);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ForecastResponse expectedWeatherResponse = weatherResponse.getBody();
        assertThat(expectedWeatherResponse).isNotNull();

    }

    @Feature(value = "Forecast weather")
    @Story(value = "Getting forecast weather by zip code")
    @Test(groups = {SMOKE})
    public void testUserCanGetForecastByZipCode() {
        ResponseEntity<ForecastResponse> weatherResponse = weatherApi.getForecastByZipCode("WC2N,gb");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        ForecastResponse expectedWeatherResponse = weatherResponse.getBody();
        assertThat(expectedWeatherResponse).isNotNull();
    }
}
