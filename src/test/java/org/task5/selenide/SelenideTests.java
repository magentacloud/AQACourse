package org.task5.selenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.task3.env.Env;
import org.task3.env.config.SeleniumConfig;
import org.task5.selenide.PageObjects.*;

public class SelenideTests extends BaseTest{
    protected final static SeleniumConfig CONFIG = Env.Selenium.SELENIUM_CONFIG;
    private static PobedaMainPage pobedaMainPage;

    @Test
    public void pobedaModalWindowTest(){
        pobedaMainPage = new PobedaMainPage(CONFIG.baseUrlPobeda());

        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"
                ,pobedaMainPage.getTitle());
        Assertions.assertTrue(pobedaMainPage.logoIsPresentedOnPage());

        Assertions.assertEquals("Подготовка к полёту", pobedaMainPage.getPreparingToFlightHeaderText());
        Assertions.assertEquals("Полезная информация", pobedaMainPage.getUsefulInformationHeaderText());
        Assertions.assertEquals("О компании", pobedaMainPage.getCompanyInformationHeaderText());
    }

    @Test
    public void pobedaSearchWithoutDateTest(){
        pobedaMainPage = new PobedaMainPage(CONFIG.baseUrlPobeda());

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
        pobedaMainPage = new PobedaMainPage(CONFIG.baseUrlPobeda());

        Assertions.assertEquals("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, прямые и трансферные рейсы с пересадками"
                ,pobedaMainPage.getTitle());
        Assertions.assertTrue(pobedaMainPage.logoIsPresentedOnPage());

        PobedaBookingElement bookingElement = pobedaMainPage.bookingManagmentButtonClick();

        Assertions.assertTrue(bookingElement.clientSurnameFieldIsPresentedOnPage());
        Assertions.assertTrue(bookingElement.ticketNumberFieldIsPresentedOnPage());
        Assertions.assertTrue(bookingElement.searchButtonIsPresentedOnPage());

        bookingElement.inputSurname("Qwerty");
        bookingElement.inputTicketNumber("XXXXXX");
        PobedaTicketPage ticketPage = bookingElement.searchButtonClick();

        ticketPage.agreeWithConfidentialPolicy();
        ticketPage.findButtonClick();

        Assertions.assertEquals("Заказ с указанными параметрами не найден", ticketPage.getErrorText());
    }

    @AfterEach
    public void teardown(){
    }
}
