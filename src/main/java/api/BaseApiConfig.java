package api;

import org.aeonbits.owner.ConfigFactory;
import utils.OpenWeatherConfig;


public abstract class BaseApiConfig {

    protected static OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
}
