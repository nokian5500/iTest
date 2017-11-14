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
public class Test_checkbox extends CustomMethods{
    
    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 1)
    public void Test_checkbox() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "_test_field_checkbox";
        String email = "autotestbeta@gmail.com";
        
        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/1550/general");
        
         _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();
        
        _step("5. Заполняем форму услуги");
        
        pause(1000);
        
//        setFieldCheckBox(driver, sBP, "." + sBP + "_--_" + "#bFavorite11"); // #bFavorite11

        pause(10000);
    }
}
