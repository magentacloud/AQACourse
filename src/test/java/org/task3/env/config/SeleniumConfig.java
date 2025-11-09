package org.task3.env.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:env",
        "system:properties",
        "classpath:config/dev/seleniumConfig.properties"
})
public interface SeleniumConfig extends Config {
    @Key("baseUrlPikabu")
    String baseUrlPikabu();

    @Key("browser.type")
    String browserType();

    @Key("browser.width")
    Integer browserWidth();

    @Key("browser.height")
    Integer browserhHeight();



    @Key("browser.headless")
    Boolean browserHeadless();

    @Key("timeout")
    Integer timeout();

    @Key("pageLoadTimeout")
    Integer pageLoadTimeout();

    @Key("screenshots")
    Boolean screenshots();

    @Key("savePageSource")
    Boolean savePageSource();

    @Key("reportsFolder")
    String reportsFolder();
}
