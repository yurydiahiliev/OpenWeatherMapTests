package com.openweathermap.tests.resttemplate;

import api.responseModels.WeatherResponse;
import com.openweathermap.tests.LoginRequiredTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.pages.NavigationFooter;
import ui.pages.WeatherPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ui.core.BasePage.at;

public class CurrentWeatherRestTemplateTest extends LoginRequiredTest {

    private WeatherResponse actualWeatherResponse;

    @BeforeClass
    public void createActualWeatherResponse() {
        actualWeatherResponse = at(NavigationFooter.class)
                .openTab(WeatherPage.class, "Weather")
                .searchCity("London")
                .clickFirstLink()
                .getActualWeatherResponse();
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityName() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCityName("London");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        assertThat(expectedWeatherResponse.getMain().getHumidity()).isEqualTo(actualWeatherResponse.getMain().getHumidity());
        assertThat(expectedWeatherResponse.getWeather().get(0).getDescription()).isEqualTo(actualWeatherResponse.getWeather().get(0).getDescription());
        assertThat(expectedWeatherResponse.getCoord().getLat()).isEqualTo(actualWeatherResponse.getCoord().getLat());
        assertThat(expectedWeatherResponse.getCoord().getLon()).isEqualTo(actualWeatherResponse.getCoord().getLon());
        assertThat(expectedWeatherResponse.getBase()).isEqualTo(actualWeatherResponse.getBase());
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherById() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherById(2643743);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        assertThat(expectedWeatherResponse.getMain().getHumidity()).isEqualTo(actualWeatherResponse.getMain().getHumidity());
        assertThat(expectedWeatherResponse.getWeather().get(0).getDescription()).isEqualTo(actualWeatherResponse.getWeather().get(0).getDescription());
        assertThat(expectedWeatherResponse.getCoord().getLat()).isEqualTo(actualWeatherResponse.getCoord().getLat());
        assertThat(expectedWeatherResponse.getCoord().getLon()).isEqualTo(actualWeatherResponse.getCoord().getLon());
        assertThat(expectedWeatherResponse.getBase()).isEqualTo(actualWeatherResponse.getBase());
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCoordinates() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCoordinates(51.51, -0.13);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        assertThat(expectedWeatherResponse.getMain().getHumidity()).isEqualTo(actualWeatherResponse.getMain().getHumidity());
        assertThat(expectedWeatherResponse.getWeather().get(0).getDescription()).isEqualTo(actualWeatherResponse.getWeather().get(0).getDescription());
        assertThat(expectedWeatherResponse.getCoord().getLat()).isEqualTo(actualWeatherResponse.getCoord().getLat());
        assertThat(expectedWeatherResponse.getCoord().getLon()).isEqualTo(actualWeatherResponse.getCoord().getLon());
        assertThat(expectedWeatherResponse.getBase()).isEqualTo(actualWeatherResponse.getBase());
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByZipCode() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByZipCode("WC2N,gb");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        WeatherResponse expectedWeatherResponse = weatherResponse.getBody();

        assertThat(expectedWeatherResponse.getMain().getHumidity()).isEqualTo(actualWeatherResponse.getMain().getHumidity());
        assertThat(expectedWeatherResponse.getWeather().get(0).getDescription()).isEqualTo(actualWeatherResponse.getWeather().get(0).getDescription());
        assertThat(expectedWeatherResponse.getCoord().getLat()).isEqualTo(actualWeatherResponse.getCoord().getLat());
        assertThat(expectedWeatherResponse.getCoord().getLon()).isEqualTo(actualWeatherResponse.getCoord().getLon());
        assertThat(expectedWeatherResponse.getBase()).isEqualTo(actualWeatherResponse.getBase());
    }
}
