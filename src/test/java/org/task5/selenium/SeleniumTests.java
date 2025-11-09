package org.task5.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.task3.env.Env;
import org.task3.env.config.SeleniumConfig;
import org.task5.selenium.PageObjects.PikabuLoginPage;
import org.task5.selenium.PageObjects.PikabuMainPage;

import java.time.Duration;
import java.util.Objects;

public class SeleniumTests {
    protected final static SeleniumConfig CONFIG = Env.Selenium.SELENIUM_CONFIG;
    private static WebDriver driver;
    private static PikabuMainPage pikabuMainPage;
    private static PikabuLoginPage pikabuLoginPage;

    @BeforeAll
    public static void beforeAll(){
        if(Objects.equals(CONFIG.browserType(), "chrome"))
            driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(CONFIG.pageLoadTimeout()));
        driver.manage().window().setSize(new Dimension(CONFIG.browserWidth(), CONFIG.browserhHeight()));
    }

    @Test
    public void test(){
        pikabuMainPage = new PikabuMainPage(driver, CONFIG.baseUrlPikabu());

        Assertions.assertEquals("Горячее – самые интересные и обсуждаемые посты | Пикабу", pikabuMainPage.getTitle());

        pikabuLoginPage = pikabuMainPage.openLoginWindow();

        String loginError =  pikabuLoginPage.loginWithIncorrectCredentials("qwerty", "qwerty");

        Assertions.assertEquals("Ошибка. Вы ввели неверные данные авторизации", loginError);
    }

    @AfterEach
    public void teardown(){
        driver.close();
    }
}
