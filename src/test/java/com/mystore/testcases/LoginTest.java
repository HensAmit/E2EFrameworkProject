package com.mystore.testcases;

import com.mystore.base.BasePage;

import static com.mystore.constants.ReportStatus.*;

import com.mystore.datamanager.ScenarioData;
import com.mystore.impl.MyStoreImpl;
import com.mystore.listeners.TestListeners;
import com.mystore.reportmanager.ExtentReporter;
import com.mystore.util.DataUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListeners.class)
public class LoginTest extends BasePage {
    private final MyStoreImpl myStoreImpl = new MyStoreImpl();
    private static final Logger logger = (Logger) LogManager.getLogger(LoginTest.class);

    @Test
    public void testOne() {
        DataUtils.readTestDataFromExcelSheet("TC-1", "ScenarioOne");
        myStoreImpl.login();
        logger.info("Inside testOne");
        System.out.println(ScenarioData.getData("TC_ID"));
        System.out.println(ScenarioData.getData("DataOne"));
        System.out.println(ScenarioData.getData("DataTwo"));
        System.out.println(ScenarioData.getData("DataThree"));
        Assert.fail("Fail demo");
        ExtentReporter.report(PASS, "testone passed");
    }

    @Test
    public void testTwo() {
        DataUtils.readTestDataFromExcelSheet("TC-2", "ScenarioTwo");
        myStoreImpl.login();
        System.out.println("Inside testTwo");
        ExtentReporter.report(PASS, "testtwo passed");
        ExtentReporter.report(INFO, "testtwo passed");
        System.out.println(ScenarioData.getData("TC_ID"));
        System.out.println(ScenarioData.getData("DataOne"));
        System.out.println(ScenarioData.getData("DataFour"));
        System.out.println(ScenarioData.getData("DataFive"));
    }
}
