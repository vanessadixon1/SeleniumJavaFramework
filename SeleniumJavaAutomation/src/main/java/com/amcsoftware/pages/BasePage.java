package com.amcsoftware.pages;

import com.amcsoftware.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));
    }
}
