package base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class BaseTest {
    private final String environment = System.getProperty("env");
    String baseUrl;
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        if(environment.equalsIgnoreCase("qa")) {
            baseUrl = "localhost/qa";
        }else if(environment.equalsIgnoreCase("dev")) {
            baseUrl = "localhost/dev";
        }
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browser) throws MalformedURLException {
        String runMode = System.getProperty("runMode");
        DriverFactory.initDriver(runMode,browser);
        driver = DriverFactory.getDriver();
        driver.get(baseUrl);
    }

    @AfterMethod
    public void teardown() {
        DriverFactory.tearDown();
    }

}
