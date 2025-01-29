package com.mystore.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mystore.reportmanager.ExtentManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListeners implements ISuiteListener, ITestListener {
    @Override
    public void onStart(ISuite suite) {
        System.out.println("Starting Test Suite: " + suite.getName());
        ExtentManager.getExtentReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Finishing Test Suite: " + suite.getName());
        ExtentManager.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentManager.getExtentReports()
                .createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());
    }
}

