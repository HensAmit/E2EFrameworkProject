package com.mystore.impl;

import com.mystore.base.BasePage;
import com.mystore.pages.LoginPage;

public class MyStoreImpl extends BasePage {
    private final LoginPage loginPage = new LoginPage();

    public void login () {
        try {
            loginPage.login();
            waitByTimeInSeconds(20);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
