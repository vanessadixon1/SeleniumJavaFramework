package com.amcsoftwae.listeners;
import com.amcsoftware.driver.DriverFactory;
import com.amcsoftware.utils.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Slf4j
public class TestListener implements ITestListener {
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        log.info("Starting test {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed {}" + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        try {
            Path screenshotsDir = Path.of("./screenshots");
            Files.createDirectories(screenshotsDir);
            File screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            Path destination = screenshotsDir.resolve(methodName + ".png");
            Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            test.get().fail(result.getThrowable());
            test.get().addScreenCaptureFromPath(destination.toString(), methodName);
        } catch (IOException e) {
            log.error("Failed to capture screenshot for {}", methodName, e);
            test.get().fail(result.getThrowable());
        }
        log.error("Test failed {}", methodName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test was skipped " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Starting: {}", context.getCurrentXmlTest().getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finished Suite {}", context.getCurrentXmlTest().getSuite().getName().trim());
        extent.flush();
    }
}
