package com.mystore.utility;

import com.mystore.base.BasePage;
import com.mystore.datamanager.ScenarioData;
import com.mystore.reportmanager.ExtentReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static com.mystore.constants.ReportStatus.FAIL;

public class ScreenshotUtil {
    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "\\reports\\screenshots\\";

    private ScreenshotUtil() {
    }

    public static String getScreenshotPath() {
        String screenshotFilePath = null;
        try {
            WebDriver driver = BasePage.getDriver();
            if (driver == null) {
                throw new IllegalStateException("Driver instance is null. Cannot take screenshot.");
            }
            new File(SCREENSHOT_PATH).mkdirs();
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            screenshotFilePath = SCREENSHOT_PATH + ScenarioData.getData("TC_ID") + "_" + timestamp + ".png";
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(screenshotFilePath));
        } catch (Exception e) {
            ExtentReporter.report(FAIL, "Failed in getScreenshotPath() method");
            ExtentReporter.fail(e);
        }
        return screenshotFilePath;
    }

    public static void cleanOldScreenshots() {
        File screenshotDir = new File(System.getProperty("user.dir") + "\\reports\\screenshots\\");
        if (screenshotDir.exists()) {
            for (File file : Objects.requireNonNull(screenshotDir.listFiles())) {
                file.delete();
            }
        }
    }
}
