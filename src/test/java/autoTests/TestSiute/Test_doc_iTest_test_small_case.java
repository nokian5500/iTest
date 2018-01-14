package autoTests.TestSiute;

import autoTests.CustomMethods;
import org.junit.Test;


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

    @Test
    public void Test_doc_iTest_test_small_case() throws Exception {
        String sBP = "_doc_iTest_test_small_case";
        String email = "autotestbeta@gmail.com";

        openURLdashboard(getBaseUrl());

        AuthorizationBySetLoginPassword("iTest_User_0007", "iTest_User_0007");
        clickButton("Увійти");
        //002. Тестовий документ (тестування вибору співробітників)
        navigateToggleMenu();
        createDocumentOrTask("002. Тестовий документ (тестування вибору співробітників)");
        clickButton("Далi");
        pause(10000);
        SetRegionFieldInputTypeString("sVarString", "Конечно оставим :)");

        /*Таблица Узгоджуючі*/
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "0", "Співробітник2 підрозділу 2.2");
        addRegionsTableRow("sTableAcceptor");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAcceptor", "sName_Acceptor", "1", "керівник підрозділу 2.1");

        /*Таблица Затверджуючі*/
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableAgree", "sName_Approver", "0", "Замдиректора Потапченко Василь");

        /*Таблица Адресат*/
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableDirect", "sName_Addressee", "0", "керівник П2");
        addRegionsTableRow("sTableDirect");
        setRegionTableCellsInputTypeEnumInput(sBP, "sTableDirect", "sName_Addressee", "1", "керівник підрозділу 2.2");

        clickButtonCreate();
    }
}
