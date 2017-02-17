/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autoTests.TestSiute;

import autoTests.API.DeleteTask;
import autoTests.ConfigurationVariables;
import autoTests.pages.main.TemplatePage;
import autoTests.CustomMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

/**
 *
 * @author HS
 */
public class dvs_745 extends CustomMethods{
 //<editor-fold desc="Тестовый пример загрузки файла">
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void dvs_745() throws Exception {
        /*****************************************объявляем элементы страниц*******************************************/
        TemplatePage o = new TemplatePage(driver);
        //  Вносим в переменные название услуги и почту
        String sBP = "_test_all_case";
        String email = "autotestbeta@gmail.com";

        _step("1. Вход по прямому URL на услугу");
        openURLservice(driver, CV.baseUrl + "/service/745/general");

         _step("3. Выбор области/города");
        o.selectRegion("Вінницька");
        o.selectCity("Вінниця");

        _step("3. Авторизация Off AuthMock/BankID");
        o.mokAuthorization();

        
        _step("4. Заполняем форму услуги");
        setFieldAutocomplete(driver,"sID_Public_SubjectOrganJoin","_test_all_case");
        setFieldValue(driver, sBP, "phone", "+380999999999");
        setFieldValue(driver, sBP, "email", email);
        setFieldValue(driver, sBP, "sFileDescription", "Пояснення");
        setFieldFile(driver, sBP, "nFile0", "src/test/resources/files/test.jpg");
        o.setFieldSelectSlotDate(driver, sBP, "bpID -- fieldID -- visitDay");
        o.setFieldSelectSlotTime(driver, sBP, "bpID -- fieldID -- visitTime");
        o.setFieldSelectSlotTime(driver, sBP, "bpID -- fieldID -- visitTime");
        o.setFieldCheckBox(driver, sBP, "новый локатор");
        
        pause (1000000);
       

    }
    //</editor-fold>
    
     @Test(enabled = false, groups = {"Main", "Критический функционал"}, priority = 2)
    public void testDeleteMethodForDEBUG() throws Exception {
        DeleteTask delete = new DeleteTask();

        ConfigurationVariables configVariables = ConfigurationVariables.getInstance();
        configVariables.orderId.add("");
//        delete.deleteAllOrderId();
        delete.deleteAllOrderId();
      
    }
}
