package api.resttemplate;

import api.responseModels.ForecastResponse;
import api.responseModels.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Configurable
public class RestTemplateClient extends BaseRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    private UriComponentsBuilder createUriWithAppIdForCurrentWeather() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.currentWeatherUrl())
                .queryParam("APPID", openWeatherConfig.app_id());
    }

    private UriComponentsBuilder createUriWithAppIdForForecast() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.forecastUrl())
                .queryParam("APPID", openWeatherConfig.app_id());
    }

    public ResponseEntity<WeatherResponse> getWeatherById(long cityId) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForCurrentWeather()
                .queryParam("id", cityId);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);
    }

    public ResponseEntity<WeatherResponse> getWeatherByZipCode(final String zipCode) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForCurrentWeather()
                .queryParam("zip", zipCode);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);
    }

    public ResponseEntity<WeatherResponse> getWeatherByCoordinates(final double lat, final double lon) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForCurrentWeather()
                .queryParam("lat", lat)
                .queryParam("lon", lon);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);
    }

    public ResponseEntity<WeatherResponse> getWeatherByCityName(final String cityName) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForCurrentWeather()
                .queryParam("q", cityName);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);
    }

    public  ResponseEntity<ForecastResponse> getForecastById(long cityId) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForForecast()
                .queryParam("id", cityId);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                ForecastResponse.class);

    }

    public ResponseEntity<ForecastResponse> getForecastByZipCode(final String zipCode) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForForecast()
                .queryParam("zip", zipCode);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                ForecastResponse.class);
    }

    public ResponseEntity<ForecastResponse> getForecastByCoordinates(final double lat, final double lon) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForForecast()
                .queryParam("lat", lat)
                .queryParam("lon", lon);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                ForecastResponse.class);
    }

    public ResponseEntity<ForecastResponse> getForecastByCityName(final String cityName) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppIdForForecast()
                .queryParam("q", cityName);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                ForecastResponse.class);
    }
}
