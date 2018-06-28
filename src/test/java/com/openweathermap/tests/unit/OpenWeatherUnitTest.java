package com.openweathermap.tests.unit;

import api.responseModels.ForecastResponse;
import api.responseModels.WeatherResponse;
import com.codeborne.selenide.Condition;
import com.openweathermap.tests.BaseTest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
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
public class OpenWeatherUnitTest extends BaseTest {

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
    public void testRestAssuredClientGetWeatherByCityNameMethod() {
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
    public void testRestAssuredClientGetWeatherByCoordinatesMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByCoordinates(39, 135);
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestAssuredClientGetWeatherByZipCodeMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByZipCode("94040");
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getSys().getSunset()).isNotNull();
    }

    @Test
    public void testRestAssuredClientGetWeatherByCityIdMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getWeatherByCityId(2172797);
        WeatherResponse weatherResponse = validatableResponse
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestAssuredClientGetForecastByCityNameMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getForecastByCityName("New York");
        ForecastResponse forecastResponse = validatableResponse
                .extract()
                .as(ForecastResponse.class);
        assertThat(forecastResponse).isNotNull();
        assertThat(forecastResponse.getCod()).isEqualTo("200");
        assertThat(forecastResponse.getCity().getName()).isEqualTo("New York");
        assertThat(forecastResponse.getCity().getCountry()).isEqualTo("US");
        assertThat(forecastResponse.getMessage()).isNotNull();
        assertThat(forecastResponse.getCnt()).isNotNull();

        assertThat(forecastResponse.getList().get(0).getDt()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getDtTxt()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getHumidity()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getTemp()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getTempMax()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getTempMin()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getPressure()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getGrndLevel()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getSeaLevel()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getMain().getTempKf()).isNotNull();

        assertThat(forecastResponse.getList().get(0).getClouds().getAll()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getWind().getSpeed()).isNotNull();
        assertThat(forecastResponse.getList().get(0).getWind().getDeg()).isNotNull();
    }

    @Test
    public void testRestAssuredClientGetForecastByCoordinatesMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getForecastByCoordinates(39, 135);
        ForecastResponse forecastResponse = validatableResponse
                .extract()
                .as(ForecastResponse.class);
        assertThat(forecastResponse).isNotNull();
    }

    @Test
    public void testRestAssuredClientGetForecastByZipCodeMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getForecastByZipCode("94040");
        ForecastResponse forecastResponse = validatableResponse
                .extract()
                .as(ForecastResponse.class);
        assertThat(forecastResponse).isNotNull();
    }

    @Test
    public void testRestAssuredClientGetForecastByCityIdMethod() {
        ValidatableResponse validatableResponse = restAssuredClient.getForecastByCityId(2172797);
        ForecastResponse forecastResponse = validatableResponse
                .extract()
                .as(ForecastResponse.class);
        assertThat(forecastResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetWeatherByCityNameMethod() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByCityName("London").getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetWeatherByZipCodeMethod() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByZipCode("94040").getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetWeatherByCoordinatesMethod() {
        WeatherResponse weatherResponse = weatherApi.getWeatherByCoordinates(39, 135).getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetWeatherByCityIdMethod() {
        WeatherResponse weatherResponse = weatherApi.getWeatherById(2172797).getBody();
        assertThat(weatherResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetForecastByCityNameMethod() {
        ForecastResponse forecastResponse = weatherApi.getForecastByCityName("London").getBody();
        assertThat(forecastResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetForecastByZipCodeMethod() {
        ForecastResponse forecastResponse = weatherApi.getForecastByZipCode("94040").getBody();
        assertThat(forecastResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetForecastByCoordinatesMethod() {
        ForecastResponse forecastResponse = weatherApi.getForecastByCoordinates(39, 135).getBody();
        assertThat(forecastResponse).isNotNull();
    }

    @Test
    public void testRestTemplateClientGetForecastByCityIdMethod() {
        ForecastResponse forecastResponse = weatherApi.getForecastById(2172797).getBody();
        assertThat(forecastResponse).isNotNull();
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptionUiEntities() {
        open(TestPage.class);
    }

    @Test
    public void testUiSignInProcess() {
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
    public void testSuccessGettingPageAnnotation(Class tClass) {
        assertThat(tClass).hasAnnotation(PageUrl.class);
    }

    @Test
    public void testSearchResultsPage() {
        open(MainPage.class);
        at(NavigationFooter.class)
                .openTab(WeatherPage.class, "Weather")
                .searchCity("London")
                .clickFirstLink()
                .getActualWeatherResponse();
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
