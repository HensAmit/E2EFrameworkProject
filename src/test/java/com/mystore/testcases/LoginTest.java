package com.mystore.testcases;

import com.mystore.base.BasePage;
import com.mystore.impl.MyStoreImpl;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class LoginTest extends BasePage {
    private final MyStoreImpl myStoreImpl = new MyStoreImpl();

    @Test
    public void testOne () {
        try {
            myStoreImpl.login();
            System.out.println("Inside testOne");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTwo () {
        try {
            myStoreImpl.login();
            System.out.println("Inside testTwo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
