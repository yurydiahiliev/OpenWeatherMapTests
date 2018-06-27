package com.openweathermap.tests.unit;

import api.responseModels.WeatherResponse;
import com.codeborne.selenide.Condition;
import com.openweathermap.tests.BaseTest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.InvalidSelectorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.core.PageUrl;
import ui.pages.*;
import utils.OpenWeatherConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static ui.core.BasePage.at;
import static ui.core.BasePage.open;

@RunWith(MockitoJUnitRunner.class)
public class RestAssuredClientUnitTest extends BaseTest {

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private RequestSpecification requestSpecification;


    @Test
    public void testRequestSpecificationConfig() {
        ValidatableResponse validatableResponse = requestSpecification.get("/").then();
        assertThat(validatableResponse.extract().body()).isNotNull();
    }

    @Test
    public void testRestAssuredClient1() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByCityName("New York");
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse.getCoord()).isNotNull();
        assertThat(weatherResponse.getClouds().getAll()).isGreaterThan(0);
        assertThat(weatherResponse.getBase()).isEqualTo("stations");
        assertThat(weatherResponse.getWeather().size()).isGreaterThan(0);
        assertThat(weatherResponse.getMain()).isNotNull();

        assertThat(weatherResponse.getVisibility()).isNotNull();
        assertThat(weatherResponse.getWind()).isNotNull();
        assertThat(weatherResponse.getCod()).isNotNull();
        assertThat(weatherResponse.getDt()).isNotNull();
        assertThat(weatherResponse.getId()).isNotNull();
        assertThat(weatherResponse.getSys()).isNotNull();
        assertThat(weatherResponse.getMain().getTempMin()).isNotNull();
        assertThat(weatherResponse.getMain().getTempMax()).isNotNull();
        assertThat(weatherResponse.getMain().getTemp()).isNotNull();
        assertThat(weatherResponse.getMain().getHumidity()).isNotNull();
        assertThat(weatherResponse.getSys().getId()).isNotNull();
        assertThat(weatherResponse.getSys().getCountry()).isNotNull();
        assertThat(weatherResponse.getSys().getMessage()).isNotNull();
        assertThat(weatherResponse.getSys().getSunrise()).isNotNull();
        assertThat(weatherResponse.getSys().getSunset()).isNotNull();

        assertThat(weatherResponse.getWeather().get(0).getDescription()).isNotNull();
        assertThat(weatherResponse.getWeather().get(0).getIcon()).isNotNull();
        assertThat(weatherResponse.getWeather().get(0).getId()).isNotNull();
        assertThat(weatherResponse.getWeather().get(0).getMain()).isNotNull();

        assertThat(weatherResponse.getWind().getDeg()).isNotNull();
        assertThat(weatherResponse.getWind().getSpeed()).isNotNull();

        assertThat(weatherResponse.getCoord().getLat()).isNotNull();
        assertThat(weatherResponse.getCoord().getLon()).isNotNull();

        assertThat(weatherResponse.getClouds().getAll()).isNotNull();
    }

    @Test
    public void testRestAssuredClient2() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByCoordinates(39, 135);
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestAssuredClient3() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByZipCode("94040");
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getSys().getSunset()).isNotNull();
    }

    @Test
    public void testRestAssuredClient4() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByCityId(2172797);
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClient1() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByCityName("London").getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClient2() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByZipCode("94040").getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClient3() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByCoordinates(39, 135).getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClient4() {
        WeatherResponse weatherResponse = weatherApi.getWeatherById(2172797).getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptionUiEntities() {
        open(TestPage.class);
    }

    @Test
    public void testUiPages() {
        open(SignInPage.class)
                .signInAsUser(user)
                .getNotificationAlertElement()
                .shouldHave(Condition.text("Signed in successfully."));
    }

    @DataProvider
    public static Object[][] pageClasses() {
        return new Object[][] {
                { MainPage.class },
                { SignInPage.class },
                { WeatherPage.class }
        };
    }

    @Test(dataProvider = "pageClasses")
    public void testSuccessUiEntities(Class tClass) {
        assertThat(tClass).hasAnnotation(PageUrl.class);
    }

    @Test(expectedExceptions = InvalidSelectorException.class)
    public void testNullArgumentsInNavigationFooter() {
        at(NavigationFooter.class)
                .openTab(null, null);
    }

    @Test
    public void testConfigReceiving() {
        OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
        assertThat(openWeatherConfig.app_id()).isNotEmpty();
        assertThat(openWeatherConfig.baseUiUrl()).isNotEmpty();
        assertThat(openWeatherConfig.baseUrl()).isNotEmpty();
        assertThat(openWeatherConfig.currentWeatherUrl()).isNotEmpty();
        assertThat(openWeatherConfig.timeout()).isNotNull();
    }

}
