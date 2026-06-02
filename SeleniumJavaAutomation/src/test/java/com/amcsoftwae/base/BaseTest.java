package com.amcsoftwae.base;

import com.amcsoftware.driver.DriverFactory;
import com.amcsoftware.utils.ExtentManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest {
    String baseUrl;
    WebDriver driver;

    @BeforeSuite
    public void beforeSuite()  {
        ExtentManager.getInstance();
    }

    @BeforeClass
    public void beforeClass() {
        String environment = System.getProperty("env");
        if (environment == null || environment.isBlank()) {
            throw new IllegalStateException("System property 'env' is not set. Pass -Denv=qa or -Denv=dev");
        }
        if (environment.equalsIgnoreCase("qa")) {
            baseUrl = "localhost";
        } else if (environment.equalsIgnoreCase("dev")) {
            baseUrl = "localhost:5000";
        } else {
            throw new IllegalArgumentException("Unknown environment '" + environment + "'. Expected: qa, dev");
        }
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws IOException {
        String runMode = System.getProperty("runMode");
        if (runMode == null || runMode.isBlank()) {
            DriverFactory.initDriver(browser);
        }else{
        DriverFactory.initDriver(runMode,browser);
        }
        driver = DriverFactory.getDriver();
//        driver.get(baseUrl);
    }

    @AfterMethod
    public void teardown() {
        DriverFactory.tearDown();
    }

}
