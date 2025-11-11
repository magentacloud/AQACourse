package org.task5.selenide.PageObjects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task3.env.Env;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public class PobedaSearchElement {
    private final String dateInFailSelector = "//input[@placeholder='Туда']/following-sibling::div[@data-failed='true']";

    private final SelenideElement from = $("input[placeholder='Откуда']");
    private final SelenideElement to = $("input[placeholder='Куда']");
    private final SelenideElement dateIn = $("input[placeholder='Туда']");
    private final SelenideElement dateInFail = $(dateInFailSelector);
    private final SelenideElement dateOut = $("input[placeholder='Обратно']");
    private final SelenideElement searchButton = $(new By.ByXPath("//span[text()='Поиск']"));

    public boolean fromFieldIsPresentedOnPage(){
        boolean isPresented = from.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти поле 'Откуда' на странице поиска");
        }

        return isPresented;
    }

    public boolean toFieldIsPresentedOnPage(){
        boolean isPresented = to.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти поле 'Куда' на странице поиска");
        }

        return isPresented;
    }

    public boolean dateInFieldIsPresentedOnPage(){
        boolean isPresented = dateIn.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти поле даты вылета туда на странице поиска");
        }

        return isPresented;
    }

    public boolean dateOutFieldIsPresentedOnPage(){
        boolean isPresented = dateOut.isDisplayed();

        if(!isPresented){
            log.error("Не удалось найти поле даты вылета обратно на странице поиска");
        }

        return isPresented;
    }

    public void inputFrom(String city){
        from.setValue(city).shouldHave(value(city));
    }

    public void inputTo(String city){
        to.setValue(city).shouldHave(value(city));
    }

    public void searchButtonClick(){
        searchButton.click();
    }

    public boolean isSearchFails(){
        return $( new By.ByXPath(dateInFailSelector)).isDisplayed();
    }
}
