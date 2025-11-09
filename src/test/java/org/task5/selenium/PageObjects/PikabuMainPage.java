package org.task5.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PikabuMainPage extends BasePage{

    private final By mainLoginButton = new By.ByClassName("header-right-menu__login-button");

    public PikabuMainPage(WebDriver driver, String URL){
        super(driver);
        driver.get(URL);
    }

    public PikabuLoginPage openLoginWindow(){
        driver.findElement(mainLoginButton).click();

        return new PikabuLoginPage(driver);
    }

}
