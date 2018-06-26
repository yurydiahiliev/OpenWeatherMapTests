package api;

import org.aeonbits.owner.ConfigFactory;
import utils.OpenWeatherConfig;

public abstract class BaseApi<T> {

    protected OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
}
