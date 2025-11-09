package org.task5.selenium.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PikabuLoginPage extends BasePage{

    private final By loginInput = new By.ByCssSelector(".auth-modal input[placeholder=Логин]");
    private final By passwordInput = new By.ByCssSelector(".auth-modal input[placeholder=Пароль]");
    private final By submitButton = new By.ByCssSelector(".auth-modal .auth__field_firstbtn>button");
    private final By authError = new By.ByCssSelector(".popup__content .auth__error_top");

    public PikabuLoginPage(WebDriver driver) {
        super(driver);
    }

    public String loginWithIncorrectCredentials(String login, String password){
        driver.findElement(loginInput).sendKeys(login);
        driver.findElement(passwordInput).sendKeys(password);

        driver.findElement(submitButton).submit();

        return driver.findElement(authError).getText();
    }
}
