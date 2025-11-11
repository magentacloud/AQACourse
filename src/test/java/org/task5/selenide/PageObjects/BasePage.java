package org.task5.selenide.PageObjects;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.title;

public abstract class BasePage {;

    public BasePage(){
    }

    public String getTitle(){
        return title();
    }
}
