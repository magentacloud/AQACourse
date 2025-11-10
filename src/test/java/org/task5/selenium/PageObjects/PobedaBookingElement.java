package org.task5.selenium.PageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task3.env.Env;

import java.time.Duration;

@Slf4j
public class PobedaBookingElement {
    WebDriver driver;

    PobedaBookingElement(WebDriver driver){
        this.driver = driver;
    }

    private final By clientSurname = new By.ByXPath("//input[@placeholder='Фамилия клиента']");
    private final By ticketNumber = new By.ByXPath("//input[@placeholder='Номер бронирования или билета']");
    private final By searchButton = new By.ByXPath("//span[text()='Поиск']");

    public boolean clientSurnameFieldIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(clientSurname));
        } catch (Exception e) {
            log.error("Не удалось найти поле 'Фамилия клиента' на вкладке 'Управление бронированием'");
            return false;
        }
        return true;
    }

    public boolean ticketNumberFieldIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ticketNumber));
        } catch (Exception e) {
            log.error("Не удалось найти поле 'Номер бронирования или билета' на вкладке 'Управление бронированием'");
            return false;
        }
        return true;
    }

    public boolean searchButtonIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchButton));
        } catch (Exception e) {
            log.error("Не удалось найти кнопку 'Поиск' на вкладке 'Управление бронированием'");
            return false;
        }
        return true;
    }

    public void inputSurname(String surname) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        driver.findElement(clientSurname).sendKeys(surname);
        wait.until(ExpectedConditions.textToBePresentInElementValue(clientSurname, surname));
    }

    public void inputTicketNumber(String number){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        driver.findElement(ticketNumber).sendKeys(number);
        wait.until(ExpectedConditions.textToBePresentInElementValue(ticketNumber, number));
    }

    public PobedaTicketPage searchButtonClick(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));
        driver.findElement(searchButton).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        return new PobedaTicketPage(driver);
    }
}
