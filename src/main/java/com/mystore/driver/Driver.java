package com.mystore.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class Driver {
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public static void initializeDriver(String browserName) {
        try {
            if (StringUtils.equalsIgnoreCase(browserName, "Chrome")) {
                WebDriverManager.chromedriver().setup();
                threadLocalDriver.set(new ChromeDriver());
            } else if (StringUtils.equalsIgnoreCase(browserName, "Firefox")) {
                WebDriverManager.firefoxdriver().setup();
                threadLocalDriver.set(new FirefoxDriver());
            } else if (StringUtils.equalsIgnoreCase(browserName, "Edge")) {
                WebDriverManager.edgedriver().setup();
                threadLocalDriver.set(new EdgeDriver());
            } else {
                throw new Exception("Browser name is Invalid");
            }
            getDriver().manage().window().maximize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}
