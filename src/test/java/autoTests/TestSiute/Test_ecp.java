/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.testng.annotations.Test;

/**
 *
 * @author User
 */
public class Test_ecp extends CustomMethods {
@Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_test_tables_other_types() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        String sBP = "_test_tables_other_types";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/87/general");
        
        _step("3. Выбор области/города");
        o.selectRegion("Донецька");
        
        _step("4. Авторизация Off AuthMock/BankID");
        o.testPriva24Authorization();
    }
}