/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute.iGov;

import autoTests.CustomMethods;
import autoTests.pages.main.TemplatePage;
import org.junit.Test;

/**
 *
 * @author HS
 */
public class _test_tables_other_types extends CustomMethods {
//<editor-fold desc="Тестовый пример загрузки файла">

    @Test
    public void Test_test_tables_other_types() throws Exception {
        /**
         * ***************************************объявляем элементы страниц******************************************
         */
        TemplatePage o = new TemplatePage();
        String sBP = "_test_tables_other_types";
        String email = "autotestbeta@gmail.com";

        openURLservice(getBaseUrl() + "/service/1583/general");

        o.selectRegion("Дніпропетровська");

        o.mokAuthorization();

        setFieldValue("phone", "+380623155533");
        setFieldValue("email", email);
        setFieldSelectByText("type_lic", "експорт"); // ENUM
        setFieldSelectByText("subekt", "фізична особа-підприємець"); // ENUM
        setFieldValue("fiz_name", "Іван Іванов Іванович");
        setFieldValue("fiz_adres", " Україна, Дніпропетровська область, м. Дніпро");
        setFieldValue("fiz_drfo", "1234567899123");
        setTableCellsInputTypeString("sTable1", "sNumberAccountUah", "0", "12345678901223456789");
        setTableCellsInputTypeString("sTable1", "sBankNameUah", "0", "PRIVATBANK");
        setTableCellsInputTypeString("sTable1", "sBankMfoUah", "0", "012345");
        setTableCellsInputTypeString("sTable1", "sBankAdresUah", "0", "вул. Банкова, 11, м. Київ, 01220");
        addTableRow("sTable1");
        setTableCellsInputTypeString("sTable1", "sNumberAccountUah", "1", "12345678901223456789");
        setTableCellsInputTypeString("sTable1", "sBankNameUah", "1", "PRIVATBANK");
        setTableCellsInputTypeString("sTable1", "sBankMfoUah", "1", "012345");
        setTableCellsInputTypeString("sTable1", "sBankAdresUah", "1", "вул. Банкова, 11, м. Київ, 01220");

        setTableCellsInputTypeString("sTable2", "sNumberAccountCurrency", "0", "12345678901223456789");
        setTableCellsInputTypeString("sTable2", "sBankNameCurrency", "0", "PRIVATBANK");
        setTableCellsInputTypeString("sTable2", "sBankMfoCurrency", "0", "012345");
        setTableCellsInputTypeString("sTable2", "sBankAdresCurrency", "0", "вул. Банкова, 11, м. Київ, 01220");
        addTableRow("sTable2");
        setTableCellsInputTypeString("sTable2", "sNumberAccountCurrency", "1", "12345678901223456789");
        setTableCellsInputTypeString("sTable2", "sBankNameCurrency", "1", "PRIVATBANK");
        setTableCellsInputTypeString("sTable2", "sBankMfoCurrency", "1", "012345");
        setTableCellsInputTypeString("sTable2", "sBankAdresCurrency", "1", "вул. Банкова, 11, м. Київ, 01220");

        setFieldSelectByText("subekt_1", "юридична особа");
        setFieldValue("ur_name_1", "Deutsche Bank");
        setFieldValue("ur_adres_1", "PJSC “Deutsche Bank DBU” Lavrska street 20 01015 Kiev UKRAINE");
        setFieldValue("applicant_account_for", "1234567899123");
        setFieldValue("for_bank_name", "Deutsche Bank DBU");
        setFieldValue("for_bank_adres", "PJSC “Deutsche Bank DBU” Lavrska street 20 01015 Kiev UKRAINE");

        setTableCellsInputTypeSelect("sTable3", "sObjectCustoms", "0", "-0-0");
        setTableCellsInputTypeString("sTable3", "nQuantity", "0", "2");
        setTableCellsInputTypeEnum("sTable3", "sUnit", "0", "кілограм|кг");
        setTableCellsInputTypeString("sTable3", "sCostUsd", "0", "12.02020");
        setTableCellsInputTypeString("sTable3", "sCostCurrency", "0", "12.02020");
        setTableCellsInputTypeString("sTable3", "sAddNameProduct", "0", "12.02020");

        setTableCellsInputTypeEnum("sTable4", "sBasis", "0", "EXW - Франко-завод");
        setTableCellsInputTypeString("sTable4", "sBasisName", "0", "м. Дніпро");
        addTableRow("sTable4");
        setTableCellsInputTypeEnum("sTable4", "sBasis", "1", "EXW - Франко-завод");
        setTableCellsInputTypeString("sTable4", "sBasisName", "1", "м. Дніпро");

        setTableCellsInputTypeSelect("sTable5", "sCountry_", "00", "-1-0");

        setFieldTypeSelect("sCountry_1", "-6-2");
        setTableCellsInputTypeSelect("sTable6", "sCountry_", "20", "-2-1");
        setTableCellsInputTypeSelect("sTable7", "sCurrency", "0", "-3-1");
        setTableCellsInputTypeSelect("sTable8", "sCurrency_", "10", "-4-3");
        setTableCellsInputTypeEnum("sTable10", "SCharakterUgoda", "0", "10 Переміщення товарів з розрахунками за товар у валюті України");
        setFieldValue("dogovor_number", "№32145");
        setFieldCalendar(sBP, "dogovor_date", "01/03/17");
        setTableCellsInputTypeString("sTable11", "sNameDodatok", "0", "Назва документу");
        setTableCellsInputTypeString("sTable11", "sNumberDodatok", "0", "1234657");
        setTableCellsTypeCalendar("sTable11", "sDateDodatok", "0", "01/03/18");
        setFieldValue("special", "№32145");
        setFieldFile(sBP, "dogovor", "src/test/resources/files/test.jpg");
        setFieldFile(sBP, "appeal", "src/test/resources/files/test.jpg");
        setFieldFile(sBP, "visnovok", "src/test/resources/files/test.jpg");
        setFieldFile(sBP, "other_docs", "src/test/resources/files/test.jpg");

        click(o.buttonSendingForm);
    }
}
