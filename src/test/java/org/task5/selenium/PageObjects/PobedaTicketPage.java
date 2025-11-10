package org.task5.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task3.env.Env;

import java.time.Duration;

public class PobedaTicketPage extends BasePage{
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

    public PobedaTicketPage(WebDriver driver) {
        super(driver);
    }

    private final By informationButton = new By.ByXPath("//div[@class='customCheckbox']");
    private final By findButton = new By.ByXPath("//button[text()='Найти заказ']");
    private final By error = new By.ByClassName("message_error");

    public void agreeWithConfidentialPolicy(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(informationButton));
        element.click();
    }

    public void findButtonClick(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(findButton));
        element.click();
    }

    public String getErrorText(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(error));
        return element.getText();
    }
}
