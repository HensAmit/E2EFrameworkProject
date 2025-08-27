package com.mystore.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.mystore.reportmanager.ExtentManager;
import com.mystore.reportmanager.ExtentReporter;
import org.testng.*;

import static com.mystore.constants.ReportStatus.*;

public class TestListeners implements ISuiteListener, ITestListener {
    @Override
    public void onStart(ISuite suite) {
        ExtentManager.getExtentReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getExtentReports().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        ExtentReporter.report(INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReporter.report(PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReporter.report(FAIL, "Test Failed: " + result.getMethod().getMethodName(), result.getThrowable());
    }
}

