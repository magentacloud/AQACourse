package org.task5.selenide.PageObjects;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task3.env.Env;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

@Slf4j
public class PobedaBookingElement {

    PobedaBookingElement(){
        try {
            clientSurname.shouldBe(visible, Duration.ofSeconds(15));
        } catch (ElementNotFound e) {
            log.error("Страница 'Управление бронированием' не загрузилась за 15 сек.");
            throw e; // Провалить тест немедленно
        }
    }

    private final SelenideElement clientSurname = $("input[placeholder='Фамилия клиента']");
    private final SelenideElement ticketNumber = $("input[placeholder='Номер бронирования или билета']");
    private final SelenideElement searchButton = $(new By.ByXPath("//span[text()='Поиск']"));

    public boolean clientSurnameFieldIsPresentedOnPage(){
        boolean isPresented = clientSurname.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти поле 'Фамилия клиента' на вкладке 'Управление бронированием'");
        }

        return isPresented;
    }

    public boolean ticketNumberFieldIsPresentedOnPage(){
        boolean isPresented = ticketNumber.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти поле 'Номер бронирования или билета' на вкладке 'Управление бронированием'");
        }

        return isPresented;
    }

    public boolean searchButtonIsPresentedOnPage(){
        boolean isPresented = searchButton.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти кнопку 'Поиск' на вкладке 'Управление бронированием'");
        }

        return isPresented;
    }

    public void inputSurname(String surname) {
        clientSurname.sendKeys(surname);
    }

    public void inputTicketNumber(String number){
        ticketNumber.sendKeys(number);
    }

    public PobedaTicketPage searchButtonClick(){
        searchButton.click();

        switchTo().window("Просмотр заказа");

        return new PobedaTicketPage();
    }
}
