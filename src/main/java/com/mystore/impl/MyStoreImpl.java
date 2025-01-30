package com.mystore.impl;

import com.mystore.base.BasePage;
import static com.mystore.constants.CommonConstants.*;
import com.mystore.pages.LoginPage;
import com.mystore.reportmanager.ExtentReporter;

public class MyStoreImpl extends BasePage {
    private final LoginPage loginPage = new LoginPage();

    public void login () {
        try {
            logger.info("Execution of login() started.");
            loginPage.login();
            waitByTimeInSeconds(5);
            ExtentReporter.report(PASS, "Login successful");
            logger.info("Execution of login() ended.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
