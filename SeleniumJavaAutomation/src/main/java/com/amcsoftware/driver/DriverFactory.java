package com.amcsoftware.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String gridURL;

    public static void initDriver(String browser) {
        browserSelection(browser);
    }

    public static void initDriver(String runMode, String browser) throws IOException {
        if (runMode.equalsIgnoreCase("local")) {
            browserSelection(browser);
        } else if (runMode.equalsIgnoreCase("grid")) {
            Properties prop = new Properties();
            prop.load(DriverFactory.class.getClassLoader().getResourceAsStream("config.properties"));
            gridURL = prop.getProperty("gridUrl");
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
    }

    private static void browserSelection(String browser) {
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
    }

    private static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void tearDown() {
        WebDriver current = driver.get();
        if (current != null) {
            current.quit();
        }
        driver.remove();
    }
}
