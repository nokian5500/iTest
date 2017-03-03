/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 *
 * @author HS
 */
public class TestSetTableCellFile extends CustomMethods {
//<editor-fold desc="Тестовый пример загрузки файла">

    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void TestSetTableCellFile() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */

        TemplatePage o = new TemplatePage(driver);
        String sBP = "_test_newattach_NewTable";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/291/general");
        
        _step("2. Проверить, что открылась нужная услуга");
        assertThis(driver, o.usluga, "_test_newattach_NewTable");
//        _step("3. Выбор области/города");
//        o.selectRegion("Дніпропетровська");

        _step("4. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        _step("5. Заполняем форму услуги");
        setFieldValue(driver, sBP, "sPositionAplicant", "Іванов Іван Іванович");
        setFieldValue(driver, sBP, "email", email);
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "sNumberCase", "380623155533");
        setFieldValue(driver, sBP, "sStatus", "особа, яка зацікавлена в результаті розгляду справи");
        setFieldValue(driver, sBP, "sNameFiz", "Іван Іванович");
        
        setFieldSelectByText(driver, sBP, "subekt", "фізична особа-підприємець"); // ENUM
        setFieldValue(driver, sBP, "fiz_name", "Іван Іванов Іванович");
        setFieldValue(driver, sBP, "fiz_adres", " Україна, Дніпропетровська область, м. Дніпро");
        setFieldValue(driver, sBP, "fiz_drfo", "1234567899123");
        setTableCellsInputTypeEnum(driver, sBP, "sTableDoc", "sCategoryDoc0", "3");
        setTableCellsTypeFile(driver, sBP, "sTableDoc","sDoc" ,"src/test/resources/files/test.jpg");
    }
}
