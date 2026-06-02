package com.amcsoftware.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage() {
       super();
    }

    @FindBy(id = "APjFqb")
    public WebElement searchBox;


    public void inputDataAndSearch(String data) throws InterruptedException {
        searchBox.sendKeys(data);
        Thread.sleep(8000);
    }
}
