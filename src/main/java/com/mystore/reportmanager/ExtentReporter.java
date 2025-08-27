package com.mystore.reportmanager;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.mystore.constants.ReportStatus;
import com.mystore.util.ScreenshotUtil;

public final class ExtentReporter {
    private ExtentReporter() {
    }

    public static void report(ReportStatus status, String details) {
        try {
            switch (status) {
                case INFO:
                    ExtentManager.getTest().log(Status.INFO, details);
                    break;
                case INFO_WITH_SCREENSHOT:
                    ExtentManager.getTest().log(Status.INFO, details);
                    ExtentManager.getTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotPath()).build());
                    break;
                case PASS:
                    ExtentManager.getTest().log(Status.PASS, details);
                    ExtentManager.getTest().log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotPath()).build());
                    break;
                case PASS_NO_SCREENSHOT:
                    ExtentManager.getTest().log(Status.PASS, details);
                    break;
                case FAIL:
                    ExtentManager.getTest().log(Status.FAIL, details);
                    ExtentManager.getTest().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtil.getScreenshotPath()).build());
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fail(Throwable throwable) {
        ExtentManager.getTest().log(Status.FAIL, throwable);
    }
}
