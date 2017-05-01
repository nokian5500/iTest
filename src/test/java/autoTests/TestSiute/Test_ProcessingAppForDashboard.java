/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 *
 * @author User
 */
public class Test_ProcessingAppForDashboard extends CustomMethods  {
    //<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_ProcessingAppForDashboard() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        String sBP = "_test_autotest_dashboard";
        String email = "autotestbeta@gmail.com";
        
        _step("1. Вход по прямому URL на дашборд");
        openURLdashboard(driver, sBP);
        
        _step("2. Авторизация login/BankID на дашборде. login/pass: (tester/tester)");
//        dashboardLogin(driver, sBP, "tester");
//        dashboardPassword(driver, sBP, "tester");
        AuthorizationBySetLoginPassword(driver, sBP, "tester", "tester");
        clickButton(driver, sBP, "Увійти");
        pause(3000);
//        setRegionFindOrder(driver, sBP, "202103569");
//           pause(9000);
//        setRegionTab(driver, sBP, "Документи");
//        
//        setRegionTab(driver, sBP, "Мій розклад");
//        
//        
//        setRegionTab(driver, sBP, "Історія");
//        
//        
//        setRegionTab(driver, sBP, "Документи");
        
//        setRegionFindOrder(driver, sBP, "214200904");
        
//        clickButton(driver, sBP, "Взяти в роботу");
//        clickButton(driver, sBP, "Опрацювати");

}
}
