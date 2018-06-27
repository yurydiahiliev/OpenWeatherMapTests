package com.openweathermap.tests.unit;

import api.responseModels.WeatherResponse;
import com.openweathermap.tests.BaseTest;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.core.PageUrl;
import ui.pages.MainPage;
import ui.pages.SignInPage;
import ui.pages.TestPage;
import ui.pages.WeatherPage;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void testRestAssuredClient() {
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
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testExceptionUiEntities() {
        open(TestPage.class);
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

}
