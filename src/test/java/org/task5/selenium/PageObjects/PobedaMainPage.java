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
public class PobedaMainPage extends BasePage{
    public PobedaMainPage(WebDriver driver) {
        super(driver);
    }

    private final By tripToKaliningradBanner = new By.ByXPath("//button[.//div[contains(., 'Полетели в Калининград!')]]");
    private final By tripToKaliningradDescription = new By.ByXPath("//div[text()='Полетели в Калининград!']");
    private final By languageChangeButton = new By.ByXPath("//button[contains(., 'РУС')]");
    private final By changeToEngilshButton = new By.ByXPath("//div[text()='English']");
    private final By ticketSearchEnButton = new By.ByXPath("//span[text()='Ticket search' and not (@aria-hidden='true')]");
    private final By onlineCheckInEnButton = new By.ByXPath("//span[text()='Online check-in' and not (@aria-hidden='true')]");
    private final By manageMyBookingEnButton = new By.ByXPath("//span[text()='Manage my booking' and not (@aria-hidden='true')]");

    public void waitForTripToKaliningrad(){
        WebElement element = driver.findElement(tripToKaliningradBanner);

        long waitingTime = 0;
        long timeout = Env.Selenium.SELENIUM_CONFIG.pageLoadTimeout();
        long startTime = System.currentTimeMillis();

        while (!element.isDisplayed()){
            if(waitingTime <= timeout){
                waitingTime = System.currentTimeMillis() - startTime;
            } else {
                log.error("Не удалось выполнить условие за" + timeout + " мс");
            }
        }
    }

    public String getTripToKaliningradText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(tripToKaliningradDescription, "Полетели в Калининград!"));

        WebElement element = driver.findElement(tripToKaliningradDescription);
        return element.getText();
    }

    public void changeLanguageToEN(){
        driver.findElement(languageChangeButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(changeToEngilshButton));

        driver.findElement(changeToEngilshButton).click();
    }

    public String getOnlineCheckInEnButtonText(){
        WebElement element = driver.findElement(onlineCheckInEnButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(element, "Online check-in"));

        return element.getText();
    }
    public String getTicketSearchEnButtonText(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(ticketSearchEnButton));

        return element.getText();
    }

    public String getManageMyBookingEnButtonText(){
        WebElement element = driver.findElement(manageMyBookingEnButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(element, "Manage my booking"));

        return element.getText();
    }
}
