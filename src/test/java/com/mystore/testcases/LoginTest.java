package com.mystore.testcases;

import com.mystore.base.BasePage;
import com.mystore.datamanager.ScenarioData;
import com.mystore.impl.MyStoreImpl;
import com.mystore.utility.DataUtils;
import org.testng.annotations.Test;

public class LoginTest extends BasePage {
    private final MyStoreImpl myStoreImpl = new MyStoreImpl();

    @Test
    public void testOne () {
        try {
            DataUtils.readTestDataFromExcelSheet("TC-1", "ScenarioOne");
            myStoreImpl.login();
            System.out.println("Inside testOne");
            System.out.println(ScenarioData.getData("TC_ID"));
            System.out.println(ScenarioData.getData("DataOne"));
            System.out.println(ScenarioData.getData("DataTwo"));
            System.out.println(ScenarioData.getData("DataThree"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTwo () {
        try {
            DataUtils.readTestDataFromExcelSheet("TC-2", "ScenarioTwo");
            myStoreImpl.login();
            System.out.println("Inside testTwo");
            System.out.println(ScenarioData.getData("TC_ID"));
            System.out.println(ScenarioData.getData("DataOne"));
            System.out.println(ScenarioData.getData("DataFour"));
            System.out.println(ScenarioData.getData("DataFive"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
