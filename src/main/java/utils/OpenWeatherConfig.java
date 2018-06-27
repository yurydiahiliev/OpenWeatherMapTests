package utils;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.properties")
public interface OpenWeatherConfig extends Config {

    String baseUrl();

    String currentWeatherUrl();

    String forecastUrl();

    String app_id();

    String baseUiUrl();

    String browser();

    int timeout();
}
