package utils;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:user.properties")
public interface UserConfig extends Config {

    String email();

    String password();
}
