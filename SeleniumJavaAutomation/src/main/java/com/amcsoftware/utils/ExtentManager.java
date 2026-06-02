package com.amcsoftware.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/report_" + timestamp + ".html");
            sparkReporter.config().setDocumentTitle("Selenium Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setReportName("Selenium Java Automation - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM yyyy")));
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}
