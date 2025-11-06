package org.task3.env.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:config/dev/dbConfig.properties"
})
public interface DBConfig extends Config {
    @Key("db.path")
    String dbPath();
}
