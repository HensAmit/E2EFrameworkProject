package com.mystore.util;

import com.mystore.base.BasePage;
import com.mystore.reportmanager.ExtentReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.mystore.constants.ReportStatus.FAIL;

public class ScreenshotUtil {
    private ScreenshotUtil() {
    }

    public static String getScreenshotPath() {
        String screenshotFilePath = null;
        try {
            WebDriver driver = BasePage.getDriver();
            if (driver == null) {
                throw new IllegalStateException("Driver instance is null. Cannot take screenshot.");
            }
            screenshotFilePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            ExtentReporter.report(FAIL, "Failed in getScreenshotPath() method", e);
        }
        return screenshotFilePath;
    }
}
