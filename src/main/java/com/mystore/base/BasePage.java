package com.mystore.base;

import com.mystore.driver.Driver;
import com.mystore.utility.ReadPropertyFile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

public class BasePage extends Driver {
    private static Properties properties;

    @BeforeSuite
    public static void loadConfig() {
        properties = ReadPropertyFile.loadProperties();
    }

    @BeforeMethod
    public void launchApp() {
        try {
            String browserName = properties.getProperty("browser");
            initializeDriver(browserName);
            getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(properties.getProperty("pageLoadTimeOut"))));
            getDriver().get(properties.getProperty("url"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static WebElement getElement(By locator) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(Integer.parseInt(properties.getProperty("explicitWaitTime")))).until(ExpectedConditions.visibilityOfElementLocated(locator));
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
            System.out.println("Clicked on element");
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
