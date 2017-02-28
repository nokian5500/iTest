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
public class Test_dnepr_cnap_267 extends CustomMethods {
//<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_dnepr_cnap_267() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        String sBP = "dnepr_cnap_267";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/160/general");
        
        _step("3. Выбор области/города");
        o.selectRegion("Дніпропетровська");
        
            _step("4. Авторизация Off AuthMock/BankID");
            o.mokAuthorization();
        
        _step("5. Заполняем форму услуги");
        setFieldValue(driver, sBP, "phone", "+380623155533");
        setFieldValue(driver, sBP, "email", email);
        setFieldSelectByText(driver,sBP,"type_lic","експорт"); // ENUM
        setFieldSelectByText(driver,sBP,"subekt","фізична особа-підприємець"); // ENUM
        setFieldValue(driver, sBP, "fiz_name", "Іван Іванов Іванович");
        setFieldValue(driver, sBP, "fiz_adres", " Україна, Дніпропетровська область, м. Дніпро");
        setFieldValue(driver, sBP, "fiz_drfo", "1234567899123");
        
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > div > input", "12345678901223456789");
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > input", "PRIVATBANK");
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(1) > td:nth-child(3) > div > div > input", "012345");
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > div > input", "вул. Банкова, 11, м. Київ, 01220");
        driver.findElement(By.cssSelector("#field-sTable1 > div > div > input")).click();
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > input", "12345678901223456789");
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > div > input", "PRIVATBANK");
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(2) > td:nth-child(3) > div > div > input", "012345");
        setTableCellsString(driver, sBP, "#field-sTable1 > div > table > tbody > tr:nth-child(2) > td:nth-child(4) > div > div > input", "вул. Банкова, 11, м. Київ, 01220");
        
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(1) > td:nth-child(1) > div > div > input", "12345678901223456789");
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div > input", "PRIVATBANK");
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(1) > td:nth-child(3) > div > div > input", "012345");
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div > div > input", "вул. Банкова, 11, м. Київ, 01220");
        driver.findElement(By.cssSelector("#field-sTable2 > div > div > input")).click();
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > input", "12345678901223456789");
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > div > input", "PRIVATBANK");
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(2) > td:nth-child(3) > div > div > input", "012345");
        setTableCellsString(driver, sBP, "#field-sTable2 > div > table > tbody > tr:nth-child(2) > td:nth-child(4) > div > div > input", "вул. Банкова, 11, м. Київ, 01220");
        
        setFieldSelectByText(driver, sBP, "subekt_1", "юридична особа");
        setFieldValue(driver, sBP, "ur_name_1", "Deutsche Bank");
        setFieldValue(driver, sBP, "ur_adres_1", "PJSC “Deutsche Bank DBU” Lavrska street 20 01015 Kiev UKRAINE");
        setFieldValue(driver, sBP, "applicant_account_for", "1234567899123");
        setFieldValue(driver, sBP, "for_bank_name", "Deutsche Bank DBU");
        setFieldValue(driver, sBP, "for_bank_adres", "PJSC “Deutsche Bank DBU” Lavrska street 20 01015 Kiev UKRAINE");
        
        setTableCellsMultipleSelect(driver, sBP, "#field-sTable3 > div > table > tbody > tr > td:nth-child(1) > div > div > div > div > span", "-0-1");
        setTableCellsString(driver, sBP, "#field-sTable3 > div > table > tbody > tr > td:nth-child(4) > div > div > input", "2");
        setTableCellsSelectDropDown(driver, sBP, "#field-sTable3 > div > table > tbody > tr > td:nth-child(3) > div > div > select");
        setTableCellsString(driver, sBP, "#field-sTable3 > div > table > tbody > tr > td:nth-child(5) > div > div > input", "12.02020");
        setTableCellsString(driver, sBP, "#field-sTable3 > div > table > tbody > tr > td:nth-child(6) > div > div > input", "12.02020");
        setTableCellsString(driver, sBP, "#field-sTable3 > div > table > tbody > tr > td:nth-child(7) > div > div > input", "12.02020");
       
        setTableCellsSelectDropDown(driver, sBP, "#field-sTable4 > div > table > tbody > tr > td:nth-child(1) > div > div > select");
        setTableCellsString(driver, sBP, "#field-sTable4 > div > table > tbody > tr > td:nth-child(2) > div > div > input", "м. Дніпро");
        driver.findElement(By.cssSelector("#field-sTable4 > div > div > input")).click();
        setTableCellsSelectDropDown(driver, sBP, "#field-sTable4 > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > select");
        setTableCellsString(driver, sBP, "#field-sTable4 > div > table > tbody > tr:nth-child(2) > td:nth-child(2) > div > div > input", "м. Дніпро");
        setTableCellsMultipleSelect(driver, sBP, "#field-sTable5 > div > table > tbody > tr > td:nth-child(1) > div > div > div > div > span", "-1-0");
        driver.findElement(By.cssSelector("#field-sTable5 > div > div > input")).click();
        setTableCellsMultipleSelect(driver, sBP, "#field-sTable5 > div > table > tbody > tr:nth-child(2) > td:nth-child(1) > div > div > div > div > span", "-7-1");
        setFieldMultipleSelect(driver, sBP, "#field-sCountry_1 > div > div > div > span", "-6-2");
        setTableCellsMultipleSelect(driver, sBP, "#field-sTable6 > div > table > tbody > tr > td:nth-child(1) > div > div > div > div > span", "-2-1");
        setTableCellsMultipleSelect(driver, sBP, "#field-sTable7 > div > table > tbody > tr > td:nth-child(1) > div > div > div > div > span", "-3-1");
        setTableCellsMultipleSelect(driver, sBP, "#field-sTable8 > div > table > tbody > tr > td:nth-child(1) > div > div > div > div > span", "-4-3");
        setTableCellsSelectDropDown(driver, sBP, "#field-sTable10 > div > table > tbody > tr > td > div > div > select");            //#field-sTable10 > div > table > tbody > tr > td > div > div > select
        setFieldValue(driver, sBP, "dogovor_number", "№32145");
        setFieldCalendar(driver, sBP, "dogovor_date", "01/03/17"); // #field-dogovor_date > p > input
        setTableCellsString(driver, sBP, "#field-sTable11 > div > table > tbody > tr > td:nth-child(1) > div > div > input", "Назва документу");
        setTableCellsString(driver, sBP, "#field-sTable11 > div > table > tbody > tr > td:nth-child(2) > div > div > input", "1234657");
        setTableCellsCalendar(driver, sBP, "#field-sTable11 > div > table > tbody > tr > td:nth-child(3) > div > div > p > input","sDateDodatok0", "01/03/18"); //#field-sTable11 > div > table > tbody > tr > td:nth-child(3) > div > div > p > input
        setFieldValue(driver, sBP, "special", "№32145");
        setFieldFile(driver, sBP, "dogovor", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "appeal", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "visnovok", "src/test/resources/files/test.jpg");
        setFieldFile(driver, sBP, "other_docs", "src/test/resources/files/test.jpg");
         
        click(driver, o.buttonSendingForm);

        
        
        
    }
}
