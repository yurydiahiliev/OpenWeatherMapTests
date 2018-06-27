package api.resttemplate;

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
public class WeatherApi extends BaseRestTemplateClient {

    @Autowired
    private RestTemplate restTemplate;

    private UriComponentsBuilder createUriWithAppId() {
        return UriComponentsBuilder.fromHttpUrl(openWeatherConfig.currentWeatherUrl())
                .queryParam("APPID", openWeatherConfig.app_id());
    }

    public  ResponseEntity<WeatherResponse> getWeatherById(long cityId) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppId()
                .queryParam("id", cityId);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);

    }

    public ResponseEntity<WeatherResponse> getWeatherByZipCode(final String zipCode) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppId()
                .queryParam("zip", zipCode);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);
    }

    public ResponseEntity<WeatherResponse> getWeatherByCoordinates(final double lat, final double lon) {
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppId()
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
        UriComponentsBuilder uriComponentsBuilder = createUriWithAppId()
                .queryParam("q", cityName);

        HttpEntity<?> entity = new HttpEntity<>(setHttpHeaders());

        return restTemplate.exchange(
                uriComponentsBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                WeatherResponse.class);
    }
}
