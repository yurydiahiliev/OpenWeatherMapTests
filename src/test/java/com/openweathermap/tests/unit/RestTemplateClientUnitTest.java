package com.openweathermap.tests.unit;

import api.responseModels.ForecastResponse;
import api.responseModels.WeatherResponse;
import api.restassured.BaseRestAssuredClient;
import api.resttemplate.BaseRestTemplateClient;
import api.resttemplate.RestTemplateClient;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.aeonbits.owner.ConfigFactory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.OpenWeatherConfig;

@ContextConfiguration(classes = {BaseRestTemplateClient.class, BaseRestAssuredClient.class})
@RunWith(MockitoJUnitRunner.class)
public class RestTemplateClientUnitTest extends AbstractTestNGCucumberTests {

    private OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);

    @BeforeClass
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private RestTemplateClient weatherApi;

    @Mock
    @Autowired
    private RestTemplate restTemplate;


    private UriComponentsBuilder queryParamsForCurrentWeather() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.currentWeatherUrl())
                .queryParam("APPID", openWeatherConfig.app_id());
    }

    private UriComponentsBuilder queryParamsForForecast() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.forecastUrl())
                .queryParam("APPID", openWeatherConfig.app_id());
    }

    private HttpHeaders setHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        return httpHeaders;
    }

    @Test
    public void testGetWeatherByCityNameMethod() {
        final String cityName = "London";
        weatherApi.getWeatherByCityName(cityName);

        HttpEntity<WeatherResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForCurrentWeather().queryParam("q", cityName).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(WeatherResponse.class));
    }

    @Test
    public void testGetWeatherByCityIdMethod() {
        final long cityId = 2172797;
        weatherApi.getWeatherById(cityId);

        HttpEntity<WeatherResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForCurrentWeather().queryParam("id", cityId).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(WeatherResponse.class));
    }

    @Test
    public void testGetWeatherByGeographicalCoordinatesMethod() {
        final double lat = 39.0;
        final double lot = 135.0;

        weatherApi.getWeatherByCoordinates(lat, lot);

        HttpEntity<WeatherResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForCurrentWeather().queryParam("lat", lat)
                                .queryParam("lon", lot).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(WeatherResponse.class));
    }

    @Test
    public void testGetWeatherByZipCodeMethod() {
        final String zipCode = "94040";

        weatherApi.getWeatherByZipCode(zipCode);

        HttpEntity<WeatherResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForCurrentWeather().queryParam("zip", zipCode).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(WeatherResponse.class));
    }

    @Test
    public void testForecastByCityNameMethod() {
        final String cityName = "London";
        weatherApi.getForecastByCityName(cityName);

        HttpEntity<ForecastResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForForecast().queryParam("q", cityName).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(ForecastResponse.class));
    }

    @Test
    public void testForecastByCityIdMethod() {
        final long cityId = 2172797;
        weatherApi.getForecastById(cityId);

        HttpEntity<ForecastResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForForecast().queryParam("id", cityId).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(ForecastResponse.class));
    }

    @Test
    public void testForecastByGeographicalCoordinatesMethod() {
        final double lat = 39.0;
        final double lot = 135.0;

        weatherApi.getForecastByCoordinates(lat, lot);

        HttpEntity<ForecastResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForForecast().queryParam("lat", lat)
                                .queryParam("lon", lot).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(ForecastResponse.class));
    }

    @Test
    public void testGetForecastByZipCodeMethod() {
        final String zipCode = "94040";

        weatherApi.getForecastByZipCode(zipCode);

        HttpEntity<ForecastResponse> entity = new HttpEntity<>(setHttpHeaders());

        Mockito.verify(restTemplate, Mockito.times(1))
                .exchange(
                        Mockito.eq(queryParamsForForecast().queryParam("zip", zipCode).toUriString()),
                        Mockito.eq(HttpMethod.GET),
                        Mockito.eq(entity),
                        Mockito.eq(ForecastResponse.class));
    }

}
