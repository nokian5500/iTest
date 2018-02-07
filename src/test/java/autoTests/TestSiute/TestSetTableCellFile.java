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
 * @author HS
 */
public class TestSetTableCellFile extends CustomMethods {
//<editor-fold desc="Тестовый пример загрузки файла">

    @Test
    public void TestSetTableCellFile() throws Exception {
        /**
         * ***************************************объявляем элементы
         * страниц******************************************
         */

        TemplatePage o = new TemplatePage();
        String sBP = "_test_newattach_NewTable";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/291/general");

        assertThis(o.usluga, "_test_newattach_NewTable");
//        _step("3. Выбор области/города");
//        o.selectRegion("Дніпропетровська");

        o.mokAuthorization();

        setFieldValue("sPositionAplicant", "Іванов Іван Іванович");
        setFieldValue("email", email);
        setFieldValue("phone", "+380623155533");
        setFieldValue("sNumberCase", "380623155533");
        setFieldValue("sStatus", "особа, яка зацікавлена в результаті розгляду справи");
        setFieldSelectByText("sSubekt", "фізична особа або фізична особа-підприємець"); // ENUM
        setFieldValue("sNameFiz", "Іван Іванович");


        //setFieldValue("fiz_name", "Іван Іванов Іванович");
        //setFieldValue("fiz_adres", " Україна, Дніпропетровська область, м. Дніпро");
        //setFieldValue("fiz_drfo", "1234567899123");
        setTableCellsInputTypeEnum("sTableDoc", "sCategoryDoc", "0", "заява про ознайомлення з матеріалами справи");
        setTableCellsInputTypeFile(sBP, "sTableDoc", "sDoc", "0", "src/test/resources/files/test.jpg");
    }
}
