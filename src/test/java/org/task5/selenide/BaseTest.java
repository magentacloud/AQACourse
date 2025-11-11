package org.task5.selenide;

import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import org.task3.env.Env;
import org.task3.env.config.SeleniumConfig;

public class BaseTest {
    private static final SeleniumConfig CONFIG = Env.Selenium.SELENIUM_CONFIG;

    @BeforeAll
    public static void setUp(){

        Configuration.browser = CONFIG.browserType();
        Configuration.browserSize = CONFIG.browserWidth() + "x"
                + CONFIG.browserhHeight();
        Configuration.headless = CONFIG.browserHeadless();
        Configuration.timeout = CONFIG.timeout();
        Configuration.pageLoadTimeout = CONFIG.pageLoadTimeout();
        Configuration.screenshots = CONFIG.screenshots();
        Configuration.savePageSource = CONFIG.savePageSource();
        Configuration.reportsFolder = CONFIG.reportsFolder();
    }
}
