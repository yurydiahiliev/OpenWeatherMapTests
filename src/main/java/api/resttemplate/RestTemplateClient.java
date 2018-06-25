package api.resttemplate;

import org.aeonbits.owner.ConfigFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import utils.OpenWeatherConfig;

public class RestTemplateClient {

    private OpenWeatherConfig cfg = ConfigFactory.create(OpenWeatherConfig.class);

    private String base_api_url = cfg.baseUrl();
    private String app_id = cfg.app_id();

    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;

    public RestTemplateClient() {
        restTemplate = new RestTemplate();
        httpHeaders = new HttpHeaders();
    }
}
