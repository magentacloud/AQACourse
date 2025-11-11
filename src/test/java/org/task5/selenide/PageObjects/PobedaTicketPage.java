package org.task5.selenide.PageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task3.env.Env;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class PobedaTicketPage extends BasePage{

    public PobedaTicketPage() {
        super();
    }

    private final SelenideElement informationButton = $("div[class='customCheckbox']");
    private final SelenideElement findButton = $(new By.ByXPath("//button[text()='Найти заказ']"));
    private final SelenideElement error =  $(".message_error");

    public void agreeWithConfidentialPolicy(){
        informationButton.click();
    }

    public void findButtonClick(){
        findButton.click();
    }

    public String getErrorText(){
       return error.getText();
    }
}
