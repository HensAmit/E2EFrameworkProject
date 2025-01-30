package com.mystore.pages;

import com.mystore.base.BasePage;
import com.mystore.reportmanager.ExtentReporter;
import org.openqa.selenium.By;

import static com.mystore.constants.ReportStatus.*;

public class LoginPage extends BasePage {
    private static final By ACCOUNT_LINK = By.xpath("//li[contains(@id, 'menu-item')]//a[text()='Account']");
    private static final By USERNAME_INPUT = By.xpath("//input[@id='username']");
    private static final By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@type='submit' and text()='Log in']");

    public void login () {
        try {
            clickElement(ACCOUNT_LINK);
            ExtentReporter.report(PASS, "Clicked on Account link");
            enterText(USERNAME_INPUT, properties.getProperty("username"));
            enterText(PASSWORD_INPUT, properties.getProperty("password"));
            clickElement(LOGIN_BUTTON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
