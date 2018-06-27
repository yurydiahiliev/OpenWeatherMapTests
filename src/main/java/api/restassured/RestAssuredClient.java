package api.restassured;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class RestAssuredClient extends BaseRestAssuredClient {

    @Autowired
    private RequestSpecification requestSpecification;

    public ValidatableResponse getWeatherByCityName(final String cityName) {
        return requestSpecification
                .when()
                .queryParam("q", cityName)
                .get(openWeatherConfig.currentWeatherUrl())
                .then();
    }

    public ValidatableResponse getWeatherByCityId(final long cityId) {
        return requestSpecification
                .when()
                .queryParam("id", cityId)
                .get(openWeatherConfig.currentWeatherUrl())
                .then();
    }

    public ValidatableResponse getWeatherByZipCode(final String zipCode) {
        return requestSpecification
                .when()
                .queryParam("zip", zipCode)
                .get(openWeatherConfig.currentWeatherUrl())
                .then();
    }

    public ValidatableResponse getWeatherByCoordinates(final double lat, final double lon) {
        return requestSpecification
                .when()
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .get(openWeatherConfig.currentWeatherUrl())
                .then();
    }

    public ValidatableResponse getForecastByCityName(final String cityName) {
        return requestSpecification
                .when()
                .queryParam("q", cityName)
                .get(openWeatherConfig.forecastUrl())
                .then();
    }

    public ValidatableResponse getForecastByCityId(final long cityId) {
        return requestSpecification
                .when()
                .queryParam("id", cityId)
                .get(openWeatherConfig.forecastUrl())
                .then();
    }

    public ValidatableResponse getForecastByZipCode(final String zipCode) {
        return requestSpecification
                .when()
                .queryParam("zip", zipCode)
                .get(openWeatherConfig.forecastUrl())
                .then();
    }

    public ValidatableResponse getForecastByCoordinates(final double lat, final double lon) {
        return requestSpecification
                .when()
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .get(openWeatherConfig.forecastUrl())
                .then();
    }
}
