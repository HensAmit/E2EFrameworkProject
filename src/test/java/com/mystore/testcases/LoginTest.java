package com.mystore.testcases;

import com.mystore.base.BasePage;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {
    @Test
    public void testOne () {
        System.out.println("Inside testOne");
        waitByTimeInSeconds(10);
    }

    @Test
    public void testTwo () {
        System.out.println("Inside testTwo");
        waitByTimeInSeconds(10);
    }
}
