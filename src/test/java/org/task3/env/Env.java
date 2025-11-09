package org.task3.env;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.task3.env.config.DBConfig;
import org.task3.env.config.SeleniumConfig;
import org.task3.env.config.ServerConfig;

public final class Env {
    public static class API
    {
        public static final ServerConfig SERVER_CONFIG = ConfigFactory.create(ServerConfig.class);
    }

    public static class DB
    {
        public static final DBConfig DB_CONFIG = ConfigFactory.create(DBConfig.class);
    }

    public static class Selenium
    {
        public static final SeleniumConfig SELENIUM_CONFIG = ConfigFactory.create(SeleniumConfig.class);
    }
}
