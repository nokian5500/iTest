/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iDoc;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 *
 * @author User
 */
public class Test_ProcessingAppForDashboard extends CustomMethods {

    //<editor-fold desc="Тестовый пример загрузки файла">
    @Test
    public void Test_ProcessingAppForDashboard() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        String sBP = "_idoc_test_autotest_dashboard";
        String email = "autotestbeta@gmail.com";

        openURLdashboard(getBaseUrl());

//        dashboardLogin(driver, sBP, "tester");
//        dashboardPassword(driver, sBP, "tester");
        AuthorizationBySetLoginPassword("tester", "tester");
        clickButton("Увійти");

        openTab();
        setRegionTab(sBP, "Документи");

        setRegionTab(sBP, "Мій розклад");

        setRegionTab(sBP, "Історія");

        setRegionTab(sBP, "Документи");

//        setRegionFindOrder(driver, sBP, "214200904");
//        clickButton(driver, sBP, "Взяти в роботу");
//        clickButton(driver, sBP, "Опрацювати");
    }
}
