package org.task3.env.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:config/dev/serverConfig.properties"
})
public interface ServerConfig extends Config {
    @Key("server.url")
    String serverUrl();
}
