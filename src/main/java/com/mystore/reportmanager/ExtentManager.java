package com.mystore.reportmanager;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;

public final class ExtentManager {
    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Logger logger = (Logger) LogManager.getLogger(ExtentManager.class);

    private ExtentManager() {
    }

    public static ExtentReports getExtentReports() {
        try {
            if (extent == null) {
                String reportPath = "target/reports/report.html";
                ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
                sparkReporter.loadJSONConfig(new File(ExtentManager.class.getClassLoader().getResource("configs/extent-report-config.json").toURI()));
                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);
                extent.setSystemInfo("Tester", "EMP-981909");
            }
        } catch (Exception e) {
            logger.error("Failed in getExtentReports() - ", e);
            throw new RuntimeException("Failed in getExtentReports() - ", e);
        }
        return extent;
    }

    static ExtentTest getTest() {
        return extentTest.get();
    }

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}