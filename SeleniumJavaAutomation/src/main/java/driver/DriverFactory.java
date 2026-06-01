package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String gridURL = "";

    public static WebDriver initDriver(String runMode, String browser) throws MalformedURLException {
        if (runMode.equalsIgnoreCase("local")) {
            switch (browser) {
                case "chrome":
                    setDriver(new ChromeDriver());
                    break;
                case "firefox":
                    setDriver(new FirefoxDriver());
                    break;
                case "edge":
                    setDriver(new EdgeDriver());
                    break;
                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }
        } else if (runMode.equalsIgnoreCase("grid")) {
            switch (browser) {
                case "chrome":
                    setDriver(new RemoteWebDriver(new URL(gridURL), new ChromeOptions()));
                    break;
                case "firefox":
                    setDriver(new RemoteWebDriver(new URL(gridURL), new FirefoxOptions()));
                    break;
                case "edge":
                    setDriver(new RemoteWebDriver(new URL(gridURL), new EdgeOptions()));
                    break;
                default:
                    throw new RuntimeException("Invalid browser: " + browser);
            }
        } else {
            throw new RuntimeException("Invalid runMode: " + runMode);
        }
        return getDriver();
    }

    private static void setDriver(WebDriver driver) {
        DriverFactory.driver.set(driver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void tearDown() {
        DriverFactory.driver.get().quit();
        DriverFactory.driver.remove();
    }
}
