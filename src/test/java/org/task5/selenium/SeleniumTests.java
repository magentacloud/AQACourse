package org.task5.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.task3.env.Env;
import org.task3.env.config.SeleniumConfig;
import org.task5.selenium.PageObjects.*;

import java.time.Duration;
import java.util.Objects;

public class SeleniumTests {
    protected final static SeleniumConfig CONFIG = Env.Selenium.SELENIUM_CONFIG;
    private static WebDriver driver;
    private static PikabuMainPage pikabuMainPage;
    private static PikabuLoginPage pikabuLoginPage;
    private static GooglePage googlePage;
    private static PobedaMainPage pobedaMainPage;

    @BeforeAll
    public static void beforeAll(){
        if(Objects.equals(CONFIG.browserType(), "chrome")){
            ChromeOptions options = new ChromeOptions();

            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--remote-allow-origins=*");

            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            driver = new ChromeDriver(options);
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(CONFIG.pageLoadTimeout()));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(CONFIG.timeout()));
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

    @Test
    public void googlePobedaTest(){
        googlePage = new GooglePage(driver, CONFIG.baseUrlGoogle());
        pobedaMainPage = googlePage.findPobedaMainPage();

        pobedaMainPage.waitForTripToKaliningrad();

        Assertions.assertEquals("Полетели в Калининград!", pobedaMainPage.getTripToKaliningradText());

        pobedaMainPage.changeLanguageToEN();

        Assertions.assertEquals("Ticket search", pobedaMainPage.getTicketSearchEnButtonText());
        Assertions.assertEquals("Online check-in", pobedaMainPage.getOnlineCheckInEnButtonText());
        Assertions.assertEquals("Manage my booking", pobedaMainPage.getManageMyBookingEnButtonText());
    }

    @Test
    public void pobedaModalWindowTest(){
        pobedaMainPage = new PobedaMainPage(driver, CONFIG.baseUrlPobeda());

        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"
                ,pobedaMainPage.getTitle());
        Assertions.assertTrue(pobedaMainPage.logoIsPresentedOnPage());

        Assertions.assertEquals("Подготовка к полёту", pobedaMainPage.getPreparingToFlightHeaderText());
        Assertions.assertEquals("Полезная информация", pobedaMainPage.getUsefulInformationHeaderText());
        Assertions.assertEquals("О компании", pobedaMainPage.getCompanyInformationHeaderText());
    }

    @Test
    public void pobedaSearchWithoutDateTest(){
        pobedaMainPage = new PobedaMainPage(driver, CONFIG.baseUrlPobeda());

        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"
                ,pobedaMainPage.getTitle());
        Assertions.assertTrue(pobedaMainPage.logoIsPresentedOnPage());

        Assertions.assertTrue(pobedaMainPage.pobedaSearchElement.toFieldIsPresentedOnPage());
        Assertions.assertTrue(pobedaMainPage.pobedaSearchElement.fromFieldIsPresentedOnPage());
        Assertions.assertTrue(pobedaMainPage.pobedaSearchElement.dateInFieldIsPresentedOnPage());
        Assertions.assertTrue(pobedaMainPage.pobedaSearchElement.dateOutFieldIsPresentedOnPage());

        pobedaMainPage.pobedaSearchElement.inputFrom("Москва");
        pobedaMainPage.pobedaSearchElement.inputTo("Санкт-Петербург");
        pobedaMainPage.pobedaSearchElement.searchButtonClick();

        Assertions.assertTrue(pobedaMainPage.pobedaSearchElement.isSearchFails());
    }

    @Test
    public void pobedaSearchBookingResults(){
        pobedaMainPage = new PobedaMainPage(driver, CONFIG.baseUrlPobeda());

        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"
                ,pobedaMainPage.getTitle());
        Assertions.assertTrue(pobedaMainPage.logoIsPresentedOnPage());

        pobedaMainPage.bookingManagmentButtonClick();

        Assertions.assertTrue(pobedaMainPage.pobedaBookingElement.clientSurnameFieldIsPresentedOnPage());
        Assertions.assertTrue(pobedaMainPage.pobedaBookingElement.ticketNumberFieldIsPresentedOnPage());
        Assertions.assertTrue(pobedaMainPage.pobedaBookingElement.searchButtonIsPresentedOnPage());

        pobedaMainPage.pobedaBookingElement.inputSurname("Qwerty");
        pobedaMainPage.pobedaBookingElement.inputTicketNumber("XXXXXX");
        PobedaTicketPage ticketPage = pobedaMainPage.pobedaBookingElement.searchButtonClick();

        ticketPage.agreeWithConfidentialPolicy();
        ticketPage.findButtonClick();

        Assertions.assertEquals("Заказ с указанными параметрами не найден", ticketPage.getErrorText());
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }
}
