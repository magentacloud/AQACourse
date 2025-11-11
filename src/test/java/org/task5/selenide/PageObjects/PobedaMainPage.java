package org.task5.selenide.PageObjects;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class PobedaMainPage extends BasePage {

    public PobedaMainPage(String URL) {
        open(URL);
    }

    public PobedaSearchElement pobedaSearchElement = new PobedaSearchElement();

    private final SelenideElement logo = $("img[alt='«Авиакомпания «Победа», Группа «Аэрофлот»']");
    private final SelenideElement informationButton = $("a[href='/information']");
    private final SelenideElement preparingToFlightHeader = $("a[href='/information#flight']");
    private final SelenideElement usefulInformationHeader = $("a[href='/information#useful']");
    private final SelenideElement companyInformationHeader = $("a[href='/information#company']");
    private final SelenideElement bookingManagmentButton = $(new By.ByXPath("//span[text()='Управление бронированием']/.."));

    public boolean logoIsPresentedOnPage() {
        boolean isPresented = logo.isDisplayed();

        if (!isPresented) {
            log.error("Не удалось найти логотип на странице");
        }

        return isPresented;
    }

    public String getPreparingToFlightHeaderText() {
        informationButton.hover();

        return preparingToFlightHeader.getText();
    }

    public String getUsefulInformationHeaderText() {
        informationButton.hover();

        return usefulInformationHeader.getText();
    }

    public String getCompanyInformationHeaderText() {
        informationButton.hover();

        return companyInformationHeader.getText();
    }

    public PobedaBookingElement bookingManagmentButtonClick() {
        bookingManagmentButton.click();

        return new PobedaBookingElement();
    }
}
