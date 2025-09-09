package com.mystore.impl;

import com.mystore.base.BasePage;
import static com.mystore.constants.ReportStatus.*;
import com.mystore.pages.LoginPage;
import com.mystore.reportmanager.ExtentReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class MyStoreImpl extends BasePage {
    private final LoginPage loginPage = new LoginPage();
    private static final Logger logger = (Logger) LogManager.getLogger(MyStoreImpl.class);

    public void login() {
        try {
            logger.info("Execution of login() started.");
            loginPage.login();
            waitByTimeInSeconds(5);
            ExtentReporter.report(PASS, "Login successful");
            logger.info("Execution of login() ended.");
        } catch (Exception e) {
            logger.error("Failed in login() : ", e);
            throw new RuntimeException("Failed in login() : ", e);
        }
    }
}
