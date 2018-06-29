package utils;

import api.BaseApiConfig;
import ui.models.User;

public class Users extends BaseApiConfig {

    public static final User ADMIN = new User(userConfig.email(), userConfig.password());
}
