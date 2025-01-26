package com.mystore.testcases;

import com.mystore.base.BasePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {
    private static final By ACCOUNT_LINK = By.xpath("//li[contains(@id, 'menu-item')]//a[text()='Account']");

    @Test
    public void testOne () {
        System.out.println("Inside testOne");
        System.out.println(getTextFromElement(ACCOUNT_LINK));
        clickElement(ACCOUNT_LINK);
        waitByTimeInSeconds(10);
    }

    @Test
    public void testTwo () {
        System.out.println("Inside testTwo");
        System.out.println(getTextFromElement(ACCOUNT_LINK));
        clickElement(ACCOUNT_LINK);
        waitByTimeInSeconds(10);
    }
}
