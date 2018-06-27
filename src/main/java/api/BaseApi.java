package api;

import org.aeonbits.owner.ConfigFactory;
import utils.OpenWeatherConfig;

public abstract class BaseApi {

    protected OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
}
