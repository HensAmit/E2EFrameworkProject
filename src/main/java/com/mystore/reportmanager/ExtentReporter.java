package com.mystore.reportmanager;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.mystore.constants.ReportStatus;
import com.mystore.util.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public final class ExtentReporter {

    private static final Logger logger = (Logger) LogManager.getLogger(ExtentReporter.class);

    private ExtentReporter() {
    }

    public static void report(ReportStatus status, String details, Throwable... t) {
        try {
            switch (status) {
                case INFO:
                    logger.info(details);
                    ExtentManager.getTest().log(Status.INFO, details);
                    break;
                case INFO_WITH_SCREENSHOT:
                    logger.info(details);
                    ExtentManager.getTest().log(Status.INFO, details);
                    ExtentManager.getTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotPath()).build());
                    break;
                case PASS:
                    logger.info("PASSED : {}", details);
                    ExtentManager.getTest().log(Status.PASS, details);
                    ExtentManager.getTest().log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotPath()).build());
                    break;
                case PASS_NO_SCREENSHOT:
                    logger.info("PASSED : {}", details);
                    ExtentManager.getTest().log(Status.PASS, details);
                    break;
                case FAIL:
                    logger.error(t[0]);
                    ExtentManager.getTest().log(Status.FAIL, details);
                    ExtentManager.getTest().log(Status.FAIL, t[0]);
                    ExtentManager.getTest().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotPath()).build());
                    break;
            }
        } catch (Exception e) {
            logger.error("Failed in report() : {}, {}, {}", status.toString(), details, t);
        }
    }
}
