package com.mystore.reportmanager;

import com.aventstack.extentreports.Status;
import com.mystore.constants.ReportStatus;

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
                    takeScreenShot();
                    break;
                case PASS:
                    ExtentManager.getTest().log(Status.PASS, details);
                    takeScreenShot();
                    break;
                case PASS_NO_SCREENSHOT:
                    ExtentManager.getTest().log(Status.PASS, details);
                    break;
                case FAIL:
                    ExtentManager.getTest().log(Status.FAIL, details);
                    takeScreenShot();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fail(Throwable throwable) {
        ExtentManager.getTest().log(Status.FAIL, throwable);
    }

    static void takeScreenShot() {
        System.out.println("Taking scshot");
    }
}
