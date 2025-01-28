package com.mystore.impl;

import com.mystore.base.BasePage;
import com.mystore.pages.LoginPage;

public class MyStoreImpl extends BasePage {
    private final LoginPage loginPage = new LoginPage();

    public void login () {
        try {
            logger.info("Execution of login() started.");
            loginPage.login();
            waitByTimeInSeconds(5);
            logger.info("Execution of login() ended.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
