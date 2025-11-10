package org.task5.selenium.PageObjects;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.task3.env.Env;

import java.time.Duration;
import java.util.List;

@Slf4j
public class PobedaSearchElement {
    WebDriver driver;

    PobedaSearchElement(WebDriver driver){
        this.driver = driver;
    }

    private final By from = new By.ByXPath("//input[@placeholder='Откуда']");
    private final By to = new By.ByXPath("//input[@placeholder='Куда']");
    private final By dateIn = new By.ByXPath("//input[@placeholder='Туда']");
    private final By dateInFail = new By.ByXPath("(//input[@placeholder='Туда']//div[@data-failed='true'])[1]");
    private final By dateOut = new By.ByXPath("//input[@placeholder='Обратно']");
    private final By searchButton = new By.ByXPath("//span[text()='Поиск']");

    public boolean fromFieldIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(from));
        } catch (Exception e) {
            log.error("Не удалось найти поле 'Откуда' на странице поиска");
            return false;
        }
        return true;
    }

    public boolean toFieldIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(to));
        } catch (Exception e) {
            log.error("Не удалось найти поле 'Откуда' на странице поиска");
            return false;
        }
        return true;
    }
    public boolean dateInFieldIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dateIn));
        } catch (Exception e) {
            log.error("Не удалось найти поле даты вылета туда на странице поиска");
            return false;
        }
        return true;
    }
    public boolean dateOutFieldIsPresentedOnPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dateOut));
        } catch (Exception e) {
            log.error("Не удалось найти поле даты вылета обратно на странице поиска");
            return false;
        }
        return true;
    }

    public void inputFrom(String city){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        driver.findElement(from).sendKeys(city);
        wait.until(ExpectedConditions.textToBePresentInElementValue(from, city));
    }

    public void inputTo(String city){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(Env.Selenium.SELENIUM_CONFIG.timeout()));

        driver.findElement(to).sendKeys(city);
        wait.until(ExpectedConditions.textToBePresentInElementValue(to, city));
    }

    public void searchButtonClick(){
        driver.findElement(searchButton).click();
    }

    public boolean isSearchFails(){
        try{
            List<WebElement> dateInFails = driver.findElements(dateInFail);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
