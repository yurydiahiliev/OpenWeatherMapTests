package api;

import org.aeonbits.owner.ConfigFactory;
import utils.OpenWeatherConfig;
import utils.UserConfig;


public abstract class BaseApiConfig {

    protected static OpenWeatherConfig openWeatherConfig = ConfigFactory.create(OpenWeatherConfig.class);
    protected static UserConfig userConfig = ConfigFactory.create(UserConfig.class);
}
