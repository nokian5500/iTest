/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 *
 * @author User
 */
public class Test_checkbox extends CustomMethods {

    //<editor-fold desc="Тестовый пример заполнение полей">
    @Test
    public void Test_checkbox() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        //  Вносим в переменные название услуги начиная с точки ._test_fields_bankid_--_ и до начала названия поля
        String sBP = "_test_field_checkbox";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/1550/general");

        o.mokAuthorization();

        pause(1000);

//        setFieldCheckBox(driver, sBP, "." + sBP + "_--_" + "#bFavorite11"); // #bFavorite11
        pause(10000);
    }
}
