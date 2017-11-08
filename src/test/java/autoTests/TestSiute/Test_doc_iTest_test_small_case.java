package autoTests.TestSiute;

import autoTests.CustomMethods;
import org.testng.annotations.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Test_doc_iTest_test_small_case extends CustomMethods {
    @Test(enabled = true, groups = {"Main", "Критический функционал"}, priority = 2)
    public void Test_doc_iTest_test_small_case() throws Exception {
        String sBP = "_doc_iTest_test_small_case";
        String email = "autotestbeta@gmail.com";
        _step("1. Вход по прямому URL на дашборд");
        openURLdashboard(driver, sBP);
        _step("2. Авторизация login/BankID на дашборде. login/pass: (iTest_User_0007/iTest_User_0007)");
        AuthorizationBySetLoginPassword(driver, sBP, "iTest_User_0007", "iTest_User_0007");
        clickButton(driver, sBP, "Увійти");
        //002. Тестовий документ (тестування вибору співробітників)
        navigateToggleMenu();
        createDocumentOrTask("002. Тестовий документ (тестування вибору співробітників)");
        clickButton(driver, sBP, "Далi");
        SetRegionFieldInputTypeString(driver, sBP, "sVarString", "Конечно оставим :)");
        
        /*Таблица Узгоджуючі*/
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник2 підрозділу 2.2");
        addRegionsTableRow(driver, sBP, "sTableAcceptor");
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAcceptor", "sName_Acceptor", "1", "керівник підрозділу 2.1");
        
        /*Таблица Затверджуючі*/
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableAgree", "sName_Approver", "0", "Замдиректора Потапченко Василь");
        
        /*Таблица Адресат*/
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableDirect", "sName_Addressee", "0", "керівник П2");
        addRegionsTableRow(driver, sBP, "sTableDirect");
        setRegionTableCellsInputTypeEnumInput(driver, sBP, "sTableDirect", "sName_Addressee", "1", "керівник підрозділу 2.2");
        
        clickButtonCreate(driver, sBP);
    }
}
