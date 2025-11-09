package org.task5.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage{
    private final By searchInput = new By.ByCssSelector("textarea[title=Поиск]");
    private final By searchButton = new By.ByCssSelector("input[name=btnK]");
    private final By firstSearchResult = new By.ByXPath("//*[@id='rso']/div[1]//h3/..");

    public GooglePage(WebDriver driver, String URL) {
        super(driver);
        driver.get(URL);
    }

    public PobedaMainPage findPobedaMainPage(){
        driver.findElement(searchInput).sendKeys("Сайт компании Победа");
        driver.findElement(searchButton).submit();

        driver.findElement(firstSearchResult).click();
        return new PobedaMainPage(driver);
    }
}
