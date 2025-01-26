package com.mystore.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

public class BasePage {
    private Properties properties;
    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    @BeforeSuite
    public void loadConfig() {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\Configs\\config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void launchApp() {
        try {
            String browserName = properties.getProperty("browser");
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
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(properties.getProperty("pageLoadTimeOut"))));
            getDriver().get(properties.getProperty("url"));
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

    private static WebElement getElement(By locator) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void scrollToElement(By locator) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) getDriver();
            executor.executeScript("arguments[0].scrollIntoView()", getElement(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clickElement(By locator) {
        try {
            getElement(locator).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enterText(By locator, String text) {
        try {
            WebElement element = getElement(locator);
            if (element.isDisplayed()) {
                element.clear();
                element.sendKeys(text);
            } else {
                throw new NoSuchElementException("Element not displayed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void selectOptionFromDropdown(By dropDownLocator, String optionToSelect) {
        try {
            WebElement element = getElement(dropDownLocator);
            Select select = new Select(element);
            if (element.isDisplayed()) {
                select.selectByVisibleText(optionToSelect);
            } else {
                throw new NoSuchElementException("Element not displayed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getTextFromElement(By locator) {
        try {
            return getElement(locator).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void waitByTimeInSeconds (long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
