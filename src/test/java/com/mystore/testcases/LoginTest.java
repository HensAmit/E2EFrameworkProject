package com.mystore.testcases;

import com.mystore.base.BasePage;
import com.mystore.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {
    private final LoginPage loginPage = new LoginPage();

    @Test
    public void testOne () {
        System.out.println("Inside testOne");
        loginPage.login();
        waitByTimeInSeconds(20);
    }

    @Test
    public void testTwo () {
        System.out.println("Inside testTwo");
        loginPage.login();
        waitByTimeInSeconds(20);
    }
}
