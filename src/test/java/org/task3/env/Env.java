package org.task3.env;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import org.task3.env.config.ServerConfig;

public final class Env {
    public static class API
    {
        public static final ServerConfig SERVER_CONFIG = ConfigFactory.create(ServerConfig.class);
    }
}
