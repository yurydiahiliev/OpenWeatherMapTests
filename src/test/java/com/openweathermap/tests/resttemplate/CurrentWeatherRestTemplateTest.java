package com.openweathermap.tests.resttemplate;

import api.responseModels.WeatherResponse;
import com.codeborne.selenide.Condition;
import com.openweathermap.tests.BaseTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;
import ui.pages.NavigationFooter;
import ui.pages.SignInPage;
import ui.pages.WeatherPage;

import static org.assertj.core.api.Assertions.assertThat;
import static ui.core.BasePage.at;
import static ui.core.BasePage.open;

public class CurrentWeatherRestTemplateTest extends BaseTest {

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCityName() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCityName("London");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        open(SignInPage.class)
                .signInAsUser(user)
                .getNotificationAlertElement()
                .shouldHave(Condition.text("Signed in successfully."));

       int humibity = at(NavigationFooter.class)
                .openTab(WeatherPage.class, "Weather")
                .searchCity("London")
                .clickFirstLink()
                .getHumidity();

       assertThat(weatherResponse.getBody().getMain().getHumidity()).isEqualTo(humibity);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherById() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherById(2172797);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByCoordinates() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByCoordinates(35, 139);
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test(groups = {SMOKE})
    public void testUserCanGetCurrentWeatherByZipCode() {
        ResponseEntity<WeatherResponse> weatherResponse = weatherApi.getWeatherByZipCode("94040");
        assertThat(weatherResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
